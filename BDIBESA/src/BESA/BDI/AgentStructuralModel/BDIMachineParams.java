/**
 * Class that contains parameter values for the BDI machine.
 * It represents the desire hierarchy pyramid, potential goals, and various thresholds.
 */
/*
 * @(#)BDIMachineParams.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.AgentStructuralModel;

import java.util.Iterator;
import java.util.Set;

public class BDIMachineParams {

    private DesireHierarchyPyramid pyramidGoals;
    private PotencialGoalStructure potencialGoals;
    private GoalBDI mainGoal;
    private GoalBDI intention;

    private double dutyThreshold;
    private double survivalThreshold;
    private double oportunityThreshold;
    private double requirementThreshold;
    private double needThreshold;
    private double attentionCycleThreshold;
    private double mainGoalThreshold;

    /**
     * Constructs a new BDIMachineParams object with default values.
     */
    public BDIMachineParams() {
        pyramidGoals = new DesireHierarchyPyramid();
        potencialGoals = new PotencialGoalStructure();
    }

    /**
     * Constructs a new BDIMachineParams object with specified thresholds.
     *
     * @param dutyThreshold The threshold value for duty goals.
     * @param survivalThreshold The threshold value for survival goals.
     * @param oportunityThreshold The threshold value for opportunity goals.
     * @param requirementThreshold The threshold value for requirement goals.
     * @param needThreshold The threshold value for need goals.
     * @param attentionCycleThreshold The threshold value for attention cycle
     * goals.
     */
    public BDIMachineParams(double dutyThreshold, double survivalThreshold, double oportunityThreshold,
            double requirementThreshold, double needThreshold, double attentionCycleThreshold) {
        this.dutyThreshold = dutyThreshold;
        this.survivalThreshold = survivalThreshold;
        this.oportunityThreshold = oportunityThreshold;
        this.requirementThreshold = requirementThreshold;
        this.needThreshold = needThreshold;
        this.attentionCycleThreshold = attentionCycleThreshold;
        this.pyramidGoals = new DesireHierarchyPyramid();
    }

    /**
     * Retrieves the potencial goals.
     *
     * @return The PotencialGoalStructure object representing the potential
     * goals.
     */
    public PotencialGoalStructure getPotencialGoals() {
        return potencialGoals;
    }

    /**
     * Sets the potential goals.
     *
     * @param potencialGoals The PotencialGoalStructure object representing the
     * potential goals.
     */
    public void setPotencialGoals(PotencialGoalStructure potencialGoals) {
        this.potencialGoals = potencialGoals;
    }

    /**
     * Retrieves the desire hierarchy pyramid.
     *
     * @return The DesireHierarchyPyramid object representing the desire
     * hierarchy pyramid.
     */
    public DesireHierarchyPyramid getPyramidGoals() {
        return pyramidGoals;
    }

    /**
     * Sets the desire hierarchy pyramid.
     *
     * @param pyramidGoals The DesireHierarchyPyramid object representing the
     * desire hierarchy pyramid.
     */
    public void setPyramidGoals(DesireHierarchyPyramid pyramidGoals) {
        this.pyramidGoals = pyramidGoals;
    }

    /**
     * Retrieves the duty threshold value.
     *
     * @return The duty threshold value.
     */
    public double getDutyThreshold() {
        return dutyThreshold;
    }

    /**
     * Sets the duty threshold value.
     *
     * @param dutyThreshold The duty threshold value.
     */
    public void setDutyThreshold(double dutyThreshold) {
        this.dutyThreshold = dutyThreshold;
    }

    /**
     * Retrieves the need threshold value.
     *
     * @return The need threshold value.
     */
    public double getNeedThreshold() {
        return needThreshold;
    }

    /**
     * Sets the need threshold value.
     *
     * @param needThreshold The need threshold value.
     */
    public void setNeedThreshold(double needThreshold) {
        this.needThreshold = needThreshold;
    }

    /**
     * Retrieves the opportunity threshold value.
     *
     * @return The opportunity threshold value.
     */
    public double getOportunityThreshold() {
        return oportunityThreshold;
    }

    /**
     * Sets the opportunity threshold value.
     *
     * @param oportunityThreshold The opportunity threshold value.
     */
    public void setOportunityThreshold(double oportunityThreshold) {
        this.oportunityThreshold = oportunityThreshold;
    }

    /**
     * Retrieves the requirement threshold value.
     *
     * @return The requirement threshold value.
     */
    public double getRequirementThreshold() {
        return requirementThreshold;
    }

    /**
     * Sets the requirement threshold value.
     *
     * @param requirementThreshold The requirement threshold value.
     */
    public void setRequirementThreshold(double requirementThreshold) {
        this.requirementThreshold = requirementThreshold;
    }

    /**
     * Retrieves the survival threshold value.
     *
     * @return The survival threshold value.
     */
    public double getSurvivalThreshold() {
        return survivalThreshold;
    }

    /**
     * Sets the survival threshold value.
     *
     * @param survivalThreshold The survival threshold value.
     */
    public void setSurvivalThreshold(double survivalThreshold) {
        this.survivalThreshold = survivalThreshold;
    }

    /**
     * Retrieves the attention cycle threshold value.
     *
     * @return The attention cycle threshold value.
     */
    public double getAttentionCycleThreshold() {
        return attentionCycleThreshold;
    }

    /**
     * Sets the attention cycle threshold value.
     *
     * @param attentionCycleThreshold The attention cycle threshold value.
     */
    public void setAttentionCycleThreshold(double attentionCycleThreshold) {
        this.attentionCycleThreshold = attentionCycleThreshold;
    }

    /**
     * Retrieves the main goal.
     *
     * @return The GoalBDI object representing the main goal.
     */
    public GoalBDI getMainGoal() {
        return mainGoal;
    }

    /**
     * Sets the main goal.
     *
     * @param mainGoal The GoalBDI object representing the main goal.
     */
    public void setMainGoal(GoalBDI mainGoal) {
        this.mainGoal = mainGoal;
    }

    /**
     * Retrieves the main goal threshold value.
     *
     * @return The main goal threshold value.
     */
    public double getMainGoalThreshold() {
        return mainGoalThreshold;
    }

    /**
     * Sets the main goal threshold value.
     *
     * @param mainGoalThreshold The main goal threshold value.
     */
    public void setMainGoalThreshold(double mainGoalThreshold) {
        this.mainGoalThreshold = mainGoalThreshold;
    }

    /**
     * Retrieves the intention goal.
     *
     * @return The GoalBDI object representing the intention goal.
     */
    public GoalBDI getIntention() {
        return intention;
    }

    /**
     * Sets the intention goal.
     *
     * @param intention The GoalBDI object representing the intention goal.
     */
    public void setIntention(GoalBDI intention) {
        this.intention = intention;
    }

    /**
     * Deletes an element from the pyramid using its id.
     *
     * @param goalId The id of the goal to be deleted.
     */
    public void deleteFromPyramid(long goalId) {
        for (Set<GoalBDI> set : this.getPyramidGoals().getGeneralHerarchyList()) {
            Iterator<GoalBDI> setIterator = set.iterator();
            while (setIterator.hasNext()) {
                GoalBDI currentElement = setIterator.next();
                if (currentElement.getId() == goalId) {
                    setIterator.remove();
                }
            }
        }
    }

    /**
     * Adds a potential goal to the appropriate list based on its type.
     *
     * @param goal The GoalBDI object representing the potential goal to be
     * added.
     */
    public void addPotentialGoal(GoalBDI goal) {
        switch (goal.getType()) {
            case SURVIVAL:
                this.potencialGoals.getSurvivalGoalsList().add(goal);
                break;
            case OBLIGATION:
                this.potencialGoals.getDutyGoalsList().add(goal);
                break;
            case DEVELOPMENT:
                this.potencialGoals.getOportunityGoalsList().add(goal);
                break;
            case SKILLSRESOURCES:
                this.potencialGoals.getRequirementGoalsList().add(goal);
                break;
            case SOCIAL:
                this.potencialGoals.getNeedGoalsList().add(goal);
                break;
            case LEISURE:
                this.potencialGoals.getAttentionCycleGoalsList().add(goal);
                break;
        }
    }
}
