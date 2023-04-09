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
package wpsPeasant.Goals.L4requirement;

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
public class ControlWeedsGoalBDI extends GoalBDI {

    /**
     *
     * @return
     */
    public static ControlWeedsGoalBDI buildGoal() {
        ControlWeedsTask controlWeedsTask = new ControlWeedsTask();
        Plan controlWeedsPlan = new Plan();
        controlWeedsPlan.addTask(controlWeedsTask);
        RationalRole controlWeedsRole = new RationalRole(
                "controlWeedsTask",
                controlWeedsPlan);
        ControlWeedsGoalBDI controlWeedsGoalBDI = new ControlWeedsGoalBDI(
                wpsControl.getPlanID(),
                controlWeedsRole,
                "controlWeedsTask",
                GoalBDITypes.REQUIREMENT);
        return controlWeedsGoalBDI;
    }

    /**
     *
     * @param id
     * @param role
     * @param description
     * @param type
     */
    public ControlWeedsGoalBDI(long id, RationalRole role, String description, GoalBDITypes type) {
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
        /*ReportBESA.info("");
        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
        if (believes.getPeasantProfile().getFarmReady() < 1) {
            return 1;
        } else {
            return 0;
        }*/
        return 0;
    }

    /**
     *
     * @param parameters
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public double detectGoal(Believes parameters) throws KernellAgentEventExceptionBESA {
        /*PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
        ReportBESA.info("PlantingSeason=" + believes.getPeasantProfile().isPlantingSeason());
        if (believes.getPeasantProfile().isPlantingSeason()) {
            return 1;
        } else {
            return 0;
        }*/
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
        return 0;
        /*PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
        if (believes.getPeasantProfile().isBusy()) {
            return 0;
        } else {
            return 1;
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
        believes.getPeasantProfile().setGrowingSeason(true);
        // @TODO: ajustar a control de maleza, no de plagas
        return believes.getPeasantProfile().getCropHealth() == 1;
    }

}
