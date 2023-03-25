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
package wpsPeasant.Activities.L4requirement;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import BESA.Log.ReportBESA;
import wpsPeasant.Agent.PeasantBDIAgentBelieves;
import wpsPeasant.Utils.PeasantActivityType;
import rational.RationalRole;
import rational.mapping.Believes;

/**
 *
 * @author jairo
 */
public class PeasantIrrigatingGoalBDI extends GoalBDI {

    /**
     *
     * @param id
     * @param role
     * @param description
     * @param type
     */
    public PeasantIrrigatingGoalBDI(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
    }

    /**
     *
     * @param believes
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        PeasantBDIAgentBelieves blvs = (PeasantBDIAgentBelieves) believes;
        //ReportBESA.debug(">>>>>>>> Evaluando " + this.getId() + " " + this.getType() + " Current Activity " + blvs.getCurrentActivity());
        /*if (blvs.getCurrentActivity() == PeasantActivityType.IRRIGATE) {
            return 1;
        } else {
            return 0;
        }*/
        return 1;
    }

    /**
     *
     * @param believes
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        /*PeasantBDIAgentBelieves blvs = (PeasantBDIAgentBelieves) believes;
        if (blvs.getCurrentActivity() == PeasantActivityType.IRRIGATING) {
            return 0;
        } else {
            return 1;
        }*/
        return 1;
    }

    /**
     *
     * @param believes
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
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
        return true;
    }

    /**
     *
     * @param believes
     * @return
     * @throws KernellAgentEventExceptionBESA
     */
    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        return false;
    }

}
