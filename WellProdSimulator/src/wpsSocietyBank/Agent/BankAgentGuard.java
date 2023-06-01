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
package wpsSocietyBank.Agent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import wpsPeasantFamily.Agent.Guards.FromBank.FromBankGuard;
import wpsPeasantFamily.Agent.Guards.FromBank.FromBankMessage;
import wpsPeasantFamily.Agent.Guards.FromBank.FromBankMessageType;
import static wpsPeasantFamily.Agent.Guards.FromBank.FromBankMessageType.APPROBED_LOAN;
import static wpsPeasantFamily.Agent.Guards.FromBank.FromBankMessageType.TERM_PAYED;
import static wpsPeasantFamily.Agent.Guards.FromBank.FromBankMessageType.TERM_TO_PAY;
import static wpsSocietyBank.Agent.BankMessageType.PAY_LOAN_TERM;
import wpsViewer.Agent.wpsReport;
import static wpsSocietyBank.Agent.BankMessageType.ASK_FOR_FORMAL_LOAN;
import static wpsPeasantFamily.Agent.Guards.FromBank.FromBankMessageType.DENIED_FORMAL_LOAN;
import static wpsPeasantFamily.Agent.Guards.FromBank.FromBankMessageType.DENIED_INFORMAL_LOAN;
import static wpsSocietyBank.Agent.BankMessageType.ASK_FOR_INFORMAL_LOAN;

/**
 *
 * @author jairo
 */
public class BankAgentGuard extends GuardBESA {

    /**
     *
     * @param event
     */
    @Override
    public void funcExecGuard(EventBESA event) {
        //wpsReport.info("$$$ Bank $$$");
        BankMessage bankMessage = (BankMessage) event.getData();
        BankAgentState state = (BankAgentState) this.agent.getState();

        BankMessageType messageType = bankMessage.getMessageType();

        try {

            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAlias(
                    bankMessage.getPeasantAlias()
            );
            FromBankMessageType fromBankMessageType = null;
            Integer amount = 0;

            switch (messageType) {
                case ASK_FOR_FORMAL_LOAN:
                    if (state.giveLoanToPeasantFamily(
                            ASK_FOR_FORMAL_LOAN,
                            bankMessage.getPeasantAlias(),
                            bankMessage.getAmount()
                    )) {
                        //wpsReport.info("$$$ APPROBED Bank to " + bankMessage.getPeasantAlias());
                        fromBankMessageType = APPROBED_LOAN;
                    } else {
                        //wpsReport.info("$$$ DENIED Bank to " + bankMessage.getPeasantAlias());
                        fromBankMessageType = DENIED_FORMAL_LOAN;
                    }
                    amount = bankMessage.getAmount();
                    break;
                case ASK_FOR_INFORMAL_LOAN:
                    if (state.giveLoanToPeasantFamily(
                            ASK_FOR_INFORMAL_LOAN,
                            bankMessage.getPeasantAlias(),
                            bankMessage.getAmount()
                    )) {
                        //wpsReport.info("$$$ APPROBED Bank to " + bankMessage.getPeasantAlias());
                        fromBankMessageType = APPROBED_LOAN;
                    } else {
                        //wpsReport.info("$$$ DENIED Bank to " + bankMessage.getPeasantAlias());
                        fromBankMessageType = DENIED_INFORMAL_LOAN;
                    }
                    amount = bankMessage.getAmount();
                    break;
                case ASK_CURRENT_TERM:
                    amount = state.currentMoneyToPay(
                            bankMessage.getPeasantAlias()
                    );
                    fromBankMessageType = TERM_TO_PAY;
                    break;
                case PAY_LOAN_TERM:
                    amount = state.payLoan(
                            bankMessage.getPeasantAlias(),
                            bankMessage.getAmount()
                    );
                    fromBankMessageType = TERM_PAYED;
                    break;
            }

            FromBankMessage fromBankMessage = new FromBankMessage(
                    fromBankMessageType,
                    amount);

            EventBESA ev = new EventBESA(
                    FromBankGuard.class.getName(),
                    fromBankMessage);
            ah.sendEvent(ev);

        } catch (ExceptionBESA | IllegalArgumentException e) {
            wpsReport.error("Mensaje no reconocido de funcExecGuard");
        }

    }

}
