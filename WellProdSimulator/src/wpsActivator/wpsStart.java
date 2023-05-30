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
package wpsActivator;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import java.util.Enumeration;
import wpsControl.Agent.ControlAgent;
import wpsControl.Agent.wpsCurrentDate;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgent;
import wpsPeasantFamily.Agent.HeartBeatGuard;
import wpsPerturbation.Agent.wpsPerturbationAgent;
import wpsSociety.Agent.SocietyAgent;
import wpsSocietyBank.Agent.BankAgent;
import wpsSocietyMarket.MarketAgent;
import wpsViewer.Agent.wpsReport;

/**
 *
 */
public class wpsStart {

    private static int PLANID = 0;
    final private static double PASSWD = 0.91;
    // Tiempo simulado 1 minuto, más o menos 1 año con un beat de 10
    final private static int RTSIM = 5;

    /**
     *
     */
    public static String aliasSocietyAgent = "MariaLabaja";
    /**
     *
     */
    public static String aliasBankAgent = "BancoAgrario";
    /**
     *
     */
    public static String aliasMarketAgent = "LaPlazaAgraria";
    /**
     *
     */
    public static String aliasControlAgent = "wps";

    /**
     *
     */
    public static String aliasPeasantFamilyAgent01;
    
    /**
     *
     */
    public static String aliasPeasantFamilyAgent02;
    /**
     * 
     */
    public static wpsConfig config = wpsConfig.getInstance();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Set default values of peasant and world
        
        aliasPeasantFamilyAgent01 = config.getRegularFarmerProfile().getProfileName();
        aliasPeasantFamilyAgent02 = config.getProactiveFarmerProfile().getProfileName();

        // Set init date of simulation
        wpsCurrentDate.getInstance().setCurrentDate(config.getStartSimulationDate());

        try {
            wpsReport.info("Inicializando WellProdSimulator");
            
            // Control Agent
            ControlAgent controlAgent = ControlAgent.createAgent(aliasControlAgent, PASSWD);
            // Society Agent
            SocietyAgent societyAgent = SocietyAgent.createAgent(aliasSocietyAgent, PASSWD);
            // Bank Agent
            BankAgent bankAgent = BankAgent.createBankAgent(aliasBankAgent, PASSWD);
            // Markt Agent
            MarketAgent marketAgent = MarketAgent.createBankAgent(aliasMarketAgent, PASSWD);
            // Perturbator Agent
            wpsPerturbationAgent perturbationAgent = wpsPerturbationAgent.createPerturbationAgent(PASSWD);
            
            // @TODO: Regular and Proactive Peasant Agents
            PeasantFamilyBDIAgent peasantFamilyAgent01 = new PeasantFamilyBDIAgent(
                    aliasPeasantFamilyAgent01,
                    config.getRegularFarmerProfile()
            );
            PeasantFamilyBDIAgent peasantFamilyAgent02 = new PeasantFamilyBDIAgent(
                    aliasPeasantFamilyAgent02,
                    config.getProactiveFarmerProfile()
            );

            // Simulation Start
            startAllAgents(
                    controlAgent,
                    societyAgent,
                    bankAgent,
                    marketAgent,
                    perturbationAgent,
                    peasantFamilyAgent01,
                    peasantFamilyAgent02
            );

        } catch (ExceptionBESA ex) {
            wpsReport.error(ex);
        } catch (Exception ex) {
            wpsReport.error(ex);
        }
    }

    /**
     *
     * @return
     */
    public static int getPlanID() {
        return ++PLANID;
    }

    private static void startAllAgents(AgentBESA... A) throws ExceptionBESA {

        // start agent by agent
        for (AgentBESA agent : A) {
            agent.start();
            wpsReport.info(agent.getAlias() + " Started");
        }

        // Start Peasant Family Heart Beat
        try {
            AdmBESA adm = AdmBESA.getInstance();
            AgHandlerBESA agHandler = adm.getHandlerByAlias(aliasPeasantFamilyAgent01);
            EventBESA eventBesa = new EventBESA(HeartBeatGuard.class.getName(), null);
            agHandler.sendEvent(eventBesa);
            //wpsReport.debug("Enviado Beat");
        } catch (ExceptionBESA e) {
            wpsReport.error(e);
        }
        try {
            AdmBESA adm = AdmBESA.getInstance();
            AgHandlerBESA agHandler = adm.getHandlerByAlias(aliasPeasantFamilyAgent02);
            EventBESA eventBesa = new EventBESA(HeartBeatGuard.class.getName(), null);
            agHandler.sendEvent(eventBesa);
            //wpsReport.debug("Enviado Beat");
        } catch (ExceptionBESA e) {
            wpsReport.error(e);
        }
        /*
        AdmBESA adm = AdmBESA.getInstance();
        PeriodicDataBESA data = new PeriodicDataBESA(
                BEAT,
                PeriodicGuardBESA.START_PERIODIC_CALL);
        AgHandlerBESA agHandler = adm.getHandlerByAlias(aliasPeasantFamilyAgent);
        EventBESA eventBesa = new EventBESA(
                HeartBeatGuard.class.getName(),
                data);
        agHandler.sendEvent(eventBesa);
        */

        // cierra la ejecución completamente al cumplirse un tiempo
        try {
            Thread.sleep(RTSIM * 60 * 1000);
            stopSimulation();
            //System.exit(0);
        } catch (InterruptedException e) {
            wpsReport.error(e.getMessage());
        }

    }

    /**
     *
     * @throws ExceptionBESA
     */
    public static void stopSimulation() throws ExceptionBESA {
        AdmBESA adm = AdmBESA.getInstance();
        Enumeration enumeration = adm.getIdList();
        while (enumeration.hasMoreElements()) {
            adm.killAgent((String) enumeration.nextElement(), PASSWD);
        }
        adm.kill(0.09);

    }

}
