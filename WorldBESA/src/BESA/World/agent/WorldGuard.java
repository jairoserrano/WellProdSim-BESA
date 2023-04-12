package BESA.World.agent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import BESA.Util.PeriodicDataBESA;
import BESA.World.agents.ExternalComm.ExternalCommGuard;
import BESA.World.agents.ExternalComm.ExternalCommMessage;
import BESA.World.agents.ExternalComm.ExternalCommMessageType;
import BESA.World.agents.messages.world.WorldMessage;
import BESA.World.helper.DateSingleton;
import BESA.World.helper.WorldConfiguration;
import BESA.World.layer.crop.CropLayer;
import BESA.World.layer.crop.cell.CropCell;
import BESA.World.layer.crop.cell.CropCellState;
import BESA.World.layer.disease.DiseaseCellState;
import org.json.JSONObject;

/**
 * BESA world's guard, holds the actions that receive from the peasant agent
 */
public class WorldGuard extends GuardBESA {

    private WorldConfiguration worldConfig = WorldConfiguration.getPropsInstance();

    @Override
    public void funcExecGuard(EventBESA eventBESA) {
        WorldMessage worldMessage = (WorldMessage) eventBESA.getData();
        //ReportBESA.info("--->" + worldMessage.toString());
        WorldState worldState = (WorldState) this.agent.getState();
        ExternalCommMessage peasantMessage;
        switch (worldMessage.getWorldMessageType()) {
            case CROP_INIT:
                ReportBESA.info("Start event, initialize first layers state");
                worldState.lazyUpdateCropsForDate(worldMessage.getDate());
                DateSingleton.getInstance().getDatePlusOneDayAndUpdate();
                peasantMessage = new ExternalCommMessage(
                        ExternalCommMessageType.CROP_INIT,
                        worldMessage.getPeasantAgentId(),
                        "CROP_INIT");
                peasantMessage.setDate(worldMessage.getDate());
                this.replyToPeasantAgent(
                        worldMessage.getPeasantAgentId(), 
                        peasantMessage);
                break;
            case CROP_INFORMATION:
                ReportBESA.info("Message received:"
                        + " Crop - " + worldMessage.getCropId() 
                        + " Information " + worldMessage.getPayload() 
                        + " Date: " + worldMessage.getDate());
                worldState.lazyUpdateCropsForDate(worldMessage.getDate());
                CropCellState cropCellState = worldState.getCropLayer().getCropStateById(worldMessage.getCropId());
                CropCell cropCellInfo = worldState.getCropLayer().getCropCellById(worldMessage.getCropId());
                DiseaseCellState diseaseCellState = (DiseaseCellState) cropCellInfo.getDiseaseCell().getCellState();
                JSONObject cropDataJson = new JSONObject(cropCellState);
                cropDataJson.put("disease", diseaseCellState.isInfected());
                cropDataJson.put("cropHarvestReady", cropCellInfo.isHarvestReady());
                peasantMessage = new ExternalCommMessage(
                        ExternalCommMessageType.CROP_INFORMATION_NOTIFICATION,
                        worldMessage.getPeasantAgentId(),
                        cropDataJson.toString());
                peasantMessage.setDate(worldMessage.getDate());
                this.replyToPeasantAgent(worldMessage.getPeasantAgentId(), peasantMessage);
                break;
            case CROP_OBSERVE:
                ReportBESA.info("Observing crops (lazy mode).... on date: " 
                        + worldMessage.getDate());
                worldState.getCropLayer().getAllCrops().forEach(cropCell -> {
                    if (((CropCellState) cropCell.getCellState()).isWaterStress()) {
                        this.notifyPeasantCropProblem(
                                ExternalCommMessageType.NOTIFY_CROP_WATER_STRESS,
                                cropCell.getAgentPeasantId(),
                                worldMessage.getDate());
                    }
                    if (((DiseaseCellState) cropCell.getDiseaseCell().getCellState()).isInfected()) {
                        this.notifyPeasantCropProblem(
                                ExternalCommMessageType.NOTIFY_CROP_DISEASE,
                                cropCell.getAgentPeasantId(),
                                worldMessage.getDate());
                    }
                    if (cropCell.isHarvestReady()) {
                        this.notifyPeasantCropReadyToHarvest(
                                cropCell.getAgentPeasantId(), 
                                worldMessage.getDate());
                    }
                });
                break;
            case CROP_IRRIGATION:
                // Adding the event of irrigation, will be reflected in the next layers execution
                ReportBESA.info("Irrigation on the crop, date: " + worldMessage.getDate());
                String cropIdToIrrigate = worldMessage.getCropId();
                String defaultWaterQuantity = this.worldConfig.getProperty("crop.defaultValuePerIrrigation");
                worldState.getCropLayer().addIrrigationEvent(
                        cropIdToIrrigate, 
                        defaultWaterQuantity, 
                        worldMessage.getDate());
                peasantMessage = new ExternalCommMessage(
                        ExternalCommMessageType.CROP_INFORMATION_NOTIFICATION, 
                        worldMessage.getPeasantAgentId(), 
                        "CROP_IRRIGATION");
                peasantMessage.setDate(worldMessage.getDate());
                this.replyToPeasantAgent(
                        worldMessage.getPeasantAgentId(), 
                        peasantMessage);
                break;
            case CROP_PESTICIDE:
                // Adding the event of pesticide, will be reflected in the next layers execution
                ReportBESA.info("Adding pesticide to the crop, date: " + worldMessage.getDate());
                String cropIdToAddPesticide = worldMessage.getCropId();
                String defaultCropInsecticideCoverage = this.worldConfig.getProperty("disease.insecticideDefaultCoverage");
                String diseaseCellId = worldState.getCropLayer().getCropCellById(cropIdToAddPesticide).getDiseaseCell().getId();
                worldState.getDiseaseLayer().addInsecticideEvent(
                        diseaseCellId,
                        defaultCropInsecticideCoverage, 
                        worldMessage.getDate());
                peasantMessage = new ExternalCommMessage(
                        ExternalCommMessageType.CROP_INFORMATION_NOTIFICATION, 
                        worldMessage.getPeasantAgentId(), 
                        "CROP_PESTICIDE");
                peasantMessage.setDate(worldMessage.getDate());
                this.replyToPeasantAgent(worldMessage.getPeasantAgentId(), peasantMessage);
                break;
            case CROP_HARVEST:
                this.harvestCrop(worldState.getCropLayer());
                peasantMessage = new ExternalCommMessage(
                        ExternalCommMessageType.CROP_HARVEST,
                        worldMessage.getPeasantAgentId(),
                        "CROP_HARVEST");
                peasantMessage.setDate(worldMessage.getDate());
                this.replyToPeasantAgent(
                        worldMessage.getPeasantAgentId(), 
                        peasantMessage);
                break;
        }
    }

