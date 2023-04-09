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
package wpsPeasant.Goals.L4requirement;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import BESA.World.agent.WorldGuard;
import BESA.World.agents.messages.world.WorldMessage;
import static BESA.World.agents.messages.world.WorldMessageType.CROP_IRRIGATION;
import BESA.World.helper.DateSingleton;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.mapping.Believes;
import rational.mapping.Task;
import wpsPeasant.Agent.PeasantBDIAgentBelieves;
import wpsSimulator.wpsControl;

/**
 *
 * @author jairo
 */
public class IrrigateCropsTask extends Task {

    private boolean finished;

    /**
     *
     */
    public IrrigateCropsTask() {
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
        //believes.getPeasantProfile().increaseFarmReady();
        
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
                        CROP_IRRIGATION, 
                        "rice_1", 
                        currentDate, 
                        believes.getPeasantProfile().getProfileName());
                ev = new EventBESA(
                        WorldGuard.class.getName(), 
                        worldMessage);
                ah.sendEvent(ev);
                DateSingleton.getInstance().setCurrentDate(currentDate);
            }
            ReportBESA.debug("!--------> Actual " + 
                    DateSingleton.getInstance().getCurrentDate());
            
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
