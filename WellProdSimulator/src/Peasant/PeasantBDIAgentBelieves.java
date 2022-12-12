package Peasant;

import BESA.Log.ReportBESA;
import Peasant.Utils.PeasantActivityType;
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
        this.setCurrentActivity(PeasantActivityType.RESTING);
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
