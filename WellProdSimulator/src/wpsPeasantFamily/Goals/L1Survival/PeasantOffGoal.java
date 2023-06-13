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
package wpsPeasantFamily.Goals.L1Survival;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsActivator.wpsStart;
import wpsPeasantFamily.Tasks.L1Survival.PeasantOffTask;
import wpsViewer.Agent.wpsReport;

/**
 *
 *
 */
public class PeasantOffGoal extends GoalBDI {

    /**
     *
     * @return
     */
    public static PeasantOffGoal buildGoal() {
        PeasantOffTask peasantOffTask = new PeasantOffTask();
        Plan peasantOffPlan = new Plan();
        peasantOffPlan.addTask(peasantOffTask);
        RationalRole peasantOffRole = new RationalRole(
                "peasantOffTask",
                peasantOffPlan);
        PeasantOffGoal peasantOffGoal = new PeasantOffGoal(
                wpsStart.getPlanID(),
                peasantOffRole,
                "peasantOffTask",
                GoalBDITypes.SURVIVAL);
        return peasantOffGoal;
    }

    /**
     *
     * @param id
     * @param role
     * @param description
     * @param type
     */
    public PeasantOffGoal(long id, RationalRole role, String description, GoalBDITypes type) {
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
        return 1;
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
        if (believes.getPeasantProfile().getHealth() <= 0) {
            //wpsReport.debug("Detectó agente Off");
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
        return 1;
    }

    /**
     *
     * @param stateBDI
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //wpsReport.info("evaluateContribution " + stateBDI.getMachineBDIParams().getAttentionCycleThreshold());
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
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        return !believes.isNewDay();
    }

}
