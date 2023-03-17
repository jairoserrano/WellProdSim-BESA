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
package wpsPeasant.Activities.L3Oportunity;

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
public class PeasantFarmingGoalBDI extends GoalBDI {

    public PeasantFarmingGoalBDI(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
    }

    @Override
    /**
     * Evaluar si se tienen los recursos
     */
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        PeasantBDIAgentBelieves blvs = (PeasantBDIAgentBelieves) believes;
        ReportBESA.debug(">>>>>>>> Evaluando " + this.getId() + " " + this.getType() + " Current Activity " + blvs.getCurrentActivity());
        if (blvs.getCurrentActivity() == PeasantActivityType.REST) {
            return 1;
        } else {
            return 0;
        }
    }

    /** validar si el goal se activa con un test sencillo, 
    *   por ejemplo epoca de siembra, recoger cosecha etc
    *   cambiar posiblemente a activateGoal
    *   qué se debe mirar en los belives para saber si puede activar la meta
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

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        return 1;
    }

    @Override
    /**
     * Revisar aquí si es de verdad evalua la contribución, 
     * Medir o evaluar la contribución, solo se llama despues de que la meta fue activada
     */
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        return 1;
    }

    /**
     * Cumple con la normatividad el agente
     * 
     */
    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        return false;
    }

}