    public void replyToPeasantAgent(String peasantAgentId, ExternalCommMessage peasantMessage) {
        try {
            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAlias(peasantAgentId);
            EventBESA event = new EventBESA(
                    "ReceiveMessagesFromWorldGuard", 
                    peasantMessage);
            ah.sendEvent(event);
            ReportBESA.debug("Sent: " + peasantMessage.getPayload());
        } catch (ExceptionBESA e) {
            ReportBESA.error(e.getMessage());
        }
    }

    public void notifyPeasantCropProblem(ExternalCommMessageType messageType, String aid, String date) {
        try {
            ReportBESA.debug("AgentID: " + aid);
            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAid(aid);
            ExternalCommMessage peasantMessage = new ExternalCommMessage(
                    messageType,
                    aid,
                    null);
            peasantMessage.setDate(date);
            EventBESA event = new EventBESA(
                    "ReceiveMessagesFromWorldGuard", 
                    peasantMessage);
            ReportBESA.debug("Sent: " + peasantMessage.getSimpleMessage());
            ah.sendEvent(event);
        } catch (ExceptionBESA e) {
            ReportBESA.error(e.getMessage());
        }
    }

    public void notifyPeasantCropReadyToHarvest(String aid, String date) {
        try {
            ReportBESA.debug("AgentID: " + aid);
            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAid(aid);
            ExternalCommMessage peasantMessage = new ExternalCommMessage(
                    ExternalCommMessageType.NOTIFY_CROP_READY_HARVEST,
                    aid,
                    null);
            peasantMessage.setDate(date);
            EventBESA event = new EventBESA(
                    "ReceiveMessagesFromWorldGuard", 
                    peasantMessage);
            ReportBESA.debug("Sent: " + peasantMessage.getSimpleMessage());
            ah.sendEvent(event);
        } catch (ExceptionBESA e) {
            ReportBESA.error(e.getMessage());
        }
    }

    public void harvestCrop(CropLayer cropLayer) {
        cropLayer.writeCropData();
        try {
            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAid(this.agent.getAid());
            PeriodicDataBESA periodicDataBESA = new PeriodicDataBESA(PeriodicGuardBESA.STOP_CALL);
            EventBESA eventPeriodic = new EventBESA("ReceiveMessagesFromWorldGuard", periodicDataBESA);
            ah.sendEvent(eventPeriodic);
        } catch (ExceptionBESA e) {
            ReportBESA.error(e.getMessage());
        }
    }
}
