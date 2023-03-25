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
package wpsPeasant.Activities.L1Survival;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import BESA.Log.ReportBESA;
import rational.RationalRole;
import rational.mapping.Believes;
import wpsPeasant.Agent.PeasantBDIAgentBelieves;

/**
 *
 * @author jairo
 */
public class HaveLandGoalBDI extends GoalBDI {

    /**
     *
     * @param id
     * @param role
     * @param description
     * @param type
     */
    public HaveLandGoalBDI(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        ReportBESA.info("");
    }

    /**
     *
     * @param believes
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        ReportBESA.info("");
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
        ReportBESA.info("");
        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
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
        ReportBESA.info("");
        //"(0.3 * Money) + (0.2 * FarmSize) + (0.1 * Communications) + (0.4 * PeasantQualityFactor)"
        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
        /*return (0.3 * believes.getPeasantProfile().getMoney()
                + 0.2 * believes.getPeasantProfile().getFarmSize()
                + 0.1 * believes.getPeasantProfile().getCommunications()
                + 0.4 * believes.getPeasantProfile().getPeasantQualityFactor());*/
        if (believes.getPeasantProfile().getHealth() > 0) {
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
        ReportBESA.info("");
        return 0.8;
    }

    /**
     *
     * @param agentStatus
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        ReportBESA.info("");
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
        ReportBESA.info("");
        PeasantBDIAgentBelieves believes = (PeasantBDIAgentBelieves) parameters;
        return believes.getPeasantProfile().haveAFarm();
    }

}
