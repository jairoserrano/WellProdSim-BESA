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
package wpsPeasant.Tasks;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import wpsWorld.Agent.WorldGuard;
import wpsWorld.Messages.WorldMessage;
import static wpsWorld.Messages.WorldMessageType.CROP_INFORMATION;
import wpsControl.Agent.DateSingleton;
import rational.mapping.Believes;
import rational.mapping.Task;
import wpsPeasant.Agent.PeasantBDIAgentBelieves;
import wpsSimulator.wpsStart;
import wpsWorld.Messages.WorldMessageType;
import static wpsWorld.Messages.WorldMessageType.CROP_OBSERVE;

/**
 *
 * @author jairo
 */
public class CheckCropsTask extends Task {

    private boolean finished;

    /**
     *
     */
    public CheckCropsTask() {
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
        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
        // @TODO: Cambiar a la venta real con el agente social market
        //

        try {
            AdmBESA adm = AdmBESA.getInstance();
            AgHandlerBESA ah = adm.getHandlerByAlias(
                    believes.getPeasantProfile().getFarmName());

            WorldMessage worldMessage;
            worldMessage = new WorldMessage(
                    CROP_OBSERVE,
                    "rice_1",
                    DateSingleton.getInstance().getDatePlusOneMonthAndUpdate(),
                    believes.getPeasantProfile().getProfileName());
            EventBESA ev = new EventBESA(
                    WorldGuard.class.getName(),
                    worldMessage);
            ah.sendEvent(ev);
            DateSingleton.getInstance().setCurrentDate(
                    believes.getPeasantProfile().getInternalCurrentDate());

            ReportBESA.debug("->Actual Date: " 
                    + DateSingleton.getInstance().getCurrentDate());

            believes.getPeasantProfile().setHarverstSeason(true);
            this.setFinished(true);

        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        }
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