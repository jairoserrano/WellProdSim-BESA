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

import wpsPeasant.Activities.L3Oportunity.PeasantFarmingTask;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import BESA.World.agent.WorldGuard;
import BESA.World.agents.messages.world.WorldMessage;
import static BESA.World.agents.messages.world.WorldMessageType.CROP_HARVEST;
import BESA.World.helper.DateSingleton;
import wpsPeasant.Agent.PeasantBDIAgentBelieves;
import wpsPeasant.Utils.PeasantActivityType;
import wpsSimulator.StartSimpleSimulator;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author jairo
 */
public class PeasantHarvestingTask extends Task {

    private boolean finished;

    public PeasantHarvestingTask() {
        ReportBESA.info("--- Task Recolecta Iniciada ---");
        this.finished = false;
    }

    @Override
    public void executeTask(Believes parameters) {
        ReportBESA.info("--- Execute Task Recolecta ---");

        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;

        try {
            AdmBESA adm = AdmBESA.getInstance();
            AgHandlerBESA ah = adm.getHandlerByAlias(StartSimpleSimulator.aliasWorldAgent);

            WorldMessage worldMessage = new WorldMessage(CROP_HARVEST, "rice_1", "01/06/2022", StartSimpleSimulator.aliasPeasantAgent);
            EventBESA ev = new EventBESA(WorldGuard.class.getName(), worldMessage);
            ah.sendEvent(ev);

            ReportBESA.warn(" ------------- TERMINÓ! -------------");
            
            believes.setCurrentActivity(PeasantActivityType.REST);
            this.setFinished(true);
            ReportBESA.debug("Actual " + DateSingleton.getInstance().getCurrentDate());
            StartSimpleSimulator.stopSimulation();

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
        ReportBESA.info("--- Interrupt Task PeasantHarvestingTask ---");
        PeasantBDIAgentBelieves blvs = (PeasantBDIAgentBelieves) believes;
        blvs.setCurrentActivity(PeasantActivityType.REST);
        this.finished = true;
    }

    @Override
    public void cancelTask(Believes believes) {
        ReportBESA.info("--- Cancel Task PeasantHarvestingTask ---");
        PeasantBDIAgentBelieves blvs = (PeasantBDIAgentBelieves) believes;
        blvs.setCurrentActivity(PeasantActivityType.REST);
        this.finished = true;
    }

    public boolean isExecuted() {
        ReportBESA.info("--- isExecuted Task PeasantHarvestingTask ---");
        return finished;
    }

    @Override
    public boolean checkFinish(Believes believes) {
        ReportBESA.info("--- checkFinish Task PeasantHarvestingTask ---");
        return isExecuted();
    }

}
