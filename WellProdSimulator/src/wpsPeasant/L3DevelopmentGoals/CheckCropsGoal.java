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
package wpsPeasant.L3DevelopmentGoals;

import wpsPeasant.Tasks.AskForCollaborationTask;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import BESA.Log.ReportBESA;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;
import wpsPeasant.Agent.PeasantBDIAgentBelieves;
import wpsPeasant.Tasks.CheckCropsTask;
import wpsSimulator.wpsStart;

/**
 *
 * @author jairo
 */
public class CheckCropsGoal extends GoalBDI {

    /**
     *
     * @return
     */
    public static CheckCropsGoal buildGoal() {
        CheckCropsTask checkCropsTask = new CheckCropsTask();
        Plan checkCropsPlan = new Plan();
        checkCropsPlan.addTask(checkCropsTask);
        RationalRole checkCropsRole = new RationalRole(
                "checkCropsTask",
                checkCropsPlan);
        CheckCropsGoal checkCropsGoalBDI = new CheckCropsGoal(
                wpsStart.getPlanID(),
                checkCropsRole,
                "checkCropsTask",
                GoalBDITypes.SOCIAL);
        return checkCropsGoalBDI;
    }

    /**
     *
     * @param id
     * @param role
     * @param description
     * @param type
     */
    public CheckCropsGoal(long id, RationalRole role, String description, GoalBDITypes type) {
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
     * @param parameters
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public double detectGoal(Believes parameters) throws KernellAgentEventExceptionBESA {
        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
        //ReportBESA.info("FamilyTimeAvailability=" + believes.getPeasantProfile().getFamilyTimeAvailability());
        if (believes.getPeasantProfile().isGrowingSeason()
                || believes.getPeasantProfile().isHarverstSeason()) {
            return 1;
        } else {
            return 1;
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
        return 1;
        /*if (believes.getPeasantProfile().getHealth() > 0.0) {
            return 1;
        } else {
            return 0;
        }*/
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
     * @param stateBDI
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public boolean evaluateLegality(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //ReportBESA.info(stateBDI.getMachineBDIParams().getPyramidGoals());
        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) stateBDI.getBelieves();
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
        //ReportBESA.info("");
        return true;
    }

}
