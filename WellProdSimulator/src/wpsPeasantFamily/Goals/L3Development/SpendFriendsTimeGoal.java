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

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsActivator.wpsStart;
import wpsPeasantFamily.Tasks.L3Development.SpendFriendsTimeTask;
import wpsPeasantFamily.Utils.TimeConsumedBy;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class SpendFriendsTimeGoal extends GoalBDI {

    /**
     *
     * @return
     */
    public static SpendFriendsTimeGoal buildGoal() {
        SpendFriendsTimeTask spendFriendsTimeTask = new SpendFriendsTimeTask();
        Plan spendFriendsTimePlan = new Plan();
        spendFriendsTimePlan.addTask(spendFriendsTimeTask);
        RationalRole spendFriendsTimeRole = new RationalRole(
                "spendFriendsTimeTask",
                spendFriendsTimePlan);
        SpendFriendsTimeGoal spendFriendsTimeGoal = new SpendFriendsTimeGoal(
                wpsStart.getPlanID(),
                spendFriendsTimeRole,
                "spendFriendsTimeTask",
                GoalBDITypes.DEVELOPMENT);
        return spendFriendsTimeGoal;
    }

    /**
     *
     * @param id
     * @param role
     * @param description
     * @param type
     */
    public SpendFriendsTimeGoal(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //wpsReport.info("");
    }

    /**
     *
     * @param parameters
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public double evaluateViability(Believes parameters) throws KernellAgentEventExceptionBESA {
        //wpsReport.info("");
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        //wpsReport.info("getTools=" + believes.getPeasantProfile().getTools());
        if (believes.getPeasantProfile().getTools() > 0) {
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
        wpsReport.info("getHousingCondition=" + believes.getPeasantProfile().getHousingCondition());
        if (believes.getPeasantProfile().getHousingCondition() < 1) {
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
        //wpsReport.info("");
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        wpsReport.info("isBusy=" + believes.getPeasantProfile().isBusy());
        if (believes.getPeasantProfile().isFree()
                && believes.getPeasantProfile().haveTimeAvailable(
                        TimeConsumedBy.SpendFriendsTime
                )) {
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
        //wpsReport.info("");
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
        //wpsReport.info(stateBDI.getMachineBDIParams().getPyramidGoals());
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) stateBDI.getBelieves();
        //wpsReport.info("getHealth=" + believes.getPeasantProfile().getHealth());
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
        //wpsReport.info("");
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        return believes.getPeasantProfile().getHousingCondition() > 0;
    }

}
