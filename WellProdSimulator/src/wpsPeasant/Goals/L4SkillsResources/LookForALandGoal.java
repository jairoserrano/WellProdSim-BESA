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
package wpsPeasant.Goals.L4SkillsResources;

import wpsPeasant.Tasks.L4SkillsResources.LookForALandTask;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import BESA.Log.ReportBESA;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;
import wpsPeasant.Agent.PeasantBDIAgentBelieves;
import wpsSimulator.wpsStart;

/**
 *
 * @author jairo
 */
public class LookForALandGoal extends GoalBDI {

    /**
     *
     * @return
     */
    public static LookForALandGoal buildGoal() {
        LookForALandTask peasantHaveLandTask = new LookForALandTask();
        Plan peasantHaveLandPlan = new Plan();
        peasantHaveLandPlan.addTask(peasantHaveLandTask);
        RationalRole peasantHaveLandRole = new RationalRole(
                "peasantHaveLandTask",
                peasantHaveLandPlan);
        LookForALandGoal peasantHaveLandGoalBDI = new LookForALandGoal(
                wpsStart.getPlanID(),
                peasantHaveLandRole,
                "PeasantHaveLandTask",
                GoalBDITypes.SURVIVAL);
        return peasantHaveLandGoalBDI;
    }

    /**
     *
     * @param id
     * @param role
     * @param description
     * @param type
     */
    public LookForALandGoal(long id, RationalRole role, String description, GoalBDITypes type) {
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
        if (believes.getPeasantProfile().getHealth() > 0) {
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
        ReportBESA.info("HaveAFarm=" + believes.getPeasantProfile().haveAFarm());
        if (believes.getPeasantProfile().haveAFarm()) {
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
    public double evaluatePlausibility(Believes parameters) throws KernellAgentEventExceptionBESA {
        //ReportBESA.info("");
        //"(0.3 * Money) + (0.2 * FarmSize) + (0.1 * Communications) + (0.4 * PeasantQualityFactor)"
        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
        /*return (0.3 * believes.getPeasantProfile().getMoney()
                + 0.2 * believes.getPeasantProfile().getFarmSize()
                + 0.1 * believes.getPeasantProfile().getCommunications()
                + 0.4 * believes.getPeasantProfile().getPeasantQualityFactor());*/
        /*if (believes.getPeasantProfile().getHealth() > 0) {
            return 1;
        } else {
            return 0;
        }*/
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
        //ReportBESA.info("");
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
        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
        return believes.getPeasantProfile().haveAFarm();
    }

}
