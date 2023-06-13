package wpsWorld.Agent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Util.PeriodicDataBESA;
import wpsPeasantFamily.Agent.Guards.FromWorld.FromWorldGuard;
import wpsPeasantFamily.Agent.Guards.FromWorld.FromWorldMessage;
import wpsPeasantFamily.Agent.Guards.FromWorld.FromWorldMessageType;
import wpsWorld.Messages.WorldMessage;
import wpsControl.Agent.ControlCurrentDate;
import wpsWorld.Helper.WorldConfiguration;
import wpsWorld.layer.crop.CropLayer;
import wpsWorld.layer.crop.cell.CropCell;
import wpsWorld.layer.crop.cell.CropCellState;
import wpsWorld.layer.disease.DiseaseCellState;
import org.json.JSONObject;
import wpsViewer.Agent.wpsReport;

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
    public synchronized void funcExecGuard(EventBESA eventBESA) {
        WorldMessage worldMessage = (WorldMessage) eventBESA.getData();
        //wpsReport.info("🚩🚩🚩" + worldMessage);
        WorldState worldState = (WorldState) this.agent.getState();
        FromWorldMessage peasantMessage;
        CropCellState cropCellState;
        CropCell cropCellInfo;
        DiseaseCellState diseaseCellState;
        JSONObject cropDataJson;
        switch (worldMessage.getWorldMessageType()) {
            case CROP_INIT:
                //wpsReport.info("Start event, initialize first layers state");
                worldState.lazyUpdateCropsForDate(worldMessage.getDate());
                //wpsCurrentDate.getInstance().getDatePlusOneDayAndUpdate();
                peasantMessage = new FromWorldMessage(
                        FromWorldMessageType.CROP_INIT,
                        worldMessage.getPeasantAgentAlias(),
                        "CROP_INIT");
                peasantMessage.setDate(worldMessage.getDate());
                this.replyToPeasantAgent(
                        worldMessage.getPeasantAgentAlias(),
                        peasantMessage);
                break;
            case CROP_INFORMATION:
                //wpsReport.info("Message received:"
                //        + " Crop - " + worldMessage.getCropId()
                //        + " Information " + worldMessage.getPayload()
                //        + " Date: " + worldMessage.getDate());
                worldState.lazyUpdateCropsForDate(worldMessage.getDate());
                cropCellState = worldState.getCropLayer().getCropStateById(worldMessage.getCropId());
                cropCellInfo = worldState.getCropLayer().getCropCellById(worldMessage.getCropId());
                diseaseCellState = (DiseaseCellState) cropCellInfo.getDiseaseCell().getCellState();
                cropDataJson = new JSONObject(cropCellState);
                cropDataJson.put("disease", diseaseCellState.isInfected());
                cropDataJson.put("cropHarvestReady", cropCellInfo.isHarvestReady());
                peasantMessage = new FromWorldMessage(
                        FromWorldMessageType.CROP_INFORMATION_NOTIFICATION,
                        worldMessage.getPeasantAgentAlias(),
                        cropDataJson.toString());
                peasantMessage.setDate(worldMessage.getDate());
                this.replyToPeasantAgent(worldMessage.getPeasantAgentAlias(), peasantMessage);
                break;
            case CROP_OBSERVE:
                //wpsReport.info("Observing crops (lazy mode).... on date: "
                //        + worldMessage.getDate());
                worldState.getCropLayer().getAllCrops().forEach(cropCell -> {
                    if (((CropCellState) cropCell.getCellState()).isWaterStress()) {
                        this.notifyPeasantCropProblem(FromWorldMessageType.NOTIFY_CROP_WATER_STRESS,
                                cropCell.getAgentPeasantId(),
                                worldMessage.getDate());
                    }
                    if (((DiseaseCellState) cropCell.getDiseaseCell().getCellState()).isInfected()) {
                        this.notifyPeasantCropProblem(FromWorldMessageType.NOTIFY_CROP_DISEASE,
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
                //wpsReport.info("Irrigation on the crop, date: " + worldMessage.getDate());
                String cropIdToIrrigate = worldMessage.getCropId();
                String defaultWaterQuantity = this.worldConfig.getProperty("crop.defaultValuePerIrrigation");
                worldState.getCropLayer().addIrrigationEvent(
                        cropIdToIrrigate,
                        defaultWaterQuantity,
                        worldMessage.getDate());
                peasantMessage = new FromWorldMessage(
                        FromWorldMessageType.CROP_INFORMATION_NOTIFICATION,
                        worldMessage.getPeasantAgentAlias(),
                        "CROP_IRRIGATION");
                peasantMessage.setDate(worldMessage.getDate());
                this.replyToPeasantAgent(
                        worldMessage.getPeasantAgentAlias(),
                        peasantMessage);
                break;
            case CROP_PESTICIDE:
                // Adding the event of pesticide, will be reflected in the next layers execution
                //wpsReport.info("Adding pesticide to the crop, date: " + worldMessage.getDate());
                String cropIdToAddPesticide = worldMessage.getCropId();
                String defaultCropInsecticideCoverage = this.worldConfig.getProperty("disease.insecticideDefaultCoverage");
                String diseaseCellId = worldState.getCropLayer().getCropCellById(cropIdToAddPesticide).getDiseaseCell().getId();
                worldState.getDiseaseLayer().addInsecticideEvent(
                        diseaseCellId,
                        defaultCropInsecticideCoverage,
                        worldMessage.getDate());
                peasantMessage = new FromWorldMessage(
                        FromWorldMessageType.CROP_PESTICIDE,
                        worldMessage.getPeasantAgentAlias(),
                        defaultCropInsecticideCoverage + " " + diseaseCellId);
                peasantMessage.setDate(worldMessage.getDate());
                this.replyToPeasantAgent(worldMessage.getPeasantAgentAlias(), peasantMessage);
                break;
            case CROP_HARVEST:
                this.harvestCrop(worldState.getCropLayer());
                cropCellState = worldState.getCropLayer().getCropStateById(worldMessage.getCropId());
                cropCellInfo = worldState.getCropLayer().getCropCellById(worldMessage.getCropId());
                diseaseCellState = (DiseaseCellState) cropCellInfo.getDiseaseCell().getCellState();
                cropDataJson = new JSONObject(cropCellState);
                cropDataJson.put("disease", diseaseCellState.isInfected());
                cropDataJson.put("cropHarvestReady", cropCellInfo.isHarvestReady());
                peasantMessage = new FromWorldMessage(
                        FromWorldMessageType.CROP_HARVEST,
                        worldMessage.getPeasantAgentAlias(),
                        cropDataJson.toString());
                peasantMessage.setDate(worldMessage.getDate());
                this.replyToPeasantAgent(
                        worldMessage.getPeasantAgentAlias(),
                        peasantMessage);
                break;
        }
    }

    /**
     *
     * @param peasantAgentAlias
     * @param peasantMessage
     */
    public synchronized void replyToPeasantAgent(String peasantAgentAlias, FromWorldMessage peasantMessage) {
        try {
            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAlias(peasantAgentAlias);
            EventBESA event = new EventBESA(
                    FromWorldGuard.class.getName(),
                    peasantMessage);
            ah.sendEvent(event);
            //wpsReport.debug("Sent: " + peasantMessage.getPayload());
        } catch (ExceptionBESA e) {
            wpsReport.error(e.getMessage());
        }
    }

    /**
     *
     * @param messageType
     * @param aid
     * @param date
     */
    public synchronized void notifyPeasantCropProblem(FromWorldMessageType messageType, String agentAlias, String date) {
        try {
            //wpsReport.debug("AgentID: " + agentAlias);
            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAlias(agentAlias);
            FromWorldMessage peasantMessage = new FromWorldMessage(
                    messageType,
                    agentAlias,
                    null);
            peasantMessage.setDate(date);
            EventBESA event = new EventBESA(
                    FromWorldGuard.class.getName(),
                    peasantMessage);
            //wpsReport.debug("Sent: " + peasantMessage.getSimpleMessage());
            ah.sendEvent(event);
        } catch (ExceptionBESA e) {
            wpsReport.error(e.getMessage());
        }
    }

    /**
     *
     * @param agentAlias
     * @param date
     */
    public synchronized void notifyPeasantCropReadyToHarvest(String agentAlias, String date) {
        try {
            //wpsReport.debug("AgentID: " + agentAlias);
            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAlias(agentAlias);
            FromWorldMessage peasantMessage = new FromWorldMessage(
                    FromWorldMessageType.NOTIFY_CROP_READY_HARVEST,
                    agentAlias,
                    null);
            peasantMessage.setDate(date);
            EventBESA event = new EventBESA(
                    FromWorldGuard.class.getName(),
                    peasantMessage);
            //wpsReport.debug("Sent: " + peasantMessage.getSimpleMessage());
            ah.sendEvent(event);
        } catch (ExceptionBESA e) {
            wpsReport.error(e.getMessage());
        }
    }

    /**
     *
     * @param cropLayer
     */
    public synchronized void harvestCrop(CropLayer cropLayer) {
        cropLayer.writeCropData();
        try {
            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAid(this.agent.getAid());
            PeriodicDataBESA periodicDataBESA = new PeriodicDataBESA(PeriodicGuardBESA.STOP_CALL);
            EventBESA eventPeriodic = new EventBESA(
                    FromWorldGuard.class.getName(),
                    periodicDataBESA);
            ah.sendEvent(eventPeriodic);
        } catch (ExceptionBESA e) {
            wpsReport.error(e.getMessage());
        }
    }
}
