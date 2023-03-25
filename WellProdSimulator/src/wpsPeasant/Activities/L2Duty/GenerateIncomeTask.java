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
package wpsPeasant.Activities.L2Duty;

import BESA.Log.ReportBESA;
import rational.mapping.Believes;
import rational.mapping.Task;
import wpsPeasant.Agent.PeasantBDIAgentBelieves;

/**
 *
 * @author jairo
 */
public class GenerateIncomeTask extends Task {

    private boolean finished;

    /**
     *
     */
    public GenerateIncomeTask() {
        ReportBESA.info("");
        this.finished = false;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void executeTask(Believes parameters) {
        ReportBESA.info("");
        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
        // @TODO: Cambiar a la venta real con el agente social market
        believes.getPeasantProfile().setHarvestedWeight(
                believes.getPeasantProfile().getHarvestedWeight() - 0.1);
        believes.getPeasantProfile().setSellDone(true);
        this.setFinished(true);
    }

    /**
     *
     * @return
     */
    public boolean isFinished() {
        ReportBESA.info("");
        return finished;
    }

    /**
     *
     * @param finished
     */
    public void setFinished(boolean finished) {
        ReportBESA.info("");
        this.finished = finished;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void interruptTask(Believes parameters) {
        ReportBESA.info("");
        this.setFinished(true);
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void cancelTask(Believes parameters) {
        ReportBESA.info("");
        this.setFinished(true);
    }

    /**
     *
     * @return
     */
    public boolean isExecuted() {
        ReportBESA.info("");
        return finished;
    }

    /**
     *
     * @param believes
     * @return
     */
    @Override
    public boolean checkFinish(Believes believes) {
        ReportBESA.info("");
        return isExecuted();
    }
}
