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
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import wpsActivator.wpsStart;
import wpsViewer.Agent.wpsReport;

public class ControlAgentState extends StateBESA implements Serializable {

    private int activeAgentsCount;
    private Map<String, Boolean> agentMap = new HashMap<>();
    private Timer timer;

    public ControlAgentState() {
        super();
        this.activeAgentsCount = 0;
        this.timer = new Timer();
    }

    public boolean getActiveAgentsReady() {
        return (this.activeAgentsCount == wpsStart.peasantFamiliesAgents);
    }

    public int getActiveAgentsCount() {
        return this.activeAgentsCount;
    }

    public void resetActiveAgents() {
        this.activeAgentsCount = 0;
    }

    public synchronized void increaseActiveAgents() {
        this.activeAgentsCount++;
    }
    
    public synchronized void clearAgentMap(){
        this.agentMap.clear();
    }

    public synchronized void modifyAgentMap(String agentName, boolean status) {
        this.agentMap.put(agentName, status);
        // Reschedule the timer to execute checkAgentsAlive in 5 minutes
        this.timer.schedule(new CheckAgentsAliveTask(), 4 * 60 * 1000);
    }

    private class CheckAgentsAliveTask extends TimerTask {

        @Override
        public void run() {
            checkAgentsAlive();
        }
    }

    private void checkAgentsAlive() {
        wpsStart.getStatus();
        wpsReport.info("--- agentMap ---" + this.agentMap);
    }
}
