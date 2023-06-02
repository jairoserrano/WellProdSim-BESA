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
import wpsControl.Agent.DateHelper;
import wpsControl.Agent.wpsCurrentDate;
import wpsPeasantFamily.Agent.Guards.Believes.UpdateGuard;
import wpsPeasantFamily.Agent.Guards.Believes.UpdateMessage;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsPeasantFamily.Agent.UpdateBelievesPeasantFamilyAgent;
import wpsPeasantFamily.Data.MoneyOriginType;
import wpsPeasantFamily.Data.TimeConsumedBy;
import wpsPeasantFamily.Data.UpdateType;
import static wpsPeasantFamily.Data.SeasonType.PREPARATION;
import static wpsPeasantFamily.Data.UpdateType.DAILY_DISCOUNT;
import static wpsPeasantFamily.Data.UpdateType.SHOW_STATUS;
import static wpsSocietyBank.Agent.BankMessageType.ASK_CURRENT_TERM;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class DoVitalsTask extends Task {

    /**
     *
     */
    public DoVitalsTask() {
    }

    /**
     *
     * @param parameters
     */
    @Override
    public synchronized void executeTask(Believes parameters) {
        //wpsReport.info("⚙️⚙️⚙️");      
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        String peasantFamilyAlias = believes.getPeasantProfile().getPeasantFamilyAlias();

        UpdateBelievesPeasantFamilyAgent.send(
                peasantFamilyAlias,
                UpdateType.USE_TIME,
                TimeConsumedBy.valueOf(this.getClass().getSimpleName())
        );

        UpdateBelievesPeasantFamilyAgent.send(
                peasantFamilyAlias,
                UpdateType.USED_NEW_DAY
        );

        // Check time to plant
        if (DateHelper.differenceDaysBetweenTwoDates(
                wpsCurrentDate.getInstance().getCurrentDate(),
                believes.getPeasantProfile().getStartRiceSeason()) == 0) {
            UpdateBelievesPeasantFamilyAgent.send(
                    peasantFamilyAlias,
                    PREPARATION
            );
        }

        // Vitals about money and food
        if (believes.getPeasantProfile().getMoney()
                >= believes.getPeasantProfile().getPeasantFamilyMinimalVital() * 10) {
            UpdateBelievesPeasantFamilyAgent.send(
                    peasantFamilyAlias,
                    DAILY_DISCOUNT
            );
        } else {
            //Set ask for a Loan
            UpdateBelievesPeasantFamilyAgent.send(
                    peasantFamilyAlias,
                    MoneyOriginType.LOAN
            );
            //believes.getPeasantProfile().decreaseHealth();
        }

        // Check for the loan pay amount only on first day of month
        if (wpsCurrentDate.getInstance().isFirstDayOfMonth()) {
            UpdateBelievesPeasantFamilyAgent.send(
                    peasantFamilyAlias,
                    ASK_CURRENT_TERM
            );
        }
        showStatus(peasantFamilyAlias);
        checkWeek(parameters);
        this.setTaskFinalized();
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void interruptTask(Believes parameters) {
        //wpsReport.info("");
        this.setTaskFinalized();
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void cancelTask(Believes parameters) {
        this.setTaskFinalized();
    }

    /**
     *
     * @param parameters
     * @return
     */
    @Override
    public boolean checkFinish(Believes parameters) {
        ////wpsReport.info("");
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        if (believes.getPeasantProfile().isNewDay()) {
            return false;
        } else {
            return true;
        }
    }

    private void checkWeek(Believes parameters) {
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        if (believes.getPeasantProfile().getCurrentDay() % 7 == 0) {
            try {
                believes.getPeasantProfile().setWeekBlock();
                wpsReport.warn(believes.getPeasantProfile().getWeekBlock());
                AdmBESA adm = AdmBESA.getInstance();
                EventBESA eventBesa = new EventBESA(ControlAgentGuard.class.getName(), null);
                AgHandlerBESA agHandler = adm.getHandlerByAlias(wpsStart.config.getControlAgentName());
                agHandler.sendEvent(eventBesa);
            } catch (ExceptionBESA ex) {
                wpsReport.error(ex);
            }
        }
    }

    private void showStatus(String peasantFamilyAlias) {
        try {
            AdmBESA adm = AdmBESA.getInstance();
            UpdateMessage updateMessage = new UpdateMessage(SHOW_STATUS);
            EventBESA eventBesa = new EventBESA(UpdateGuard.class.getName(), updateMessage);
            AgHandlerBESA agHandler = adm.getHandlerByAlias(peasantFamilyAlias);
            agHandler.sendEvent(eventBesa);
        } catch (ExceptionBESA ex) {
            wpsReport.error(ex);
        }
    }
}
