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

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import wpsWorld.Agent.WorldGuard;
import wpsWorld.Messages.WorldMessage;
import static wpsWorld.Messages.WorldMessageType.CROP_IRRIGATION;
import wpsControl.Agent.wpsCurrentDateSingleton;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.mapping.Believes;
import rational.mapping.Task;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsActivator.wpsStart;
import wpsPeasantFamily.Utils.TimeConsumedBy;

/**
 *
 * @author jairo
 */
public class ObtainToolsTask extends Task {

    private boolean finished;

    /**
     *
     */
    public ObtainToolsTask() {
        ////ReportBESA.info("");
        this.finished = false;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void executeTask(Believes parameters) {
        ////ReportBESA.info("");
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        // @TODO: Cambiar a la venta real con el agente social market
        //believes.getPeasantProfile().increaseFarmReady();
        believes.getPeasantProfile().useTime(TimeConsumedBy.ObtainTools);
        this.setFinished(true);
    }

    /**
     *
     * @return
     */
    public boolean isFinished() {
        ////ReportBESA.info("");
        return finished;
    }

    /**
     *
     * @param finished
     */
    public void setFinished(boolean finished) {
        ////ReportBESA.info("");
        this.finished = finished;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void interruptTask(Believes parameters) {
        ////ReportBESA.info("");
        this.setFinished(true);
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void cancelTask(Believes parameters) {
        ////ReportBESA.info("");
        this.setFinished(true);
    }

    /**
     *
     * @return
     */
    public boolean isExecuted() {
        ////ReportBESA.info("");
        return finished;
    }

    /**
     *
     * @param believes
     * @return
     */
    @Override
    public boolean checkFinish(Believes believes) {
        ////ReportBESA.info("");
        return isExecuted();
    }
}
