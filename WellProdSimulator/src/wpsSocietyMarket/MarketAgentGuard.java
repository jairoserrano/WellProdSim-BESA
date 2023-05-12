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
package wpsSocietyMarket;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import wpsPeasantFamily.Agent.FromMarketGuard;
import wpsPeasantFamily.Agent.FromMarketMessage;
import wpsPeasantFamily.Agent.FromMarketMessageType;
import static wpsSocietyMarket.MarketMessageType.ASK_FOR_PRICE_LIST;
import static wpsSocietyMarket.MarketMessageType.BUY_SEEDS;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class MarketAgentGuard extends GuardBESA {

    /**
     *
     * @param event
     */
    @Override
    public void funcExecGuard(EventBESA event) {
        MarketMessage marketMessage = (MarketMessage) event.getData();
        MarketAgentState state = (MarketAgentState) this.agent.getState();

        MarketMessageType messageType = marketMessage.getMessageType();
        int quantity = marketMessage.getQuantity();

        try {

            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAlias(
                    marketMessage.getPeasantAlias()
            );
            FromMarketMessageType fromMarketMessageType = null;
            FromMarketMessage fromMarketMessage;

            if (messageType.equals(ASK_FOR_PRICE_LIST)) {
                fromMarketMessageType = FromMarketMessageType.PRICE_LIST;
                fromMarketMessage = new FromMarketMessage(
                        fromMarketMessageType,
                        state.getResources()
                );
            } else {
                switch (messageType) {
                    case BUY_SEEDS:
                        if (state.getResources().get("Seeds").isAvailable(quantity)){
                            state.getResources().get("Seeds").discountQuantity(quantity);
                        }
                        break;
                    case BUY_WATER:
                        if (state.getResources().get("Water").isAvailable(quantity)){
                            state.getResources().get("Water").discountQuantity(quantity);
                        }
                        break;
                    case BUY_PESTICIDES:
                        if (state.getResources().get("Pesticides").isAvailable(quantity)){
                            state.getResources().get("Pesticides").discountQuantity(quantity);
                        }
                        break;
                    case BUY_SUPPLIES:
                        if (state.getResources().get("Supplies").isAvailable(quantity)){
                            state.getResources().get("Supplies").discountQuantity(quantity);
                        }
                        break;
                    case BUY_TOOLS:
                        if (state.getResources().get("Tools").isAvailable(quantity)){
                            state.getResources().get("Tools").discountQuantity(quantity);
                        }
                        break;
                    case BUY_LIVESTOCK:
                        if (state.getResources().get("Livestock").isAvailable(quantity)){
                            state.getResources().get("Livestock").discountQuantity(quantity);
                        }
                        break;
                }
                fromMarketMessage = new FromMarketMessage(
                        fromMarketMessageType,
                        quantity);
            }

            EventBESA ev = new EventBESA(
                    FromMarketGuard.class.getName(),
                    fromMarketMessage);
            ah.sendEvent(ev);

        } catch (ExceptionBESA | IllegalArgumentException e) {
            wpsReport.error("Mensaje no reconocido de funcExecGuard");
        }
    }

}
