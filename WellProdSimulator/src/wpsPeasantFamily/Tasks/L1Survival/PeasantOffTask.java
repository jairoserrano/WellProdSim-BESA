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
package wpsPeasantFamily.Tasks.L1Survival;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import rational.mapping.Believes;
import rational.mapping.Task;
import wpsActivator.wpsStart;
import wpsControl.Agent.ControlAgentGuard;
import wpsPeasantFamily.Agent.Guards.ToControlMessage;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsPeasantFamily.Data.CropCareType;
import wpsPeasantFamily.Data.PeasantActivityType;
import wpsPeasantFamily.Data.PeasantLeisureType;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class PeasantOffTask extends Task {

    private boolean finished;

    /**
     *
     */
    public PeasantOffTask() {
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
        
        believes.setCurrentActivity(PeasantActivityType.PTW);
        believes.setCurrentPeasantLeisureType(PeasantLeisureType.NONE);
        believes.setCurrentCropCare(CropCareType.NONE);    
        believes.setNewDay(false);
        believes.useTime(believes.getTimeLeftOnDay());
        
        if (believes.getCurrentDay() % wpsStart.DAYSTOCHECK == 0) {
            try {
                believes.setWeekBlock();
                wpsReport.warn(believes.getWeekBlock());
                AdmBESA adm = AdmBESA.getInstance();
                ToControlMessage toControlMessage = new ToControlMessage(
                        believes.getPeasantProfile().getPeasantFamilyAlias(),
                        believes.getCurrentDay(),
                        believes.getPeasantProfile().getHealth() <= 0
                );
                EventBESA eventBesa = new EventBESA(
                        ControlAgentGuard.class.getName(),
                        toControlMessage
                );
                AgHandlerBESA agHandler = adm.getHandlerByAlias(wpsStart.config.getControlAgentName());
                agHandler.sendEvent(eventBesa);
            } catch (ExceptionBESA ex) {
                wpsReport.error(ex);
            }
        }
        this.finished = true;
        this.setTaskFinalized();
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void interruptTask(Believes parameters) {
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        believes.setCurrentActivity(PeasantActivityType.PTW);
        this.setTaskFinalized();
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void cancelTask(Believes parameters) {
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        believes.setCurrentActivity(PeasantActivityType.PTW);
        this.setTaskFinalized();
    }

    /**
     *
     * @param parameters
     * @return
     */
    @Override
    public boolean checkFinish(Believes parameters) {
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        if (believes.isNewDay()) {
            return false;
        } else {
            return true;
        }
    }
}
