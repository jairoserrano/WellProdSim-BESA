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
package wpsSimulator;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import BESA.Util.PeriodicDataBESA;
import wpsPeasant.Agent.PeasantBDIAgent;
import wpsPeasant.Agent.startReachingGoalsGuard;
import wpsSociety.Agent.SocietyAgent;
import wpsSociety.Agent.SocietyAgentGuardCreation;
import wpsSociety.Agent.SocietyAgentState;

/**
 *
 * @author jairo
 */
public class wpsStart {

    private static int PLANID = 0;
    final private static double PASSWD = 0.91;
    final private static int BEAT = 500;

    /**
     *
     */
    public static String aliasSocietyAgent = "MariaLabaja";

    /**
     *
     */
    public static String aliasPeasantAgent;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Set default values of peasant and world
        wpsConfig config = wpsConfig.getInstance();
        aliasPeasantAgent = config.getRegularFarmerProfile().getProfileName();

        try {
            //AdmBESA adm = AdmBESA.getInstance();
            ReportBESA.info("Inicializando WellProdSimulator");

            // @TODO: Regular Peasant Agent
            PeasantBDIAgent peasant = new PeasantBDIAgent(
                    aliasPeasantAgent,
                    config.getRegularFarmerProfile()
            );
            ReportBESA.info("Inicializando Agente RegularFarmerProfile");

            // Society Agent
            StructBESA societyAgentStruct = new StructBESA();
            try {
                societyAgentStruct.bindGuard(
                        "ComportamientoJugador",
                        SocietyAgentGuardCreation.class);
            } catch (ExceptionBESA ex) {
                ReportBESA.error(ex);
            }
            SocietyAgentState societyAgentState = new SocietyAgentState();
            SocietyAgent societyAgent = new SocietyAgent(
                    aliasSocietyAgent,
                    societyAgentState,
                    societyAgentStruct,
                    PASSWD);

            // Simulation Start
            startAllAgents(peasant, societyAgent);

        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        } catch (Exception ex) {
            ReportBESA.error(ex);
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
            ReportBESA.info(agent.getAlias() + " Started");
        }

        AdmBESA adm = AdmBESA.getInstance();
        PeriodicDataBESA data = new PeriodicDataBESA(
                BEAT,
                PeriodicGuardBESA.START_PERIODIC_CALL);
        AgHandlerBESA agHandler = adm.getHandlerByAlias(aliasPeasantAgent);
        EventBESA eventBesa = new EventBESA(
                startReachingGoalsGuard.class.getName(),
                data);
        agHandler.sendEvent(eventBesa);

    }

    /**
     *
     * @throws ExceptionBESA
     */
    public static void stopSimulation() throws ExceptionBESA {
        AdmBESA adm = AdmBESA.getInstance();
        //AgHandlerBESA agHandler;
        adm.killAgent(adm.getHandlerByAlias(aliasPeasantAgent).getAgId(), PASSWD);
        //adm.killAgent(adm.getHandlerByAlias(aliasWorldAgent).getAgId(), PASSWD);
        adm.killAgent(adm.getHandlerByAlias(aliasSocietyAgent).getAgId(), PASSWD);
        adm.kill(0.09);
        System.exit(0);
    }

}
