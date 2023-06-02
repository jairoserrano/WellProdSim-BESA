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
package wpsPeasantFamily.Agent.Guards.Believes;

import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsPeasantFamily.Data.UpdateType;
import static wpsPeasantFamily.Data.UpdateType.CROP_CHECKED_TODAY;
import static wpsPeasantFamily.Data.UpdateType.DAILY_DISCOUNT;
import static wpsPeasantFamily.Data.UpdateType.MONEY_ORIGIN;
import static wpsPeasantFamily.Data.UpdateType.NEW_DAY;
import static wpsPeasantFamily.Data.UpdateType.SHOW_STATUS;
import static wpsPeasantFamily.Data.UpdateType.USED_NEW_DAY;
import static wpsPeasantFamily.Data.UpdateType.USE_TIME;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class UpdateGuard extends GuardBESA {

    /**
     *
     * @param event
     */
    @Override
    public void funcExecGuard(EventBESA event) {
        UpdateMessage updateMessage = (UpdateMessage) event.getData();
        StateBDI state = (StateBDI) this.agent.getState();
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) state.getBelieves();
        UpdateType updateType = updateMessage.getUpdateType();

        switch (updateType) {
            case SHOW_STATUS:
                wpsReport.info(believes.getPeasantProfile());
                break;
            case NEW_DAY:
                believes.getPeasantProfile().makeNewDay();
                break;
            case USED_NEW_DAY:
                believes.getPeasantProfile().setNewDayFalse();
                break;
            case USE_TIME:
                believes.getPeasantProfile().useTime(updateMessage.getUsedTime());
                break;            
            case CROP_CHECKED_TODAY:
                believes.getPeasantProfile().setCropCheckedToday(true);
                break;
            case MONEY_ORIGIN:
                believes.getPeasantProfile().setCurrentMoneyOrigin(updateMessage.getMoneyOriginType());
                break;
            case DAILY_DISCOUNT:
                believes.getPeasantProfile().discountDailyMoney();
                break;           
            case CHANGE_SEASON:
                break;
        }
    }

}
