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
import wpsControl.Agent.DateHelper;
import wpsControl.Agent.wpsCurrentDate;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsPeasantFamily.Utils.TimeConsumedBy;
import wpsSocietyBank.Agent.BankAgentGuard;
import wpsSocietyBank.Agent.BankMessage;
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
        wpsReport.info("⚙️⚙️⚙️");      
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        believes.getPeasantProfile().setNewDayFalse();
        believes.getPeasantProfile().useTime(TimeConsumedBy.DoVitalsTask);
        
        if (DateHelper.differenceDaysBetweenTwoDates(
                wpsCurrentDate.getInstance().getCurrentDate(),
                believes.getPeasantProfile().getStartRiceSeason()) == 0) {
            believes.getPeasantProfile().setPreparationSeason(true);
        }

        // Vitals about money and food
        if (believes.getPeasantProfile().getMoney()
                >= believes.getPeasantProfile().getPeasantFamilyMinimalVital()) {
            believes.getPeasantProfile().discountDailyMoney();
        } else {
            believes.getPeasantProfile().setFormalLoanSeason(true);
            believes.getPeasantProfile().decreaseHealth();
        }

        // Check for the loan pay amount only on first day of month
        if (wpsCurrentDate.getInstance().isFirstDayOfMonth()) {
            try {
                AdmBESA adm = AdmBESA.getInstance();
                AgHandlerBESA ah = adm.getHandlerByAlias(wpsStart.aliasBankAgent);

                BankMessage bankMessage = new BankMessage(
                        ASK_CURRENT_TERM,
                        believes.getPeasantProfile().getProfileName()
                );

                EventBESA ev = new EventBESA(
                        BankAgentGuard.class.getName(),
                        bankMessage);
                ah.sendEvent(ev);

            } catch (ExceptionBESA ex) {
                wpsReport.error(ex);
            }
        }
        wpsReport.debug(believes.getPeasantProfile());
        //this.setTaskWaitingForExecution();
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
        return !believes.getPeasantProfile().isNewDay();
    }
}
