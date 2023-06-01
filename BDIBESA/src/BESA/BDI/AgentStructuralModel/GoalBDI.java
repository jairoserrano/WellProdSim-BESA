/**
 * Represents a goal in the BDI flow.
 * Implements BDIEvaluable, Serializable, and Comparable<GoalBDI>.
 */
package BESA.BDI.AgentStructuralModel;

import BESA.BDI.AgentStructuralModel.Functions.ContributionComparator;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import rational.RationalRole;
import rational.mapping.Believes;
import java.io.Serializable;

public abstract class GoalBDI implements BDIEvaluable, Serializable, Comparable<GoalBDI> {

    private long id;
    private double plausibilityLevel;
    private double viabilityValue;
    private double contributionValue;
    private double detectionValue;
    private RationalRole role;
    private String description;
    private GoalBDITypes type;
    private boolean succeed;

    /**
     * Constructs a new GoalBDI object with specified id, role, description, and type.
     * 
     * @param id    The id of the goal.
     * @param role    The RationalRole object representing the goal's role.
     * @param description    The description of the goal.
     * @param type    The GoalBDITypes object representing the goal's type.
     */
    public GoalBDI(long id, RationalRole role, String description, GoalBDITypes type) {
        this.id = id;
        this.role = role;
        this.description = description;
        this.type = type;
    }

    /**
     * Checks if the goal has succeeded.
     * 
     * @return True if the goal has succeeded, false otherwise.
     */
    public boolean isSucceed() {
        return succeed;
    }

    /**
     * Sets the flag indicating whether the goal has succeeded.
     * 
     * @param succed    The flag indicating whether the goal has succeeded.
     */
    public void setSucceed(boolean succed) {
        this.succeed = succed;
    }

    /**
     * Retrieves the detection value of the goal.
     * 
     * @return The detection value of the goal.
     */
    public synchronized double getDetectionValue() {
        return detectionValue;
    }

    /**
     * Sets the detection value of the goal.
     * 
     * @param detectionValue    The detection value of the goal.
     */
    public void setDetectionValue(double detectionValue) {
        this.detectionValue = detectionValue;
    }

    /**
     * Retrieves the contribution value of the goal.
     * 
     * @return The contribution value of the goal.
     */
    public synchronized double getContributionValue() {
        return contributionValue;
    }

    /**
     * Sets the contribution value of the goal.
     * 
     * @param contributionValue    The contribution value of the goal.
     */
    public void setContributionValue(double contributionValue) {
        this.contributionValue = contributionValue;
    }

    /**
     * Retrieves the description of the goal.
     * 
     * @return The description of the goal.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the goal.
     * 
     * @param description    The description of the goal.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the id of the goal.
     * 
     * @return The id of the goal.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id of the goal.
     * 
     * @param id    The id of the goal.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Retrieves the plausibility level of the goal.
     * 
     * @return The plausibility level of the goal.
     */
    public double getPlausibilityLevel() {
        return plausibilityLevel;
    }

    /**
     * Sets the plausibility level of the goal.
     * 
     * @param plausibilityLevel    The plausibility level of the goal.
     */
    public void setPlausibilityLevel(double plausibilityLevel) {
        this.plausibilityLevel = plausibilityLevel;
    }

    /**
     * Retrieves the type of the goal.
     * 
     * @return The GoalBDITypes object representing the type of the goal.
     */
    public GoalBDITypes getType() {
        return type;
    }

    /**
     * Sets the type of the goal.
     * 
     * @param type    The GoalBDITypes object representing the type of the goal.
     */
    public void setType(GoalBDITypes type) {
        this.type = type;
    }

    /**
     * Retrieves the RationalRole object representing the role of the goal.
     * 
     * @return The RationalRole object representing the role of the goal.
     */
    public RationalRole getRole() {
        return role;
    }

    /**
     * Sets the RationalRole object representing the role of the goal.
     * 
     * @param role    The RationalRole object representing the role of the goal.
     */
    public void setRole(RationalRole role) {
        this.role = role;
    }

    /**
     * Retrieves the viability value of the goal.
     * 
     * @return The viability value of the goal.
     */
    public double getViabilityValue() {
        return viabilityValue;
    }

    /**
     * Sets the viability value of the goal.
     * 
     * @param viabilityValue    The viability value of the goal.
     */
    public void setViabilityValue(double viabilityValue) {
        this.viabilityValue = viabilityValue;
    }

    /**
     * Evaluates the viability for a mapping process using the believes.
     * 
     * @param machineParams    The BDIMachineParams object representing the BDI machine parameters.
     * @param believes    The Believes object representing the agent's beliefs.
     * @return True if the viability evaluation is above the threshold, false otherwise.
     * @throws KernellAgentEventExceptionBESA    If an exception occurs during the evaluation process.
     */
    public boolean evaluateMappingViability(BDIMachineParams machineParams, Believes believes) throws KernellAgentEventExceptionBESA {
        boolean returnValue = false;
        double viabilityEvaluation = this.evaluateViability(believes);
        switch (this.getType()) {
            case OBLIGATION:
                if (viabilityEvaluation > machineParams.getDutyThreshold()) {
                    returnValue = true;
                }
            case SOCIAL:
                if (viabilityEvaluation > machineParams.getNeedThreshold()) {
                    returnValue = true;
                }
            case DEVELOPMENT:
                if (viabilityEvaluation > machineParams.getOportunityThreshold()) {
                    returnValue = true;
                }
            case SKILLSRESOURCES:
                if (viabilityEvaluation > machineParams.getRequirementThreshold()) {
                    returnValue = true;
                }
            case SURVIVAL:
                if (viabilityEvaluation > machineParams.getSurvivalThreshold()) {
                    returnValue = true;
                }
            case LEISURE:
                if (viabilityEvaluation > machineParams.getAttentionCycleThreshold()) {
                    returnValue = true;
                }
        }
        return returnValue;
    }

    @Override
    public int compareTo(GoalBDI o) {
        ContributionComparator contributionComparator = new ContributionComparator();
        return contributionComparator.compare(this, o);
    }

    @Override
    public boolean equals(Object goal) {
        if (goal == null) {
            return false;
        } else if (!(goal instanceof GoalBDI)) {
            return false;
        } else {
            GoalBDI g = (GoalBDI) goal;
            return this.getId() == g.getId();
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

}
