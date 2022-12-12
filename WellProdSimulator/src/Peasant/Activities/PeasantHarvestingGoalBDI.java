package Peasant.Activities;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import BESA.Log.ReportBESA;
import Peasant.PeasantBDIAgentBelieves;
import Peasant.Utils.PeasantActivityType;
import rational.RationalRole;
import rational.mapping.Believes;

/**
 *
 * @author jairo
 */
public class PeasantHarvestingGoalBDI extends GoalBDI {

    public PeasantHarvestingGoalBDI(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        PeasantBDIAgentBelieves blvs = (PeasantBDIAgentBelieves) believes;
        ReportBESA.debug(">>>>>>>> Evaluando " + this.getId() + " " + this.getType() + " Current Activity " + blvs.getCurrentActivity());
        if (blvs.getCurrentActivity() == PeasantActivityType.HARVESTING) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        /*PeasantBDIAgentBelieves blvs = (PeasantBDIAgentBelieves) believes;
        if (blvs.getCurrentActivity() == PeasantActivityType.FARMING) {
            return 0;
        } else {
            return 1;
        }*/
        return 1;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        return 1;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        return false;
    }

}
