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
import wpsPeasantFamily.Data.MoneyOriginType;
import wpsPeasantFamily.Data.TimeConsumedBy;
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

    /**
     *
     */
    public LookForLoanTask() {
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void executeTask(Believes parameters) {
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        believes.useTime(TimeConsumedBy.LookForLoanTask);
        MoneyOriginType currentMoneyOrigin = believes.getCurrentMoneyOrigin();
        believes.setCurrentMoneyOrigin(MoneyOriginType.NONE);

        // @TODO: Se debe calcular cuanto necesitas prestar hasta que se coseche.
        try {
            AdmBESA adm = AdmBESA.getInstance();
            AgHandlerBESA ah = adm.getHandlerByAlias(wpsStart.config.getBankAgentName());

            BankMessage bankMessage;
            if (currentMoneyOrigin == MoneyOriginType.LOAN) {
                bankMessage = new BankMessage(
                        ASK_FOR_FORMAL_LOAN,
                        believes.getPeasantProfile().getPeasantFamilyAlias(),
                        100000);
            } else {
                bankMessage = new BankMessage(
                        ASK_FOR_INFORMAL_LOAN,
                        believes.getPeasantProfile().getPeasantFamilyAlias(),
                        500000);
            }

            EventBESA ev = new EventBESA(
                    BankAgentGuard.class.getName(),
                    bankMessage);
            ah.sendEvent(ev);

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
