package Peasant.Activities;

import Peasant.Activities.PeasantFarmingTask;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import BESA.World.agent.WorldGuard;
import BESA.World.agents.messages.world.WorldMessage;
import BESA.World.agents.messages.world.WorldMessageType;
import static BESA.World.agents.messages.world.WorldMessageType.CROP_HARVEST;
import static BESA.World.agents.messages.world.WorldMessageType.CROP_IRRIGATION;
import BESA.World.helper.DateSingleton;
import Peasant.PeasantBDIAgentBelieves;
import Peasant.Utils.PeasantActivityType;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author jairo
 */
public class PeasantIrrigatingTask extends Task {

    private boolean finished;

    public PeasantIrrigatingTask() {
        ReportBESA.info("--- Task Riego Iniciada ---");
        this.finished = false;
    }

    @Override
    public void executeTask(Believes parameters) {
        ReportBESA.info("--- Execute Task Riego ---");

        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;

        try {
            AdmBESA adm = AdmBESA.getInstance();
            AgHandlerBESA ah = adm.getHandlerByAlias("MariaLaBaja");

            WorldMessage worldMessage;
            EventBESA ev;
            String currentDate = "";
            for (int i = 3; i < 7; i++) {
                currentDate = "01/0" + i + "/2022";
                worldMessage = new WorldMessage(CROP_IRRIGATION, "rice_1", currentDate, "Campesino");
                ev = new EventBESA(WorldGuard.class.getName(), worldMessage);
                ah.sendEvent(ev);
                DateSingleton.getInstance().setCurrentDate(currentDate);
            }
            ReportBESA.debug("!--------> Actual " + DateSingleton.getInstance().getCurrentDate());
            this.setFinished(true);
            believes.setCurrentActivity(PeasantActivityType.HARVESTING);

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
