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
package wpsPeasantFamily.Agent;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class HeartBeatGuard extends GuardBESA{
    
    /**
     *
     * @param event
     */
    @Override
    public void funcExecGuard(EventBESA event) {
        //wpsReport.debug("Recibido Beat");
        ((PeasantFamilyBDIAgent)this.getAgent()).BDIPulse();
    }
    
}
