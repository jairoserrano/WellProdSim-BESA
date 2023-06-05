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
package wpsControl.Agent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import java.util.logging.Level;
import java.util.logging.Logger;
import wpsActivator.wpsStart;
import wpsPeasantFamily.Agent.Guards.FromControlGuard;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class ControlAgentGuard extends GuardBESA {

    /**
     *
     * @param event
     */
    @Override
    public void funcExecGuard(EventBESA event) {
        wpsReport.warn("Llegada al ControlAgent desde " + event.getSenderAgId());

        ((ControlAgentState) this.getAgent().getState()).increaseActiveAgents();
        
        wpsReport.warn("Completados " + ((ControlAgentState) this.getAgent().getState()).getActiveAgents());

        if (((ControlAgentState) this.getAgent().getState()).getActiveAgentsReady()) {
            
            try {
                for (int i = 1; i <= wpsStart.peasantFamiliesAgents; i++) {
                    AdmBESA adm = AdmBESA.getInstance();
                    EventBESA eventBesa = new EventBESA(FromControlGuard.class.getName(), null);
                    AgHandlerBESA agHandler = adm.getHandlerByAlias("PeasantFamily_" + i);
                    agHandler.sendEvent(eventBesa);
                }
            } catch (ExceptionBESA ex) {
                Logger.getLogger(wpsStart.class.getName()).log(Level.SEVERE, null, ex);
            }

            ((ControlAgentState) this.getAgent().getState()).resetActiveAgents();
            String currentDate = wpsCurrentDate.getInstance().getDatePlusXDaysAndUpdate(wpsStart.daysToCheck);
        }

    }

}
