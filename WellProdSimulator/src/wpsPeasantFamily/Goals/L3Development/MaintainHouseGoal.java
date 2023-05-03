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

import wpsPeasantFamily.Tasks.L3Development.MaintainHouseTask;
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
import wpsPeasantFamily.Utils.TimeConsumedBy;

/**
 *
 * @author jairo
 */
public class MaintainHouseGoal extends GoalBDI {

    /**
     *
     * @return
     */
    public static MaintainHouseGoal buildGoal() {
        MaintainHouseTask maintainHouseTask = new MaintainHouseTask();
        Plan maintainHousePlan = new Plan();
        maintainHousePlan.addTask(maintainHouseTask);
        RationalRole maintainHouseRole = new RationalRole(
                "maintainHouseTask",
                maintainHousePlan);
        MaintainHouseGoal maintainHouseGoalBDI = new MaintainHouseGoal(
                wpsStart.getPlanID(),
                maintainHouseRole,
                "maintainHouseTask",
                GoalBDITypes.DEVELOPMENT);
        return maintainHouseGoalBDI;
    }

    /**
     *
     * @param id
     * @param role
     * @param description
     * @param type
     */
    public MaintainHouseGoal(long id, RationalRole role, String description, GoalBDITypes type) {
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
        ReportBESA.info("getTools=" + believes.getPeasantProfile().getTools());
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
        ReportBESA.info("getHousingCondition=" + believes.getPeasantProfile().getHousingCondition());
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
        //ReportBESA.info("");
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        ReportBESA.info("isBusy=" + believes.getPeasantProfile().isBusy());
        if (believes.getPeasantProfile().isBusy() 
                && believes.getPeasantProfile().haveTimeAvailable(TimeConsumedBy.MaintainHouse)) {
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
     * @param stateBDI
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public boolean evaluateLegality(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        //ReportBESA.info(stateBDI.getMachineBDIParams().getPyramidGoals());
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) stateBDI.getBelieves();
        //ReportBESA.info("getHealth=" + believes.getPeasantProfile().getHealth());
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
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        return believes.getPeasantProfile().getHousingCondition() > 0;
    }

}
