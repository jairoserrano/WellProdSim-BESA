package WPSimulator;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import BESA.Util.PeriodicDataBESA;
import BESA.World.agent.WorldAgent;
import BESA.World.agent.WorldGuard;
import BESA.World.agent.WorldState;
import BESA.World.agents.messages.world.WorldMessage;
import BESA.World.agents.messages.world.WorldMessageType;
import BESA.World.helper.DateSingleton;
import BESA.World.helper.Hemisphere;
import BESA.World.helper.Soil;
import BESA.World.helper.WorldConfiguration;
import BESA.World.layer.crop.CropLayer;
import BESA.World.layer.crop.cell.rice.RiceCell;
import BESA.World.layer.disease.DiseaseCell;
import BESA.World.layer.disease.DiseaseLayer;
import BESA.World.layer.evapotranspiration.EvapotranspirationLayer;
import BESA.World.layer.rainfall.RainfallLayer;
import BESA.World.layer.shortWaveRadiation.ShortWaveRadiationLayer;
import BESA.World.layer.temperature.TemperatureLayer;
import Peasant.PeasantBDIAgent;
import Peasant.Utils.PeasantPurpose;
import Peasant.Utils.PeasantPurposeType;
import Peasant.startReachingGoalsGuard;

/**
 *
 * @author jairo
 */
public class StartSimpleSimulator {

    private static int PLANID = 0;
    public static String aliasPeasantAgent = "Campesino";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Set default values of peasant
        WPSExperimentConfig wpsExperimentConfig = new WPSExperimentConfig(args);

        // Set world perturbation
        setPerturbation(wpsExperimentConfig.getPerturbation());

        // Set init date of simulation
        DateSingleton.getInstance().setCurrentDate(wpsExperimentConfig.getStartSimulationDate());

        try {
            AdmBESA adm = AdmBESA.getInstance();
            ReportBESA.info("Inicializando WellProdSimulator");

            PeasantBDIAgent peasant = new PeasantBDIAgent(aliasPeasantAgent, new PeasantPurpose(PeasantPurposeType.FARMER));
            ReportBESA.info("Inicializando Agente Campesino");

            WorldAgent worldAgent = buildWorld(getRainfallFile(wpsExperimentConfig.getRainfallConditions()), peasant.getAid());
            ReportBESA.info("Inicializando Mundo");

            // Init world layers state message
            initialWorldStateInitialization(worldAgent);
            ReportBESA.info("Configurado Mundo Simple");

            // Simulation Start
            startAllAgents(peasant, worldAgent);

        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        } catch (Exception ex) {
            ReportBESA.error(ex);
        }
    }

    public static int getPlanID() {
        return ++PLANID;
    }

    private static void setPerturbation(String arg) {
        WorldConfiguration worldConfiguration = WorldConfiguration.getPropsInstance();
        switch (arg) {
            case "disease":
                worldConfiguration.setPerturbations(true, false);
                break;
            case "course":
                worldConfiguration.setPerturbations(false, true);
                break;
            case "all":
                worldConfiguration.setPerturbations(true, true);
                break;
            default:
                worldConfiguration.setPerturbations(false, false);
                break;
        }
    }

    private static String getRainfallFile(String arg) {
        WorldConfiguration worldConfiguration = WorldConfiguration.getPropsInstance();
        String rainfallFile;
        switch (arg) {
            case "wet":
                rainfallFile = worldConfiguration.getProperty("data.rainfall.wet");
                break;
            case "dry":
                rainfallFile = worldConfiguration.getProperty("data.rainfall.dry");
                break;
            case "normal":
                rainfallFile = worldConfiguration.getProperty("data.rainfall");
                break;
            default:
                rainfallFile = worldConfiguration.getProperty("data.rainfall");
                break;
        }
        return rainfallFile;
    }

    private static WorldAgent buildWorld(String rainfallFile, String agentId) {
        WorldState worldState = buildWorldState(rainfallFile, agentId);
        StructBESA structBESA = new StructBESA();
        structBESA.bindGuard(WorldGuard.class);
        try {
            WorldAgent worldAgent = new WorldAgent("MariaLaBaja", worldState, structBESA);
            return worldAgent;
        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        }
        return null;
    }

    private static WorldState buildWorldState(String rainfallFile, String agentId) {
        WorldConfiguration worldConfiguration = WorldConfiguration.getPropsInstance();
        ShortWaveRadiationLayer radiationLayer = new ShortWaveRadiationLayer(
                worldConfiguration.getProperty("data.radiation"),
                Hemisphere.SOUTHERN,
                9);
        TemperatureLayer temperatureLayer = new TemperatureLayer(worldConfiguration.getProperty("data.temperature"));
        EvapotranspirationLayer evapotranspirationLayer = new EvapotranspirationLayer(worldConfiguration.getProperty("data.evapotranspiration"));
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
                agentId
        ));
        cropLayer.bindLayer("radiation", radiationLayer);
        cropLayer.bindLayer("rainfall", rainfallLayer);
        cropLayer.bindLayer("temperature", temperatureLayer);
        cropLayer.bindLayer("evapotranspiration", evapotranspirationLayer);
        return new WorldState(temperatureLayer, radiationLayer, cropLayer, diseaseLayer, evapotranspirationLayer, rainfallLayer);
    }

    private static void initialWorldStateInitialization(WorldAgent worldAgent) {
        AdmBESA adm = AdmBESA.getInstance();
        WorldMessage worldMessage = new WorldMessage(
                WorldMessageType.CROP_INIT,
                null,
                DateSingleton.getInstance().getCurrentDate(),
                null);
        try {
            AgHandlerBESA ah = adm.getHandlerByAid(worldAgent.getAid());
            EventBESA eventBesa = new EventBESA(WorldGuard.class.getName(), worldMessage);
            ah.sendEvent(eventBesa);
        } catch (Exception e) {
            ReportBESA.error(e);
        }
    }

    private static void startAllAgents(AgentBESA... A) throws ExceptionBESA {

        for (AgentBESA agent : A) {
            agent.start();
            ReportBESA.info(agent.getAlias() + " Started");
        }

        AdmBESA adm = AdmBESA.getInstance();
        PeriodicDataBESA data = new PeriodicDataBESA(1000, PeriodicGuardBESA.START_PERIODIC_CALL);
        AgHandlerBESA agHandler = adm.getHandlerByAlias(aliasPeasantAgent);
        //EventBESA eventBesa = new EventBESA(startReachingGoalsSimpleGuard.class.getName(), data);
        EventBESA eventBesa = new EventBESA(startReachingGoalsGuard.class.getName(), data);
        agHandler.sendEvent(eventBesa);

    }

    public static void stopSimulation() throws ExceptionBESA {
        AdmBESA adm = AdmBESA.getInstance();
        AgHandlerBESA agHandler;
        adm.killAgent(adm.getHandlerByAlias("Campesino").getAgId(), 0.91);
        adm.killAgent(adm.getHandlerByAlias("MariaLaBaja").getAgId(), 0.91);
        adm.kill(0.09);
        System.exit(0);
    }

}
