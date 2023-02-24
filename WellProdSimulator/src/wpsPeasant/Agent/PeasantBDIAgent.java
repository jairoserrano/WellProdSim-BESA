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
package wpsPeasant.Agent;

import wpsPeasant.Utils.ReceiveMessagesFromWorldGuard;
import wpsPeasant.Activities.L3Oportunity.PeasantHarvestingGoalBDI;
import wpsPeasant.Activities.L3Oportunity.PeasantFarmingGoalBDI;
import wpsPeasant.Activities.L3Oportunity.PeasantHarvestingTask;
import wpsPeasant.Activities.L3Oportunity.PeasantFarmingTask;
import BESA.BDI.AgentStructuralModel.Agent.AgentBDI;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import wpsPeasant.Activities.L4requirement.PeasantIrrigatingGoalBDI;
import wpsPeasant.Activities.L4requirement.PeasantIrrigatingTask;
import wpsPeasant.Utils.PeasantPurpose;
import wpsSimulator.StartSimpleSimulator;
import java.util.ArrayList;
import java.util.List;
import rational.RationalRole;
import rational.guards.InformationFlowGuard;
import rational.mapping.Plan;

/**
 *
 * @author jairo
 */
public class PeasantBDIAgent extends AgentBDI {

    private static final double TH = 0.91;

    public PeasantBDIAgent(String alias, PeasantPurpose purpose) throws ExceptionBESA {
        super(alias, createBelieves(purpose.toString()), createGoals(), TH, createStruct(new StructBESA()));
        ReportBESA.info("PeasantAgent Iniciado");
    }

    @Override
    public void setupAgentBDI() {
    }

    @Override
    public void shutdownAgentBDI() {
    }

    private static List<GoalBDI> createGoals() {
        List<GoalBDI> goals = new ArrayList();

        PeasantFarmingTask peasantFarmingTask = new PeasantFarmingTask();
        Plan FarmingPlan = new Plan();
        FarmingPlan.addTask(peasantFarmingTask);
        RationalRole FarmingPlanRole = new RationalRole("peasantFarmingTask", FarmingPlan);
        PeasantFarmingGoalBDI peasantFarmingGoalBDI = new PeasantFarmingGoalBDI(StartSimpleSimulator.getPlanID(), FarmingPlanRole, "peasantFarmingTask", GoalBDITypes.SURVIVAL);
        goals.add(peasantFarmingGoalBDI);

        PeasantHarvestingTask peasantHarvestingTask = new PeasantHarvestingTask();
        Plan HarvestingPlan = new Plan();
        HarvestingPlan.addTask(peasantHarvestingTask);
        RationalRole HarvestingPlanRole = new RationalRole("peasantHarvestingTask", HarvestingPlan);
        PeasantHarvestingGoalBDI peasantHarvestingGoalBDI = new PeasantHarvestingGoalBDI(StartSimpleSimulator.getPlanID(), HarvestingPlanRole, "peasantHarvestingTask", GoalBDITypes.NEED);
        goals.add(peasantHarvestingGoalBDI);

        PeasantIrrigatingTask peasantIrrigatingTask = new PeasantIrrigatingTask();
        Plan IrrigatingPlan = new Plan();
        IrrigatingPlan.addTask(peasantIrrigatingTask);
        RationalRole IrrigatingPlanRole = new RationalRole("peasantIrrigatingTask", IrrigatingPlan);
        PeasantIrrigatingGoalBDI peasantIrrigatingGoalBDI = new PeasantIrrigatingGoalBDI(StartSimpleSimulator.getPlanID(), IrrigatingPlanRole, "PeasantIrrigatingTask", GoalBDITypes.OPORTUNITY);
        goals.add(peasantIrrigatingGoalBDI);

        ReportBESA.info("Meta Inicial PeasantHarvestingGoal created");
        return goals;
    }

    private static StructBESA createStruct(StructBESA structBESA) throws ExceptionBESA {
        structBESA.addBehavior("startReachingGoalsSimpleGuard");
        structBESA.bindGuard("startReachingGoalsSimpleGuard", startReachingGoalsSimpleGuard.class);
        structBESA.addBehavior("startReachingGoalsGuard");
        structBESA.bindGuard("startReachingGoalsGuard", startReachingGoalsGuard.class);
        structBESA.addBehavior("ReceiveMessagesFromWorldGuard");
        structBESA.bindGuard("ReceiveMessagesFromWorldGuard", ReceiveMessagesFromWorldGuard.class);
        return structBESA;
    }

    private static PeasantBDIAgentBelieves createBelieves(String alias) {
        return new PeasantBDIAgentBelieves(alias);
    }

    public void BDIPulse() {
        try {
            AgHandlerBESA agHandler = AdmBESA.getInstance().getHandlerByAlias(this.getAlias());
            EventBESA eventBesa = new EventBESA(InformationFlowGuard.class.getName(), null);
            agHandler.sendEvent(eventBesa);
            //ReportBESA.info("BDIPulse InformationFlowGuard enviado");
        } catch (Exception e) {
            ReportBESA.error(e);
        }
    }

}
