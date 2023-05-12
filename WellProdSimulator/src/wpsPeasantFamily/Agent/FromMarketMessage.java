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

import BESA.Kernel.Agent.Event.DataBESA;
import java.util.HashMap;
import java.util.Map;
import wpsPeasantFamily.Utils.FarmingResource;

/**
 *
 * @author jairo
 */
public class FromMarketMessage extends DataBESA {

    private Integer quantity;
    private FromMarketMessageType fromMarketMessageType;
    private Map<String, FarmingResource> priceList = new HashMap<>();


    /**
     *
     * @param fromMarketMessageType
     * @param quantity
     */
    public FromMarketMessage(FromMarketMessageType fromMarketMessageType, Integer quantity) {
        this.quantity = quantity;
        this.fromMarketMessageType = fromMarketMessageType;
    }

        /**
     *
     * @param fromMarketMessageType
     * @param priceList
     */
    public FromMarketMessage(FromMarketMessageType fromMarketMessageType, Map<String, FarmingResource> priceList) {
        this.priceList = priceList;
        this.fromMarketMessageType = fromMarketMessageType;
    }
    
    /**
     *
     * @param quantity
     */
    public FromMarketMessage(Integer quantity) {
        this.quantity = quantity;
    }
    public Map<String, FarmingResource> getPriceList() {
        return priceList;
    }
    public void setPriceList(Map<String, FarmingResource> priceList) {
        this.priceList = priceList;
    }

    /**
     *
     * @return
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     */
    public FromMarketMessageType getMessageType() {
        return fromMarketMessageType;
    }

    /**
     *
     * @param fromMarketMessageType
     */
    public void setMessageType(FromMarketMessageType fromMarketMessageType) {
        this.fromMarketMessageType = fromMarketMessageType;
    }
}
