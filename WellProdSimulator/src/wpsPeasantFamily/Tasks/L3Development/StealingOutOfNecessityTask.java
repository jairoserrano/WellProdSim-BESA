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
package wpsPeasantFamily.Tasks.L3Development;

import rational.mapping.Believes;
import rational.mapping.Task;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsPeasantFamily.Data.MoneyOriginType;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class StealingOutOfNecessityTask extends Task {

    private boolean finished;

    /**
     *
     */
    public StealingOutOfNecessityTask() {
        this.finished = false;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void executeTask(Believes parameters) {
        wpsReport.info("⚙️⚙️⚙️");
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        believes.useTime(believes.getTimeLeftOnDay());
        believes.increaseRoberyAccount();
        if (Math.random() < 0.4) {
            believes.getPeasantProfile().increaseMoney(65000);
        }else{
            believes.getPeasantProfile().increaseMoney(130000);
        }
        if (Math.random() < 0.6) {
            believes.getPeasantProfile().decreaseHealth();
        }
        believes.setCurrentMoneyOrigin(MoneyOriginType.ROBERY);
        this.finished = true;
        //this.setTaskFinalized();
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void interruptTask(Believes parameters) {
        //wpsReport.info("");
        ((PeasantFamilyBDIAgentBelieves) parameters).setCurrentMoneyOrigin(MoneyOriginType.ROBERY);
        this.setTaskFinalized();
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void cancelTask(Believes parameters) {
        ((PeasantFamilyBDIAgentBelieves) parameters).setCurrentMoneyOrigin(MoneyOriginType.ROBERY);
        this.setTaskFinalized();
    }

    /**
     *
     * @param parameters
     * @return
     */
    @Override
    public boolean checkFinish(Believes parameters) {
        return this.finished;
    }
}
