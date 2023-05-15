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
package wpsPeasantFamily.Agent;

import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import static wpsPeasantFamily.Agent.FromMarketMessageType.PRICE_LIST;
import wpsViewer.Agent.wpsReport;
import static wpsPeasantFamily.Agent.FromMarketMessageType.SEEDS;

/**
 *
 * @author jairo
 */
public class FromMarketGuard extends GuardBESA {

    /**
     *
     * @param event
     */
    @Override
    public void funcExecGuard(EventBESA event) {
        FromMarketMessage fromMarketMessage = (FromMarketMessage) event.getData();
        StateBDI state = (StateBDI) this.agent.getState();
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) state.getBelieves();

        FromMarketMessageType fromMarketMessageType = fromMarketMessage.getMessageType();
        int discount = 0;

        try {

            switch (fromMarketMessageType) {
                case PRICE_LIST:
                    believes.getPeasantProfile().setPriceList(
                            fromMarketMessage.getPriceList()
                    );
                    break;
                case SEEDS:
                    believes.getPeasantProfile().setSeeds(
                            fromMarketMessage.getQuantity()
                    );
                    discount = fromMarketMessage.getQuantity() * believes.getPeasantProfile().getPriceList().get("Seeds").getCost();
                    break;
                case WATER:
                    believes.getPeasantProfile().setWaterAvailable(
                            fromMarketMessage.getQuantity()
                    );
                    discount = fromMarketMessage.getQuantity() * believes.getPeasantProfile().getPriceList().get("Water").getCost();
                    break;
                case PESTICIDES:
                    believes.getPeasantProfile().setPesticidesAvailable(
                            fromMarketMessage.getQuantity()
                    );
                    discount = fromMarketMessage.getQuantity() * believes.getPeasantProfile().getPriceList().get("Pesticides").getCost();
                    break;
                case SUPPLIES:
                    believes.getPeasantProfile().setSupplies(
                            fromMarketMessage.getQuantity()
                    );
                    discount = fromMarketMessage.getQuantity() * believes.getPeasantProfile().getPriceList().get("Supplies").getCost();
                    break;
                case TOOLS:
                    believes.getPeasantProfile().setTools(
                            fromMarketMessage.getQuantity()
                    );
                    discount = fromMarketMessage.getQuantity() * believes.getPeasantProfile().getPriceList().get("Tools").getCost();
                    break;
                case LIVESTOCK:
                    believes.getPeasantProfile().setLivestockNumber(
                            fromMarketMessage.getQuantity()
                    );
                    discount = fromMarketMessage.getQuantity() * believes.getPeasantProfile().getPriceList().get("Livestock").getCost();
                    break;
            }

            believes.getPeasantProfile().useMoney(discount);

        } catch (IllegalArgumentException e) {
            wpsReport.error("Mensaje no reconocido de FromWorldMessageType");
        }

    }

}
