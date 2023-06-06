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
package wpsPeasantFamily.Goals.L3Development;

import wpsPeasantFamily.Tasks.L3Development.PlantCropTask;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsActivator.wpsStart;
import wpsPeasantFamily.Data.ResourceNeededType;
import wpsPeasantFamily.Data.SeasonType;
import wpsPeasantFamily.Data.TimeConsumedBy;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class PlantCropGoal extends GoalBDI {

    /**
     *
     * @return PlantCropGoal
     */
    public static PlantCropGoal buildGoal() {
        PlantCropTask plantCropTask = new PlantCropTask();
        Plan plantCropPlan = new Plan();
        plantCropPlan.addTask(plantCropTask);
        RationalRole plantCropRole = new RationalRole(
                "PlantCropTask",
                plantCropPlan);
        PlantCropGoal plantCropGoal = new PlantCropGoal(
                wpsStart.getPlanID(),
                plantCropRole,
                "PlantCropTask",
                GoalBDITypes.SKILLSRESOURCES);
        return plantCropGoal;
    }

    /**
     *
     * @param id
     * @param role
     * @param description
     * @param type
     */
    public PlantCropGoal(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
    }

    /**
     *
     * @param parameters
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public double evaluateViability(Believes parameters) throws KernellAgentEventExceptionBESA {
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        if (!believes.getPriceList().isEmpty()
                && believes.getPeasantProfile().getTools() > 0
                && believes.getPeasantProfile().getSeeds() > 0
                && believes.getPeasantProfile().getWaterAvailable() > 0) {
            wpsReport.warn("PLANTANDO VIABLE");
            return 1;
        } else {
            if (believes.getPeasantProfile().getTools() <= 0){
                believes.setCurrentResourceNeededType(ResourceNeededType.TOOLS);
            }
            if (believes.getPeasantProfile().getSeeds() <= 0){
                believes.setCurrentResourceNeededType(ResourceNeededType.SEEDS);
            }
            if (believes.getPeasantProfile().getWaterAvailable() <= 0){
                believes.setCurrentResourceNeededType(ResourceNeededType.WATER);
            }
            return 0;
        }
    }

    /**
     *
     * @param parameters
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public double detectGoal(Believes parameters) throws KernellAgentEventExceptionBESA {
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        if (believes.getCurrentSeason() == SeasonType.PLANTING) {
            //wpsReport.warn("PLANTANDO DETECTADO");
            return 1;
        } else {
            return 0;
        }
    }

    /**
     *
     * @param parameters
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public double evaluatePlausibility(Believes parameters) throws KernellAgentEventExceptionBESA {
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        if (believes.haveTimeAvailable(TimeConsumedBy.PlantCropTask)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     *
     * @param stateBDI
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        return 1;
    }

    /**
     *
     * @param stateBDI
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public boolean evaluateLegality(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) stateBDI.getBelieves();
        return believes.getPeasantProfile().getHealth() > 0;
    }

    /**
     *
     * @param parameters
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public boolean goalSucceeded(Believes parameters) throws KernellAgentEventExceptionBESA {
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        return believes.getCurrentSeason() == SeasonType.GROWING;
    }

}
