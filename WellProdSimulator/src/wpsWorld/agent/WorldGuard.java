package wpsWorld.Agent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import BESA.Util.PeriodicDataBESA;
import wpsWorld.Guards.PeasantCommGuard;
import wpsWorld.Guards.PeasantCommMessage;
import wpsWorld.Guards.PeasantCommMessageType;
import wpsWorld.Messages.WorldMessage;
import wpsControl.Agent.DateSingleton;
import wpsWorld.Helper.WorldConfiguration;
import wpsWorld.layer.crop.CropLayer;
import wpsWorld.layer.crop.cell.CropCell;
import wpsWorld.layer.crop.cell.CropCellState;
import wpsWorld.layer.disease.DiseaseCellState;
import org.json.JSONObject;

/**
 * BESA world's guard, holds the actions that receive from the peasant agent
 */
public class WorldGuard extends GuardBESA {

    private WorldConfiguration worldConfig = WorldConfiguration.getPropsInstance();

    /**
     *
     * @param eventBESA
     */
    @Override
    public void funcExecGuard(EventBESA eventBESA) {
        WorldMessage worldMessage = (WorldMessage) eventBESA.getData();
        //ReportBESA.info("--->" + worldMessage.toString());
        WorldState worldState = (WorldState) this.agent.getState();
        PeasantCommMessage peasantMessage;
        switch (worldMessage.getWorldMessageType()) {
            case CROP_INIT:
                ReportBESA.info("Start event, initialize first layers state");
                worldState.lazyUpdateCropsForDate(worldMessage.getDate());
                DateSingleton.getInstance().getDatePlusOneDayAndUpdate();
                peasantMessage = new PeasantCommMessage(
                        PeasantCommMessageType.CROP_INIT,
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
                peasantMessage = new PeasantCommMessage(
                        PeasantCommMessageType.CROP_INFORMATION_NOTIFICATION,
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
                        this.notifyPeasantCropProblem(PeasantCommMessageType.NOTIFY_CROP_WATER_STRESS,
                                cropCell.getAgentPeasantId(),
                                worldMessage.getDate());
                    }
                    if (((DiseaseCellState) cropCell.getDiseaseCell().getCellState()).isInfected()) {
                        this.notifyPeasantCropProblem(PeasantCommMessageType.NOTIFY_CROP_DISEASE,
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
                peasantMessage = new PeasantCommMessage(
                        PeasantCommMessageType.CROP_INFORMATION_NOTIFICATION, 
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
                peasantMessage = new PeasantCommMessage(
                        PeasantCommMessageType.CROP_INFORMATION_NOTIFICATION, 
                        worldMessage.getPeasantAgentId(), 
                        "CROP_PESTICIDE");
                peasantMessage.setDate(worldMessage.getDate());
                this.replyToPeasantAgent(worldMessage.getPeasantAgentId(), peasantMessage);
                break;
            case CROP_HARVEST:
                this.harvestCrop(worldState.getCropLayer());
                peasantMessage = new PeasantCommMessage(
                        PeasantCommMessageType.CROP_HARVEST,
                        worldMessage.getPeasantAgentId(),
                        "CROP_HARVEST");
                peasantMessage.setDate(worldMessage.getDate());
                this.replyToPeasantAgent(
                        worldMessage.getPeasantAgentId(), 
                        peasantMessage);
                break;
        }
    }

    /**
     *
     * @param peasantAgentId
     * @param peasantMessage
     */
    public void replyToPeasantAgent(String peasantAgentId, PeasantCommMessage peasantMessage) {
        try {
            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAlias(peasantAgentId);
            EventBESA event = new EventBESA(
                    PeasantCommGuard.class.getName(), 
                    peasantMessage);
            ah.sendEvent(event);
            ReportBESA.debug("Sent: " + peasantMessage.getPayload());
        } catch (ExceptionBESA e) {
            ReportBESA.error(e.getMessage());
        }
    }

    /**
     *
     * @param messageType
     * @param aid
     * @param date
     */
    public void notifyPeasantCropProblem(PeasantCommMessageType messageType, String aid, String date) {
        try {
            ReportBESA.debug("AgentID: " + aid);
            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAid(aid);
            PeasantCommMessage peasantMessage = new PeasantCommMessage(
                    messageType,
                    aid,
                    null);
            peasantMessage.setDate(date);
            EventBESA event = new EventBESA(
                    PeasantCommGuard.class.getName(), 
                    peasantMessage);
            ReportBESA.debug("Sent: " + peasantMessage.getSimpleMessage());
            ah.sendEvent(event);
        } catch (ExceptionBESA e) {
            ReportBESA.error(e.getMessage());
        }
    }

    /**
     *
     * @param aid
     * @param date
     */
    public void notifyPeasantCropReadyToHarvest(String aid, String date) {
        try {
            ReportBESA.debug("AgentID: " + aid);
            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAid(aid);
            PeasantCommMessage peasantMessage = new PeasantCommMessage(
                    PeasantCommMessageType.NOTIFY_CROP_READY_HARVEST,
                    aid,
                    null);
            peasantMessage.setDate(date);
            EventBESA event = new EventBESA(
                    PeasantCommGuard.class.getName(), 
                    peasantMessage);
            ReportBESA.debug("Sent: " + peasantMessage.getSimpleMessage());
            ah.sendEvent(event);
        } catch (ExceptionBESA e) {
            ReportBESA.error(e.getMessage());
        }
    }

    /**
     *
     * @param cropLayer
     */
    public void harvestCrop(CropLayer cropLayer) {
        cropLayer.writeCropData();
        try {
            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAid(this.agent.getAid());
            PeriodicDataBESA periodicDataBESA = new PeriodicDataBESA(PeriodicGuardBESA.STOP_CALL);
            EventBESA eventPeriodic = new EventBESA(
                    PeasantCommGuard.class.getName(), 
                    periodicDataBESA);
            ah.sendEvent(eventPeriodic);
        } catch (ExceptionBESA e) {
            ReportBESA.error(e.getMessage());
        }
    }
}
