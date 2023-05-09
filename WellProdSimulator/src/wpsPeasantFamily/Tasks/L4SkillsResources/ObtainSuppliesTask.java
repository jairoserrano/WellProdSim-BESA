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
package wpsPeasantFamily.Tasks.L4SkillsResources;

import rational.mapping.Believes;
import rational.mapping.Task;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsPeasantFamily.Utils.TimeConsumedBy;

/**
 *
 * @author jairo
 */
public class ObtainSuppliesTask extends Task {

    private boolean finished;

    /**
     *
     */
    public ObtainSuppliesTask() {
        ////wpsReport.info("");
        this.finished = false;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void executeTask(Believes parameters) {
        ////wpsReport.info("");
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        // @TODO: Cambiar a la venta real con el agente social market
        //believes.getPeasantProfile().increaseFarmReady();
        believes.getPeasantProfile().useTime(TimeConsumedBy.ObtainSupplies);
        this.setFinished(true);
    }

    /**
     *
     * @return
     */
    public boolean isFinished() {
        ////wpsReport.info("");
        return finished;
    }

    /**
     *
     * @param finished
     */
    public void setFinished(boolean finished) {
        ////wpsReport.info("");
        this.finished = finished;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void interruptTask(Believes parameters) {
        ////wpsReport.info("");
        this.setFinished(true);
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void cancelTask(Believes parameters) {
        ////wpsReport.info("");
        this.setFinished(true);
    }

    /**
     *
     * @return
     */
    public boolean isExecuted() {
        ////wpsReport.info("");
        return finished;
    }

    /**
     *
     * @param believes
     * @return
     */
    @Override
    public boolean checkFinish(Believes believes) {
        ////wpsReport.info("");
        return isExecuted();
    }
}
