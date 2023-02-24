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

import BESA.Log.ReportBESA;
import wpsPeasant.Utils.PeasantActivityType;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author jairo
 */
public class PeasantBDIAgentBelieves implements Believes {

    private PeasantActivityType currentActivity;

    public PeasantBDIAgentBelieves(String alias) {
        //ReportBESA.debug(">>>>>>>> Nuevo " + alias);
        this.setCurrentActivity(PeasantActivityType.REST);
    }

    @Override
    public boolean update(InfoData infoData) {
        //ReportBESA.debug(">>>>>>>> Nuevo update");
        return true;
    }

    @Override
    public Believes clone() throws CloneNotSupportedException {
        return this;
    }

    public PeasantActivityType getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(PeasantActivityType currentActivity) {
        this.currentActivity = currentActivity;
    }

}
