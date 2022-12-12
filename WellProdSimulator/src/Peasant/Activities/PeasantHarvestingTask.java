package Peasant.Activities;

import Peasant.Activities.PeasantFarmingTask;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import BESA.World.agent.WorldGuard;
import BESA.World.agents.messages.world.WorldMessage;
import static BESA.World.agents.messages.world.WorldMessageType.CROP_HARVEST;
import BESA.World.helper.DateSingleton;
import Peasant.PeasantBDIAgentBelieves;
import Peasant.Utils.PeasantActivityType;
import WPSimulator.StartSimpleSimulator;
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
            AgHandlerBESA ah = adm.getHandlerByAlias("MariaLaBaja");

            WorldMessage worldMessage = new WorldMessage(CROP_HARVEST, "rice_1", "01/06/2022", "Campesino");
            EventBESA ev = new EventBESA(WorldGuard.class.getName(), worldMessage);
            ah.sendEvent(ev);

            ReportBESA.warn(" ------------- TERMINÓ! -------------");
            
            believes.setCurrentActivity(PeasantActivityType.RESTING);
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
        blvs.setCurrentActivity(PeasantActivityType.RESTING);
        this.finished = true;
    }

    @Override
    public void cancelTask(Believes believes) {
        ReportBESA.info("--- Cancel Task PeasantHarvestingTask ---");
        PeasantBDIAgentBelieves blvs = (PeasantBDIAgentBelieves) believes;
        blvs.setCurrentActivity(PeasantActivityType.RESTING);
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
