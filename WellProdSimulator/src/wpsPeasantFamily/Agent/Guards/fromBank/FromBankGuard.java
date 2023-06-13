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
package wpsPeasantFamily.Agent.Guards.FromBank;

import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import static wpsPeasantFamily.Agent.Guards.FromBank.FromBankMessageType.APPROBED_LOAN;
import static wpsPeasantFamily.Agent.Guards.FromBank.FromBankMessageType.DENIED_FORMAL_LOAN;
import static wpsPeasantFamily.Agent.Guards.FromBank.FromBankMessageType.TERM_TO_PAY;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsPeasantFamily.Data.MoneyOriginType;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class FromBankGuard extends GuardBESA {

    /**
     *
     * @param event
     */
    @Override
    public void funcExecGuard(EventBESA event) {
        FromBankMessage fromBankMessage = (FromBankMessage) event.getData();
        StateBDI state = (StateBDI) this.agent.getState();
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) state.getBelieves();

        FromBankMessageType fromBankMessageType = fromBankMessage.getMessageType();

        try {

            switch (fromBankMessageType) {
                case APPROBED_LOAN:
                    wpsReport.info(believes.getPeasantProfile().getPeasantFamilyAlias() + " incrementó el dinero con prestamo en: " + fromBankMessage.getAmount());
                    believes.getPeasantProfile().increaseMoney(
                            fromBankMessage.getAmount()
                    );
                    believes.setCurrentMoneyOrigin(MoneyOriginType.LOAN);
                    break;
                case APPROBED_SOCIAL:
                    wpsReport.info(believes.getPeasantProfile().getPeasantFamilyAlias() + " incrementó el dinero en de social para: " + fromBankMessage.getAmount());
                    believes.getPeasantProfile().increaseMoney(
                            fromBankMessage.getAmount()
                    );
                    believes.setCurrentMoneyOrigin(MoneyOriginType.BENEFICENCIA);
                    break;
                case DENIED_FORMAL_LOAN:
                    // @TODO: Pedir prestado en otro lado? cancelar?
                    wpsReport.info("Denegado DENIED_FORMAL_LOAN");
                    believes.setCurrentMoneyOrigin(MoneyOriginType.LOAN_DENIED);
                    break;
                case DENIED_INFORMAL_LOAN:
                    // @TODO: Pedir prestado en otro lado? cancelar?
                    //if (Math.random() < 0.2) {
                    //believes.setCurrentMoneyOrigin(MoneyOriginType.BENEFICENCIA);
                    wpsReport.info("Denegado DENIED_INFORMAL_LOAN");
                    believes.setCurrentMoneyOrigin(MoneyOriginType.INFORMAL_DENIED);
                    break;
                case TERM_TO_PAY:
                    believes.getPeasantProfile().setLoanAmountToPay(
                            fromBankMessage.getAmount()
                    );
                    break;
                case TERM_PAYED:
                    believes.setCurrentMoneyOrigin(MoneyOriginType.NONE);
                    believes.getPeasantProfile().setLoanAmountToPay(0);
                    break;
            }
        } catch (IllegalArgumentException e) {
            wpsReport.error("Mensaje no reconocido de FromWorldMessageType");
        }

    }

}
