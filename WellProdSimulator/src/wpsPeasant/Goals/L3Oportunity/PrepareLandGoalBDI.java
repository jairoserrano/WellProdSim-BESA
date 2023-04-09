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
package wpsPeasant.Goals.L3Oportunity;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import BESA.Log.ReportBESA;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;
import wpsPeasant.Agent.PeasantBDIAgentBelieves;
import wpsSimulator.wpsControl;

/**
 *
 * @author jairo
 */
public class PrepareLandGoalBDI extends GoalBDI {

    /**
     *
     * @return
     */
    public static PrepareLandGoalBDI buildGoal() {
        PrepareLandTask prepareLandTask = new PrepareLandTask();
        Plan prepareLandPlan = new Plan();
        prepareLandPlan.addTask(prepareLandTask);
        RationalRole prepareLandRole = new RationalRole(
                "prepareLandTask",
                prepareLandPlan);
        PrepareLandGoalBDI prepareLandGoalBDI = new PrepareLandGoalBDI(
                wpsControl.getPlanID(),
                prepareLandRole,
                "prepareLandTask",
                GoalBDITypes.DUTY);
        return prepareLandGoalBDI;
    }

    /**
     *
     * @param id
     * @param role
     * @param description
     * @param type
     */
    public PrepareLandGoalBDI(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //ReportBESA.info("");
    }

    /**
     *
     * @param parameters
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public double evaluateViability(Believes parameters) throws KernellAgentEventExceptionBESA {
        ReportBESA.info("");
        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
        if (believes.getPeasantProfile().getTools() > 0
                && believes.getPeasantProfile().getSupplies() > 0) {
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
    public double detectGoal(Believes parameters) throws KernellAgentEventExceptionBESA {
        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
        //ReportBESA.info("PlantingSeason=" + believes.getPeasantProfile().isPlantingSeason());
        if (believes.getPeasantProfile().haveAFarm()) {
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
        //ReportBESA.info("");
        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
        if (believes.getPeasantProfile().isBusy()) {
            return 0;
        } else {
            return 1;
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
        //ReportBESA.info("");
        return 1;
    }

    /**
     *
     * @param agentStatus
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //ReportBESA.info("");
        return true;
    }

    /**
     *
     * @param parameters
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public boolean goalSucceeded(Believes parameters) throws KernellAgentEventExceptionBESA {
        //ReportBESA.info("");
        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
        //believes.getPeasantProfile().setGrowingSeason(true);
        return believes.getPeasantProfile().getFarmReady() == 1;
    }

}
