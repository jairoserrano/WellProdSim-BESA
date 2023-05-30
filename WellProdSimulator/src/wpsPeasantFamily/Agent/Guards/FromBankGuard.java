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
package wpsPeasantFamily.Agent.Guards;

import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import static wpsPeasantFamily.Agent.Guards.FromBankMessageType.APPROBED_LOAN;
import static wpsPeasantFamily.Agent.Guards.FromBankMessageType.TERM_TO_PAY;
import wpsViewer.Agent.wpsReport;
import static wpsPeasantFamily.Agent.Guards.FromBankMessageType.DENIED_FORMAL_LOAN;

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
                    wpsReport.info("Incrementó el dinero en: " + fromBankMessage.getAmount());
                    believes.getPeasantProfile().increaseMoney(
                            fromBankMessage.getAmount()
                    );
                    believes.getPeasantProfile().setFormalLoanSeason(false);
                    break;
                case DENIED_FORMAL_LOAN:
                    // @TODO: Pedir prestado en otro lado? cancelar?
                    believes.getPeasantProfile().setFormalLoanSeason(false);
                    believes.getPeasantProfile().setInformalLoanSeason(true);
                    break;
                case TERM_TO_PAY:
                    believes.getPeasantProfile().setLoanAmountToPay(
                            fromBankMessage.getAmount()
                    );
                    break;
                case TERM_PAYED:
                    believes.getPeasantProfile().setLoanAmountToPay(0);
                    break;
            }
        } catch (IllegalArgumentException e) {
            wpsReport.error("Mensaje no reconocido de FromWorldMessageType");
        }
        
    }
    
}
