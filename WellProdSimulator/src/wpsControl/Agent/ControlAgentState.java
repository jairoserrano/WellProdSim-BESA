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
package wpsControl.Agent;

import BESA.Kernel.Agent.StateBESA;
import java.io.Serializable;
import wpsActivator.wpsStart;

/**
 *
 * @author jairo
 */
public class ControlAgentState extends StateBESA implements Serializable {

    int activeAgents;

    /**
     *
     */
    public ControlAgentState() {
        super();
        this.activeAgents = 0;
    }

    public boolean getActiveAgentsReady() {
        return (this.activeAgents == wpsStart.peasantFamiliesAgents);
    }

    public int getActiveAgents() {
        return this.activeAgents;
    }

    public void resetActiveAgents() {
        this.activeAgents = 0;
    }

    public synchronized void increaseActiveAgents() {
        this.activeAgents++;
    }
}
