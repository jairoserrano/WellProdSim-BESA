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

    private boolean finished;

    /**
     *
     */
    public DoVitalsTask() {
        ////wpsReport.info("");
        this.finished = false;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void executeTask(Believes parameters) {
        ////wpsReport.info("");
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;

        // dormir 8 horas y 4 horas de alimentación
        wpsReport.info("🔆🔆🔆 Despertó el " + wpsCurrentDate.getInstance().getCurrentDate() + ". Hizo sus funciones vitales.");

        // Vitals about money and food
        if (believes.getPeasantProfile().getMoney()
                > believes.getPeasantProfile().getPeasantFamilyMinimalVital()) {
            believes.getPeasantProfile().discountDailyMoney();
        } else {
            believes.getPeasantProfile().setFormalLoanSeason(true);
        }

        // Check for the loan pay amount
        //wpsReport.debug(" PREEEEE00000000001111111111 ");
        if (wpsCurrentDate.getInstance().isFirstDayOfMonth()) {
            //wpsReport.debug(" 00000000001111111111 ");
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
        //wpsReport.debug(" POOSSST00000000001111111111 ");

        // Vitals about
        believes.getPeasantProfile().useTime(TimeConsumedBy.DoVitalsTask);
        believes.getPeasantProfile().setNewDayFalse();
        //this.setFinished(true);
        this.setTaskWaitingForExecution();
    }

    /**
     *
     * @return
     */
    public boolean isFinished() {
        ////wpsReport.info("");
        return finished;
    }

    /**
     *
     * @param finished
     */
    public void setFinished(boolean finished) {
        //wpsReport.info("");
        this.finished = finished;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void interruptTask(Believes parameters) {
        //wpsReport.info("");
        this.setFinished(true);
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void cancelTask(Believes parameters) {
        ////wpsReport.info("");
        this.setFinished(true);
    }

    /**
     *
     * @return
     */
    public boolean isExecuted() {
        ////wpsReport.info("");
        return finished;
    }

    /**
     *
     * @param believes
     * @return
     */
    @Override
    public boolean checkFinish(Believes believes) {
        ////wpsReport.info("");
        return isExecuted();
    }
}
