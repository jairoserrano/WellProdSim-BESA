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
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsPeasantFamily.Data.CropCareType;
import wpsPeasantFamily.Data.MoneyOriginType;
import wpsPeasantFamily.Data.SeasonType;
import wpsPeasantFamily.Data.TimeConsumedBy;
import wpsSocietyBank.Agent.BankAgentGuard;
import wpsSocietyBank.Agent.BankMessage;
import static wpsSocietyBank.Agent.BankMessageType.ASK_CURRENT_TERM;
import wpsViewer.Agent.wpsReport;

/**
 *
 *
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
    public void executeTask(Believes parameters) {
        //wpsReport.info("⚙️⚙️⚙️");        
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        believes.useTime(TimeConsumedBy.valueOf(this.getClass().getSimpleName()));
        believes.setNewDay(false);
        
        if (DateHelper.differenceDaysBetweenTwoDates(
                believes.getInternalCurrentDate(),
                believes.getPeasantProfile().getStartRiceSeason()) == 0) {
            believes.setCurrentSeason(SeasonType.PREPARATION);
        }

        // Vitals about money and food
        if (believes.getPeasantProfile().getMoney()
                >= believes.getPeasantProfile().getPeasantFamilyMinimalVital() * 10) {
            believes.getPeasantProfile().discountDailyMoney();
        } else {
            believes.setCurrentMoneyOrigin(MoneyOriginType.LOAN);
            believes.getPeasantProfile().decreaseHealth();
        }
        checkBankDebt(believes);
        checkWeek(believes);
        // En que gasta el tiempo el día
        believes.setRandomCurrentPeasantLeisureType();
        if (believes.getCurrentSeason() == SeasonType.GROWING) {
            believes.setCurrentCropCare(CropCareType.CHECK);
        }
        //wpsReport.debug(believes.toString());
        //this.setTaskFinalized();
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void interruptTask(Believes parameters) {
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void cancelTask(Believes parameters) {
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
    
    private void checkWeek(PeasantFamilyBDIAgentBelieves believes) {
        if (believes.getCurrentDay() % wpsStart.DAYSTOCHECK == 0) {
            try {
                believes.setWeekBlock();
                wpsReport.warn(believes.getWeekBlock());
                AdmBESA adm = AdmBESA.getInstance();
                EventBESA eventBesa = new EventBESA(ControlAgentGuard.class.getName(), null);
                AgHandlerBESA agHandler = adm.getHandlerByAlias(wpsStart.config.getControlAgentName());
                agHandler.sendEvent(eventBesa);
            } catch (ExceptionBESA ex) {
                wpsReport.error(ex);
            }
        }
    }
    
    private void checkBankDebt(PeasantFamilyBDIAgentBelieves believes) {
        // Check for the loan pay amount only on first day of month
        if (wpsCurrentDate.getInstance().isFirstDayOfMonth(believes.getInternalCurrentDate())) {
            try {
                AdmBESA adm = AdmBESA.getInstance();
                AgHandlerBESA ah = adm.getHandlerByAlias(wpsStart.config.getBankAgentName());
                
                BankMessage bankMessage = new BankMessage(
                        ASK_CURRENT_TERM,
                        believes.getPeasantProfile().getPeasantFamilyAlias()
                );
                
                EventBESA ev = new EventBESA(
                        BankAgentGuard.class.getName(),
                        bankMessage);
                ah.sendEvent(ev);
                
            } catch (ExceptionBESA ex) {
                wpsReport.error(ex);
            }
        }
    }
}
