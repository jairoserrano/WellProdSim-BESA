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
package wpsPeasant.Tasks.L3Development;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import wpsWorld.Agent.WorldGuard;
import wpsWorld.Messages.WorldMessage;
import wpsWorld.Messages.WorldMessageType;
import static wpsWorld.Messages.WorldMessageType.CROP_PESTICIDE;
import wpsControl.Agent.DateSingleton;
import rational.mapping.Believes;
import rational.mapping.Task;
import wpsPeasant.Agent.PeasantBDIAgentBelieves;

/**
 *
 * @author jairo
 */
public class ManagePestsTask extends Task {

    private boolean finished;

    /**
     *
     */
    public ManagePestsTask() {
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
        // @TODO: Cambiar a la venta real con el agente mundo
        
        try {
            AdmBESA adm = AdmBESA.getInstance();
            AgHandlerBESA ah = adm.getHandlerByAlias(
                    believes.getPeasantProfile().getFarmName());

            WorldMessage worldMessage;
            EventBESA ev;
            String currentDate = "";
            for (int i = 3; i < 7; i++) {
                currentDate = "01/0" + i + "/2022";
                worldMessage = new WorldMessage(
                        CROP_PESTICIDE, 
                        "rice_1", 
                        currentDate, 
                        believes.getPeasantProfile().getProfileName());
                ev = new EventBESA(
                        WorldGuard.class.getName(), 
                        worldMessage);
                ah.sendEvent(ev);
                DateSingleton.getInstance().setCurrentDate(currentDate);
            }
            ReportBESA.debug("!----> Actual " + 
                    DateSingleton.getInstance().getCurrentDate());
            
            believes.getPeasantProfile().setHarverstSeason(true);
            this.setFinished(true);

        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex);
        }
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
