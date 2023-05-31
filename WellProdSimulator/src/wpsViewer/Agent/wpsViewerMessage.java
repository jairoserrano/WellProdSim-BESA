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
package wpsViewer.Agent;

import BESA.Kernel.Agent.Event.DataBESA;

/**
 *
 * @author jairo
 */
public class wpsViewerMessage extends DataBESA {

    private String peasantAlias;
    private String peasantMessage;

    /**
     *
     * @param peasantAlias
     * @param peasantMessage
     */
    public wpsViewerMessage(String peasantAlias, String peasantMessage) {
        this.peasantAlias = peasantAlias;
        this.peasantMessage = peasantMessage;
    }

    /**
     *
     * @return
     */
    public String getPeasantAlias() {
        return peasantAlias;
    }

    /**
     *
     * @param peasantAlias
     */
    public void setPeasantAlias(String peasantAlias) {
        this.peasantAlias = peasantAlias;
    }

    public String getPeasantMessage() {
        return peasantMessage;
    }

    public void setPeasantMessage(String peasantMessage) {
        this.peasantMessage = peasantMessage;
    }

}
