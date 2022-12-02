/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import Peasant.Utils.PeasantActivity;
import java.util.ArrayList;
import java.util.List;
import rational.RationalRole;
import rational.mapping.Believes;
import rational.mapping.Plan;
import rational.mapping.Task;
import wpsmicro.SimulatorMicro;

/**
 *
 * @author jairo
 */
public class PeasantAgroGoal extends GoalBDI {

    private static String descrip = "AgroGoal";

    public static PeasantAgroGoal buildGoal() {
        PeasantFarmingTask farming = new PeasantFarmingTask();
        PeasantHarvestTask harvest = new PeasantHarvestTask();

        Plan rolePlan = new Plan();

        List<Task> tarea;
        rolePlan.addTask(farming);
        tarea = new ArrayList<>();
        tarea.add(farming);
        rolePlan.addTask(farming, tarea);
        rolePlan.addTask(harvest, tarea);

        RationalRole peasantFarmingRole = new RationalRole(descrip, rolePlan);
        PeasantAgroGoal b = new PeasantAgroGoal(SimulatorMicro.getPlanID(), peasantFarmingRole, descrip, GoalBDITypes.SURVIVAL);
        return b;
    }

    public PeasantAgroGoal(long id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta MusicoTerapia created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta peasantFarmingRole detectGoal");
        PeasantAgentBelieves blvs = (PeasantAgentBelieves) believes;
        System.out.println("EmocionPredominante: " + blvs.getPeasantAgentBelieveEmotionalState().getEmocionPredominante());

        if (blvs.getPeasantAgentBelieveEmotionalState().getEmocionPredominante() < 0 && blvs.getPeasantProfile().getProfile().getFarmerProfile().getFast() <= 5) {
            return 0.4 + (blvs.getPeasantAgentBelieveActivityState().getGustoActividad(PeasantActivity.FARMING) * 0.6);
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        double valor = 1;

        /*for (Actxpreferencia act : listaAct) {
            if (act.getActividadpwa().getNombre().equalsIgnoreCase(ResPwAActivity.MUSICOTERAPIA.toString())) {
                valor = act.getGusto();
            }
        }*/
        return valor;

    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta MusicoTerapia predictResultUnlegability");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        PeasantAgentBelieves blvs = (PeasantAgentBelieves) believes;
        if ((System.currentTimeMillis() - blvs.getPeasantAgentBelieveActivityState().calcTiempoActividad()) >= 300 && blvs.getPeasantAgentBelieveEmotionalState().getEmocionPredominante() > 0) {
            return true;
        }
        return false;
    }
}
