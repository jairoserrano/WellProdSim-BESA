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
package wpsPeasantFamily.Tasks.L5Social;

import BESA.Log.ReportBESA;
import rational.mapping.Believes;
import rational.mapping.Task;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsPeasantFamily.Utils.TimeConsumedBy;

/**
 *
 * @author jairo
 */
public class CommunicateTask extends Task {

    private boolean finished;

    /**
     *
     */
    public CommunicateTask() {
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
        believes.getPeasantProfile().increaseHealth();
        believes.getPeasantProfile().useLeisureOptions();
        believes.getPeasantProfile().increaseFamilyTimeAvailability();
        believes.getPeasantProfile().useTime(TimeConsumedBy.Communicate);
        this.setFinished(true);
        //ReportBESA.info("new healt" + believes.getPeasantProfile().getHealth());
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
     * @param parameters
     */
    @Override
    public void interruptTask(Believes parameters) {
        //ReportBESA.info("");
        this.setFinished(true);
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void cancelTask(Believes parameters) {
        //ReportBESA.info("");
        this.setFinished(true);
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
