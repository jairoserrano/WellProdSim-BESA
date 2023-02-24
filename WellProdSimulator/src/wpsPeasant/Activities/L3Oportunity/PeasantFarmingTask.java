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
package wpsPeasant.Activities.L3Oportunity;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import BESA.World.agent.WorldGuard;
import BESA.World.agents.messages.world.WorldMessage;
import static BESA.World.agents.messages.world.WorldMessageType.CROP_INIT;
import wpsPeasant.Agent.PeasantBDIAgentBelieves;
import wpsPeasant.Utils.PeasantActivityType;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.mapping.Believes;
import rational.mapping.Task;
import wpsSimulator.StartSimpleSimulator;

/**
 *
 * @author jairo
 */
public class PeasantFarmingTask extends Task {

    private boolean finished;

    public PeasantFarmingTask() {
        ReportBESA.info("--- Task Siembra inicializada ---");
        this.finished = false;
    }

    @Override
    public void executeTask(Believes parameters) {
        ReportBESA.info("--- Execute Task PeasantFarmingTask ---");

        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
        believes.setCurrentActivity(PeasantActivityType.CULTIVATE);

        try {
            AdmBESA adm = AdmBESA.getInstance();
            AgHandlerBESA ah = adm.getHandlerByAlias(StartSimpleSimulator.aliasWorldAgent);

            WorldMessage worldMessage = new WorldMessage(CROP_INIT, "rice_1", "01/04/2022", StartSimpleSimulator.aliasPeasantAgent);
            EventBESA ev = new EventBESA(WorldGuard.class.getName(), worldMessage);
            ah.sendEvent(ev);

            believes.setCurrentActivity(PeasantActivityType.IRRIGATE);
            this.setFinished(true);

        } catch (ExceptionBESA ex) {
            Logger.getLogger(PeasantFarmingTask.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public void interruptTask(Believes believes) {
        ReportBESA.info("--- Interrupt Task PeasantFarmingTask ---");
        PeasantBDIAgentBelieves blvs = (PeasantBDIAgentBelieves) believes;
        blvs.setCurrentActivity(PeasantActivityType.REST);
        this.finished = true;
    }

    @Override
    public void cancelTask(Believes believes) {
        ReportBESA.info("--- Cancel Task PeasantFarmingTask ---");
        PeasantBDIAgentBelieves blvs = (PeasantBDIAgentBelieves) believes;
        blvs.setCurrentActivity(PeasantActivityType.REST);
        this.finished = true;
    }

    public boolean isExecuted() {
        ReportBESA.info("--- isExecuted Task PeasantFarmingTask ---");
        return finished;
    }

    @Override
    public boolean checkFinish(Believes believes) {
        ReportBESA.info("--- checkFinish Task PeasantFarmingTask ---");
        return isExecuted();
    }
}
