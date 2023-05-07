/**
 * ==========================================================================
 * __      __ _ __   ___  *    WellProdSim                                  *
 * \ \ /\ / /| '_ \ / __| *    @version 1.0                                 *
 *  \ V  V / | |_) |\__ \ *    @since 2023                                  *
 *   \_/\_/  | .__/ |___/ *                                                 *
 *           | |          *    @author Jairo Serrano                        *
 *           |_|          *    @author Enrique Gonzalez                     *
 * ==========================================================================
 * Social Simulator used to estimate productivity and well-being of peasant *
 * families. It is event oriented, high concurrency, heterogeneous time     *
 * management and emotional reasoning BDI.                                  *
 * ==========================================================================
 */
package wpsPeasantFamily.Tasks.L4SkillsResources;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import wpsWorld.Agent.WorldAgent;
import wpsWorld.Agent.WorldGuard;
import wpsWorld.Agent.WorldState;
import wpsWorld.Messages.WorldMessage;
import wpsWorld.Messages.WorldMessageType;
import wpsControl.Agent.wpsCurrentDate;
import wpsWorld.Helper.Hemisphere;
import wpsWorld.Helper.Soil;
import wpsWorld.Helper.WorldConfiguration;
import wpsWorld.layer.crop.CropLayer;
import wpsWorld.layer.crop.cell.rice.RiceCell;
import wpsWorld.layer.disease.DiseaseCell;
import wpsWorld.layer.disease.DiseaseLayer;
import wpsWorld.layer.evapotranspiration.EvapotranspirationLayer;
import wpsWorld.layer.rainfall.RainfallLayer;
import wpsWorld.layer.shortWaveRadiation.ShortWaveRadiationLayer;
import wpsWorld.layer.temperature.TemperatureLayer;
import rational.mapping.Believes;
import rational.mapping.Task;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsActivator.wpsConfig;
import java.util.UUID;
import wpsPeasantFamily.Utils.TimeConsumedBy;

/**
 *
 * @author jairo
 */
public class LookForALandTask extends Task {

    private static void setPerturbation(String arg) {
        WorldConfiguration worldConfiguration = WorldConfiguration.getPropsInstance();
        switch (arg) {
            case "disease" ->
                worldConfiguration.setPerturbations(true, false);
            case "course" ->
                worldConfiguration.setPerturbations(false, true);
            case "all" ->
                worldConfiguration.setPerturbations(true, true);
            default ->
                worldConfiguration.setPerturbations(false, false);
        }
    }

    private static WorldState buildWorldState(String rainfallFile, String agentAlias) {
        WorldConfiguration worldConfiguration = WorldConfiguration.getPropsInstance();
        ShortWaveRadiationLayer radiationLayer = new ShortWaveRadiationLayer(
                worldConfiguration.getProperty("data.radiation"),
                Hemisphere.SOUTHERN,
                9);
        TemperatureLayer temperatureLayer = new TemperatureLayer(
                worldConfiguration.getProperty("data.temperature"));
        EvapotranspirationLayer evapotranspirationLayer = new EvapotranspirationLayer(
                worldConfiguration.getProperty("data.evapotranspiration"));
        RainfallLayer rainfallLayer = new RainfallLayer(rainfallFile);
        DiseaseLayer diseaseLayer = new DiseaseLayer();
        DiseaseCell diseaseCellRice = new DiseaseCell("rice1DiseaseCell");
        diseaseLayer.addVertex(diseaseCellRice);
        CropLayer cropLayer = new CropLayer();
        cropLayer.addCrop(new RiceCell(
                1.05,
                1.2,
                0.7,
                1512,
                3330,
                100,
                0.9,
                0.2,
                Soil.SAND,
                true,
                diseaseCellRice,
                "rice_1",
                agentAlias));
        cropLayer.bindLayer("radiation", radiationLayer);
        cropLayer.bindLayer("rainfall", rainfallLayer);
        cropLayer.bindLayer("temperature", temperatureLayer);
        cropLayer.bindLayer("evapotranspiration", evapotranspirationLayer);
        return new WorldState(
                temperatureLayer,
                radiationLayer,
                cropLayer,
                diseaseLayer,
                evapotranspirationLayer,
                rainfallLayer);
    }

