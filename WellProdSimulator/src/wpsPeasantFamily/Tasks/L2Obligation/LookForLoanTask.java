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
package wpsPeasantFamily.Tasks.L2Obligation;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import rational.mapping.Believes;
import rational.mapping.Task;
import wpsActivator.wpsStart;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsPeasantFamily.Utils.TimeConsumedBy;
import wpsSocietyBank.Agent.BankAgentGuard;
import wpsSocietyBank.Agent.BankMessage;
import wpsViewer.Agent.wpsReport;
import static wpsSocietyBank.Agent.BankMessageType.ASK_FOR_FORMAL_LOAN;
import static wpsSocietyBank.Agent.BankMessageType.ASK_FOR_INFORMAL_LOAN;

/**
 *
 * @author jairo
 */
public class LookForLoanTask extends Task {

    private boolean finished;

    /**
     *
     */
    public LookForLoanTask() {
        ////wpsReport.info("");
        this.finished = false;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public synchronized void executeTask(Believes parameters) {
        wpsReport.info("⚙️⚙️⚙️");
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        
        believes.getPeasantProfile().setFormalLoanSeason(false);
        
        // @TODO: Se debe calcular cuanto necesitas prestar hasta que se coseche.
        try {
            AdmBESA adm = AdmBESA.getInstance();
            AgHandlerBESA ah = adm.getHandlerByAlias(wpsStart.aliasBankAgent);

            BankMessage bankMessage;
            if (believes.getPeasantProfile().isInformalLoanNeeded()) {
                bankMessage = new BankMessage(
                        ASK_FOR_INFORMAL_LOAN,
                        believes.getPeasantProfile().getProfileName(),
                        100000);
                believes.getPeasantProfile().setInformalLoanSeason(false);
            } else {
                bankMessage = new BankMessage(
                        ASK_FOR_FORMAL_LOAN,
                        believes.getPeasantProfile().getProfileName(),
                        500000);
                believes.getPeasantProfile().setFormalLoanSeason(false);
            }

            EventBESA ev = new EventBESA(
                    BankAgentGuard.class.getName(),
                    bankMessage);
            ah.sendEvent(ev);

            believes.getPeasantProfile().useTime(TimeConsumedBy.LookForLoan);

        } catch (ExceptionBESA ex) {
            wpsReport.error(ex);
        }
        this.setTaskFinalized();

    }

    /**
     *
     * @param parameters
     */
    @Override
    public void interruptTask(Believes parameters) {
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
     * @param believes
     * @return
     */
    @Override
    public boolean checkFinish(Believes believes) {
        return true;
    }
}
