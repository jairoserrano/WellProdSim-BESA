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
package wpsPeasantFamily.Goals.L5Social;

import wpsPeasantFamily.Tasks.L5Social.LookForCollaborationTask;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import BESA.Log.ReportBESA;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsActivator.wpsStart;

/**
 *
 * @author jairo
 */
public class LookForCollaborationGoal extends GoalBDI {

    /**
     *
     * @return
     */
    public static LookForCollaborationGoal buildGoal() {
        LookForCollaborationTask askForCollaborationTask = new LookForCollaborationTask();
        Plan askForCollaborationPlan = new Plan();
        askForCollaborationPlan.addTask(askForCollaborationTask);
        RationalRole askForCollaborationRole = new RationalRole(
                "askForCollaborationTask",
                askForCollaborationPlan);
        LookForCollaborationGoal askForCollaborationGoalBDI = new LookForCollaborationGoal(
                wpsStart.getPlanID(),
                askForCollaborationRole,
                "askForCollaborationTask",
                GoalBDITypes.SOCIAL);
        return askForCollaborationGoalBDI;
    }

    /**
     *
     * @param id
     * @param role
     * @param description
     * @param type
     */
    public LookForCollaborationGoal(long id, RationalRole role, String description, GoalBDITypes type) {
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
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        if (believes.getPeasantProfile().getNeighbors() > 0) {
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
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        //ReportBESA.info("FamilyTimeAvailability=" + believes.getPeasantProfile().getFamilyTimeAvailability());
        /*if (believes.getPeasantProfile().getFamilyTimeAvailability() > 0) {
            return 0;
        } else {
            return 1;
        }*/
        //@TODO: Calcular si hace falta ayuda o no
        return 0;
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
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        return 1;
        /*if (believes.getPeasantProfile().getHealth() > 0 
        && believes.getPeasantProfile().haveTimeAvailable(TimeConsumedBy.WasteTimeAndResourcesGoal)) {
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
        //ReportBESA.info("");
        return false;
    }

}
