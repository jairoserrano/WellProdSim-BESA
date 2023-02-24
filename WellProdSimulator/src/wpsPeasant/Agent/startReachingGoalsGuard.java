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
package wpsPeasant.Agent;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Log.ReportBESA;

/**
 *
 * @author jairo
 */
public class startReachingGoalsGuard extends PeriodicGuardBESA{
    
    @Override
    public void funcPeriodicExecGuard(EventBESA event) {
        //ReportBESA.debug("------------------------------------------------> startReachingGoalsGuard --");
        ((PeasantBDIAgent)this.getAgent()).BDIPulse();
    }
    
}
