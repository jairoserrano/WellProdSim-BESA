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
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Util.PeriodicDataBESA;
import wpsControl.Agent.wpsCurrentDate;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgent;
import wpsPeasantFamily.Agent.PeasantFamilyHeartBeatGuard;
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
    final private static int BEAT = 50;
    // Tiempo simulado 1 minuto, más o menos 1 año con un beat de 10
    final private static int RTSIM = 1;

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
    public static String aliasPeasantFamilyAgent;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Set default values of peasant and world
        wpsConfig config = wpsConfig.getInstance();
        aliasPeasantFamilyAgent = config.getRegularFarmerProfile().getProfileName();

        wpsReport.info(aliasPeasantFamilyAgent);
        // Set init date of simulation
        wpsCurrentDate.getInstance().setCurrentDate(config.getStartSimulationDate());

        try {
            wpsReport.info("Inicializando WellProdSimulator");

            // @TODO: Regular Peasant Agent
            PeasantFamilyBDIAgent peasantFamilyAgent = new PeasantFamilyBDIAgent(
                    aliasPeasantFamilyAgent,
                    config.getRegularFarmerProfile()
            );

            // Society Agent
            SocietyAgent societyAgent = SocietyAgent.createAgent(aliasBankAgent, PASSWD);
            // Bank Agent
            BankAgent bankAgent = BankAgent.createBankAgent(aliasBankAgent, PASSWD);
            // Markt Agent
            MarketAgent marketAgent = MarketAgent.createBankAgent(aliasMarketAgent, PASSWD);
            // Perturbator Agent
            wpsPerturbationAgent perturbationAgent = wpsPerturbationAgent.createPerturbationAgent(PASSWD);

            // Simulation Start
            startAllAgents(
                    peasantFamilyAgent,
                    societyAgent,
                    bankAgent,
                    marketAgent,
                    perturbationAgent
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
        AdmBESA adm = AdmBESA.getInstance();
        PeriodicDataBESA data = new PeriodicDataBESA(
                BEAT,
                PeriodicGuardBESA.START_PERIODIC_CALL);
        AgHandlerBESA agHandler = adm.getHandlerByAlias(aliasPeasantFamilyAgent);
        EventBESA eventBesa = new EventBESA(
                PeasantFamilyHeartBeatGuard.class.getName(),
                data);
        agHandler.sendEvent(eventBesa);

        // cierra la ejecución completamente al cumplirse un tiempo
        try {
            Thread.sleep(RTSIM * 60 * 1000);
            System.exit(0);
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
        //AgHandlerBESA agHandler;
        adm.killAgent(adm.getHandlerByAlias(aliasPeasantFamilyAgent).getAgId(), PASSWD);
        adm.killAgent(adm.getHandlerByAlias(aliasBankAgent).getAgId(), PASSWD);
        adm.killAgent(adm.getHandlerByAlias(aliasSocietyAgent).getAgId(), PASSWD);
        adm.killAgent(adm.getHandlerByAlias(aliasMarketAgent).getAgId(), PASSWD);
        adm.killAgent(adm.getHandlerByAlias("Land_" + aliasPeasantFamilyAgent).getAgId(), PASSWD);
        adm.kill(0.09);

    }

}
