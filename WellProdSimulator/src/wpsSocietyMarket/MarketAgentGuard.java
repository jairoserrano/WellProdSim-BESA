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
import wpsPeasantFamily.Agent.Guards.FromMarketGuard;
import wpsPeasantFamily.Agent.Guards.FromMarketMessage;
import wpsPeasantFamily.Agent.Guards.FromMarketMessageType;
import static wpsSocietyMarket.MarketMessageType.ASK_FOR_PRICE_LIST;
import static wpsSocietyMarket.MarketMessageType.BUY_SEEDS;
import static wpsSocietyMarket.MarketMessageType.SELL_CROP;
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

            if (messageType == ASK_FOR_PRICE_LIST) {
                wpsReport.debug(marketMessage.getPeasantAlias() + " pidio lista de precios");
                fromMarketMessageType = FromMarketMessageType.PRICE_LIST;
                fromMarketMessage = new FromMarketMessage(
                        fromMarketMessageType,
                        state.getResources()
                );
            } else if (messageType == SELL_CROP) {
                state.getResources().get(marketMessage.getCropName()).setQuantity(
                        quantity
                        + marketMessage.getQuantity()
                );
                //wpsReport.warn("Comprado");
                fromMarketMessageType = FromMarketMessageType.SOLD_CROP;
                fromMarketMessage = new FromMarketMessage(
                        fromMarketMessageType,
                        marketMessage.getCropName(),
                        quantity,
                        state.getResources().get(
                                marketMessage.getCropName()
                        ).getCost() * quantity
                );
            } else {
                String productType = switch (messageType) {
                    case BUY_SEEDS ->
                        "seeds";
                    case BUY_WATER ->
                        "water";
                    case BUY_PESTICIDES ->
                        "pesticides";
                    case BUY_SUPPLIES ->
                        "supplies";
                    case BUY_TOOLS ->
                        "tools";
                    case BUY_LIVESTOCK ->
                        "livestock";
                    default ->
                        throw new IllegalArgumentException("Invalid message type: " + messageType);
                };

                if (state.getResources().get(productType).isAvailable(quantity)) {
                    state.getResources().get(productType).discountQuantity(quantity);
                }
                fromMarketMessageType = FromMarketMessageType.valueOf(productType.toUpperCase());
                fromMarketMessage = new FromMarketMessage(fromMarketMessageType, quantity);
            }

            EventBESA ev = new EventBESA(
                    FromMarketGuard.class.getName(),
                    fromMarketMessage);
            ah.sendEvent(ev);
            wpsReport.debug(marketMessage.getPeasantAlias() + " enviada lista de precios");

        } catch (ExceptionBESA | IllegalArgumentException e) {
            wpsReport.error("Mensaje no reconocido de funcExecGuard");
        }
    }

}