    private static void initialWorldStateInitialization(WorldAgent worldAgent, String agentAlias) {
        AdmBESA adm = AdmBESA.getInstance();
        WorldMessage worldMessage = new WorldMessage(
                WorldMessageType.CROP_INIT,
                null,
                wpsCurrentDate.getInstance().getCurrentDate(),
                agentAlias);
        try {
            AgHandlerBESA ah = adm.getHandlerByAid(worldAgent.getAid());
            EventBESA eventBesa = new EventBESA(WorldGuard.class.getName(), worldMessage);
            ah.sendEvent(eventBesa);
        } catch (ExceptionBESA e) {
            ReportBESA.error(e);
        }
    }

    private static WorldAgent buildWorld(String rainfallFile, String agentAlias, String aliasWorldAgent) {
        WorldState worldState = buildWorldState(rainfallFile, agentAlias);
        StructBESA structBESA = new StructBESA();
        structBESA.bindGuard(WorldGuard.class);
        try {
            WorldAgent worldAgent = new WorldAgent(aliasWorldAgent, worldState, structBESA);
            return worldAgent;
        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        }
        return null;
    }

    private static String getRainfallFile(String arg) {
        WorldConfiguration worldConfiguration = WorldConfiguration.getPropsInstance();
        String rainfallFile;
        switch (arg) {
            case "wet" ->
                rainfallFile = worldConfiguration.getProperty("data.rainfall.wet");
            case "dry" ->
                rainfallFile = worldConfiguration.getProperty("data.rainfall.dry");
            case "normal" ->
                rainfallFile = worldConfiguration.getProperty("data.rainfall");
            default ->
                rainfallFile = worldConfiguration.getProperty("data.rainfall");
        }
        return rainfallFile;
    }
    private boolean finished;

    /**
     *
     */
    public LookForALandTask() {
        //ReportBESA.info("");
        this.finished = false;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void executeTask(Believes parameters) {
        //ReportBESA.info("");
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        int uuid = (int) (UUID.randomUUID().getLeastSignificantBits() % 1000);

        // @TODO: setFarmName lo cambia el gobierno o el campesino
        believes.getPeasantProfile().setFarmName("Land_" + uuid);
        believes.getPeasantProfile().setFarmSize(2);
        believes.getPeasantProfile().setHousing(1);
        believes.getPeasantProfile().setServicesPresence(1);
        believes.getPeasantProfile().setHousingSize(1);
        believes.getPeasantProfile().setHousingLocation(1);
        believes.getPeasantProfile().setFarmDistance(1);

        // @TODO: setPlantingSeason lo cambia el reloj global
        believes.getPeasantProfile().useTime(TimeConsumedBy.LookForALand);

        // Set default values of peasant and world
        wpsConfig config = wpsConfig.getInstance();
        // Set world perturbation
        setPerturbation(config.getPerturbation());

        try {
            WorldAgent worldAgent = buildWorld(
                    getRainfallFile(config.getRainfallConditions()),
                    believes.getPeasantProfile().getProfileName(),
                    believes.getPeasantProfile().getFarmName()
            );
            //ReportBESA.info("Inicializando Mundo");
            initialWorldStateInitialization(
                    worldAgent,
                    believes.getPeasantProfile().getProfileName()
            );

            //ReportBESA.info("Configurado Mundo Simple para " + believes.getPeasantProfile().getProfileName());
            worldAgent.start();

        } catch (Exception ex) {
            ReportBESA.error(ex);
        }

        believes.getPeasantProfile().setFarm(true);
        ReportBESA.info("🥬 La familia campesina ya tiene tierra.");
        this.setFinished(true);

    }

    /**
     *
     * @return
     */
    public boolean isFinished() {
        //ReportBESA.info("");
        return finished;
    }

    /**
     *
     * @param finished
     */
    public void setFinished(boolean finished) {
        //ReportBESA.info("");
        this.finished = finished;
    }

    /**
     *
     * @param believes
     */
    @Override
    public void interruptTask(Believes believes) {
        //ReportBESA.info("");
        this.finished = true;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void cancelTask(Believes parameters) {
        //ReportBESA.info("");
        this.finished = true;
    }

    /**
     *
     * @return
     */
    public boolean isExecuted() {
        //ReportBESA.info("");
        return finished;
    }

    /**
     *
     * @param believes
     * @return
     */
    @Override
    public boolean checkFinish(Believes believes) {
        //ReportBESA.info("");
        return isExecuted();
    }
}
