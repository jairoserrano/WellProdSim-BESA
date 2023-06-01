/**
 * Represents the state of a BDI agent in the BDI structural model.
 * Extends RationalState and implements Serializable.
 */
package BESA.BDI.AgentStructuralModel;

import rational.RationalState;
import java.io.Serializable;
import java.util.List;
import rational.mapping.Believes;

public class StateBDI extends RationalState implements Serializable {

    private BDIMachineParams machineBDIParams;
    private boolean endedTheDesiresMachine;
    private boolean inQueue;

    /**
     * Constructs a new StateBDI object with default values.
     */
    public StateBDI() {
        super();
        this.machineBDIParams = new BDIMachineParams();
        this.endedTheDesiresMachine = true;
        this.inQueue = false;
    }

    /**
     * Constructs a new StateBDI object with specified goals, threshold, and believes.
     * 
     * @param goals    The list of GoalBDI objects representing the agent's goals.
     * @param threshold    The threshold value for BDI machine parameters.
     * @param believes    The Believes object representing the agent's beliefs.
     */
    public StateBDI(List<GoalBDI> goals, double threshold, Believes believes) {
        super(believes);
        this.machineBDIParams = new BDIMachineParams();
        this.machineBDIParams.setNeedThreshold(threshold);
        this.machineBDIParams.setDutyThreshold(threshold);
        this.machineBDIParams.setOportunityThreshold(threshold);
        this.machineBDIParams.setRequirementThreshold(threshold);
        this.machineBDIParams.setSurvivalThreshold(threshold);
        this.machineBDIParams.setAttentionCycleThreshold(threshold);
        for (GoalBDI goal : goals) {
            this.machineBDIParams.addPotentialGoal(goal);
        }
        this.endedTheDesiresMachine = true;
        this.inQueue = false;
    }

    /**
     * Constructs a new StateBDI object with specified BDI machine parameters and believes.
     * 
     * @param machineBDIParams    The BDIMachineParams object representing the agent's BDI machine parameters.
     * @param believes    The Believes object representing the agent's beliefs.
     */
    public StateBDI(BDIMachineParams machineBDIParams, Believes believes) {
        super(believes);
        this.machineBDIParams = machineBDIParams;
        this.endedTheDesiresMachine = true;
        this.inQueue = false;
    }

    /**
     * Constructs a new StateBDI object with specified BDI machine parameters, believes, and threshold.
     * 
     * @param machineBDIParams    The BDIMachineParams object representing the agent's BDI machine parameters.
     * @param believes    The Believes object representing the agent's beliefs.
     * @param threshold    The threshold value for BDI machine parameters.
     */
    public StateBDI(BDIMachineParams machineBDIParams, Believes believes, double threshold) {
        super(believes);
        this.machineBDIParams = machineBDIParams;
        this.machineBDIParams.setNeedThreshold(threshold);
        this.machineBDIParams.setDutyThreshold(threshold);
        this.machineBDIParams.setOportunityThreshold(threshold);
        this.machineBDIParams.setRequirementThreshold(threshold);
        this.machineBDIParams.setSurvivalThreshold(threshold);
        this.machineBDIParams.setAttentionCycleThreshold(threshold);
        this.endedTheDesiresMachine = true;
        this.inQueue = false;
    }

    /**
     * Retrieves the BDI machine parameters.
     * 
     * @return The BDIMachineParams object representing the agent's BDI machine parameters.
     */
    public BDIMachineParams getMachineBDIParams() {
        return machineBDIParams;
    }

    /**
     * Sets the BDI machine parameters.
     * 
     * @param machineBDIParams    The BDIMachineParams object representing the agent's BDI machine parameters.
     */
    public synchronized void setMachineBDIParams(BDIMachineParams machineBDIParams) {
        this.machineBDIParams = machineBDIParams;
    }

    /**
     * Checks if the desires machine has ended.
     * 
     * @return True if the desires machine has ended, false otherwise.
     */
    public synchronized boolean isEndedTheDesiresMachine() {
        return endedTheDesiresMachine;
    }

    /**
     * Sets the flag indicating whether the desires machine has ended.
     * 
     * @param endedTheDesiresMachine    The flag indicating whether the desires machine has ended.
     */
    public synchronized void setEndedTheDesiresMachine(boolean endedTheDesiresMachine) {
        this.endedTheDesiresMachine = endedTheDesiresMachine;
    }

    /**
     * Checks if the agent is in a queue.
     * 
     * @return True if the agent is in a queue, false otherwise.
     */
    public synchronized boolean isInQueue() {
        return inQueue;
    }

    /**
     * Sets the flag indicating whether the agent is in a queue.
     * 
     * @param inQueue    The flag indicating whether the agent is in a queue.
     */
    public synchronized void setInQueue(boolean inQueue) {
        this.inQueue = inQueue;
    }
}
