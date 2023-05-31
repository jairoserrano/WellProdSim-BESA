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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import wpsControl.Agent.ControlAgent;
import wpsControl.Agent.wpsCurrentDate;
import wpsPeasantFamily.Agent.Guards.StatusGuard;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgent;
import wpsPeasantFamily.Agent.HeartBeatGuard;
import wpsPerturbation.Agent.PerturbationAgent;
import wpsSociety.Agent.SocietyAgent;
import wpsSocietyBank.Agent.BankAgent;
import wpsSocietyMarket.MarketAgent;
import wpsViewer.Agent.wpsReport;
import wpsViewer.Agent.wpsViewerAgent;

/**
 *
 */
public class wpsStart {

    private static int PLANID = 0;
    final private static double PASSWD = 0.91;
    public static wpsConfig config = wpsConfig.getInstance();
    private static int peasantFamiliesAgents = 2;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        printHeader();

        // Set init date of simulation
        wpsCurrentDate.getInstance().setCurrentDate(config.getStartSimulationDate());

        List<PeasantFamilyBDIAgent> peasantFamilyBDIAgents = new ArrayList<>();

        try {

            ControlAgent controlAgent = ControlAgent.createAgent(config.getControlAgentName(), PASSWD);
            SocietyAgent societyAgent = SocietyAgent.createAgent(config.getSocietyAgentName(), PASSWD);
            BankAgent bankAgent = BankAgent.createBankAgent(config.getBankAgentName(), PASSWD);
            MarketAgent marketAgent = MarketAgent.createAgent(config.getMarketAgentName(), PASSWD);
            PerturbationAgent perturbationAgent = PerturbationAgent.createAgent(PASSWD);
            wpsViewerAgent viewerAgent = wpsViewerAgent.createAgent(config.getViewerAgentName(), PASSWD);

            // @TODO: Regular and Proactive Peasant Agents
            for (int i = 0; i < peasantFamiliesAgents; i++) {
                PeasantFamilyBDIAgent peasantFamilyBDIAgent = new PeasantFamilyBDIAgent(
                        config.getUniqueFarmerName(),
                        config.getFarmerProfile()
                );
                peasantFamilyBDIAgents.add(peasantFamilyBDIAgent);
            }

            // Simulation Start
            startAllAgents(
                    peasantFamilyBDIAgents,
                    viewerAgent,
                    controlAgent,
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

    private static void startAllAgents(List<PeasantFamilyBDIAgent> peasantFamilies, AgentBESA... A) throws ExceptionBESA {

        try {
            // Starting general simulation agents
            for (AgentBESA agent : A) {
                agent.start();
                wpsReport.info(agent.getAlias() + " Started");
            }
            // Starting families agents
            for (PeasantFamilyBDIAgent peasantFamily : peasantFamilies) {
                peasantFamily.start();
                wpsReport.info(peasantFamily.getAlias() + " Started");
            }
            // first heart beat to families
            for (int i = 1; i <= peasantFamiliesAgents; i++) {
                AdmBESA adm = AdmBESA.getInstance();
                EventBESA eventBesa = new EventBESA(HeartBeatGuard.class.getName(), null);
                AgHandlerBESA agHandler = adm.getHandlerByAlias("PeasantFamily_" + i);
                agHandler.sendEvent(eventBesa);
            }

        } catch (ExceptionBESA e) {
            wpsReport.error(e);
        }

        stopSimulation();

    }

    /**
     *
     * @throws ExceptionBESA
     */
    public static void stopSimulation() throws ExceptionBESA {

        // Closing simulation after 5 minutes
        try {
            Thread.sleep((60 * 1) * 1000);
            
            getStatus();
            
            AdmBESA adm = AdmBESA.getInstance();
            Enumeration enumeration = adm.getIdList();
            while (enumeration.hasMoreElements()) {
                adm.killAgent((String) enumeration.nextElement(), PASSWD);
            }
            adm.kill(0.09);
            wpsReport.info("Simulation finished.\n\n\n\n");
            System.exit(0);
        } catch (InterruptedException e) {
            wpsReport.error(e.getMessage());
        }

    }
    
    public static void getStatus(){
        // first heart beat to families
        try {
            for (int i = 1; i <= peasantFamiliesAgents; i++) {
                AdmBESA adm = AdmBESA.getInstance();
                EventBESA eventBesa = new EventBESA(StatusGuard.class.getName(), null);
                AgHandlerBESA agHandler = adm.getHandlerByAlias("PeasantFamily_" + i);
                agHandler.sendEvent(eventBesa);
                Thread.sleep(2000);
            }
        } catch (ExceptionBESA ex) {
            Logger.getLogger(wpsStart.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(wpsStart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printHeader() {

        wpsReport.info(
                "\n\n/**\n"
                + " * ==========================================================================\n"
                + " * __      __ _ __   ___  *    WellProdSim                                  *\n"
                + " * \\ \\ /\\ / /| '_ \\ / __| *    @version 1.0                                 *\n"
                + " *  \\ V  V / | |_) |\\__ \\ *    @since 2023                                  *\n"
                + " *   \\_/\\_/  | .__/ |___/ *                                                 *\n"
                + " *           | |          *    @author Jairo Serrano                        *\n"
                + " *           |_|          *    @author Enrique Gonzalez                     *\n"
                + " * ==========================================================================\n"
                + " * Social Simulator used to estimate productivity and well-being of peasant *\n"
                + " * families. It is event oriented, high concurrency, heterogeneous time     *\n"
                + " * management and emotional reasoning BDI.                                  *\n"
                + " * ==========================================================================\n"
                + " */\n\n"
        );
    }

}
