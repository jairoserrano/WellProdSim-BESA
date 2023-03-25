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

import BESA.BDI.AgentStructuralModel.Agent.AgentBDI;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.GoalBDITypes;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import java.util.ArrayList;
import java.util.List;
import rational.RationalRole;
import rational.guards.InformationFlowGuard;
import rational.mapping.Plan;
import wpsPeasant.Activities.L1Survival.HaveAJobGoalBDI;
import wpsPeasant.Activities.L1Survival.HaveAJobTask;
import wpsPeasant.Activities.L1Survival.HaveAPurposeGoalBDI;
import wpsPeasant.Activities.L1Survival.HaveAPurposeTask;
import wpsPeasant.Activities.L1Survival.HaveLandGoalBDI;
import wpsPeasant.Activities.L1Survival.HaveLandTask;
import wpsPeasant.Activities.L2Duty.GenerateIncomeGoalBDI;
import wpsPeasant.Activities.L2Duty.GenerateIncomeTask;
import wpsPeasant.Activities.L2Duty.PayDebtsGoalBDI;
import wpsPeasant.Activities.L2Duty.PayDebtsTask;
import wpsPeasant.Activities.L6Attention.LeisureGoalBDI;
import wpsPeasant.Activities.L6Attention.LeisureTask;
import wpsPeasant.Utils.PeasantProfile;
import wpsPeasant.Utils.ReceiveMessagesFromWorldGuard;
import wpsSimulator.wpsControl;

/**
 *
 * @author jairo
 */
@SuppressWarnings("unchecked")
public class PeasantBDIAgent extends AgentBDI {

    private static final double TH = 0.91;

    private static List<GoalBDI> createGoals() {
        ReportBESA.info("");
        List<GoalBDI> goals = new ArrayList();

        HaveAPurposeTask peasantHaveAPurposeTask = new HaveAPurposeTask();
        Plan PeasantHaveAPurposePlan = new Plan();
        PeasantHaveAPurposePlan.addTask(peasantHaveAPurposeTask);
        RationalRole HaveAPurposeRole = new RationalRole(
                "PeasantHaveAPurposeTask",
                PeasantHaveAPurposePlan);
        HaveAPurposeGoalBDI peasantHaveAPurposeGoalBDI = new HaveAPurposeGoalBDI(
                wpsControl.getPlanID(),
                HaveAPurposeRole,
                "PeasantHaveAPurposeTask",
                GoalBDITypes.SURVIVAL);
        goals.add(peasantHaveAPurposeGoalBDI);

        HaveAJobTask peasantHaveAJobTask = new HaveAJobTask();
        Plan peasantHaveAJobPlan = new Plan();
        peasantHaveAJobPlan.addTask(peasantHaveAJobTask);
        RationalRole peasantHaveAJobRole = new RationalRole(
                "PeasantHaveAJobTask",
                peasantHaveAJobPlan);
        HaveAJobGoalBDI peasantHaveAJobGoalBDI = new HaveAJobGoalBDI(
                wpsControl.getPlanID(),
                peasantHaveAJobRole,
                "PeasantHaveAJobTask",
                GoalBDITypes.SURVIVAL);
        goals.add(peasantHaveAJobGoalBDI);

        HaveLandTask peasantHaveLandTask = new HaveLandTask();
        Plan peasantHaveLandPlan = new Plan();
        peasantHaveLandPlan.addTask(peasantHaveLandTask);
        RationalRole peasantHaveLandRole = new RationalRole(
                "peasantHaveLandTask",
                peasantHaveLandPlan);
        HaveLandGoalBDI peasantHaveLandGoalBDI = new HaveLandGoalBDI(
                wpsControl.getPlanID(),
                peasantHaveLandRole,
                "PeasantHaveLandTask",
                GoalBDITypes.SURVIVAL);
        goals.add(peasantHaveLandGoalBDI);

        GenerateIncomeTask peasantGenerateIncomeTask = new GenerateIncomeTask();
        Plan peasantGenerateIncomePlan = new Plan();
        peasantHaveLandPlan.addTask(peasantGenerateIncomeTask);
        RationalRole peasantGenerateIncomeRole = new RationalRole(
                "peasantGenerateIncomeTask",
                peasantGenerateIncomePlan);
        GenerateIncomeGoalBDI peasantGenerateIncomeGoalBDI = new GenerateIncomeGoalBDI(
                wpsControl.getPlanID(),
                peasantGenerateIncomeRole,
                "peasantGenerateIncomeTask",
                GoalBDITypes.DUTY);
        goals.add(peasantGenerateIncomeGoalBDI);

        PayDebtsTask peasantPayDebtsTask = new PayDebtsTask();
        Plan peasantPayDebtsPlan = new Plan();
        peasantHaveLandPlan.addTask(peasantPayDebtsTask);
        RationalRole peasantPayDebtsRole = new RationalRole(
                "peasantPayDebtsTaks",
                peasantPayDebtsPlan);
        PayDebtsGoalBDI peasantPayDebtsGoalBDI = new PayDebtsGoalBDI(
                wpsControl.getPlanID(),
                peasantPayDebtsRole,
                "peasantPayDebtsTaks",
                GoalBDITypes.DUTY);
        goals.add(peasantPayDebtsGoalBDI);

        LeisureTask peasantLeisureTask = new LeisureTask();
        Plan peasantLeisurePlan = new Plan();
        peasantHaveLandPlan.addTask(peasantLeisureTask);
        RationalRole peasantLeisureRole = new RationalRole(
                "peasantLeisureTask",
                peasantLeisurePlan);
        LeisureGoalBDI peasantLeisureGoalBDI = new LeisureGoalBDI(
                wpsControl.getPlanID(),
                peasantLeisureRole,
                "peasantLeisureTask",
                GoalBDITypes.ATTENTION_CYCLE);
        goals.add(peasantLeisureGoalBDI);

        /*PeasantFarmingTask peasantFarmingTask = new PeasantFarmingTask();
        Plan FarmingPlan = new Plan();
        FarmingPlan.addTask(peasantFarmingTask);
        RationalRole FarmingPlanRole = new RationalRole("peasantFarmingTask", FarmingPlan);
        PeasantFarmingGoalBDI peasantFarmingGoalBDI = new PeasantFarmingGoalBDI(wpsControl.getPlanID(), FarmingPlanRole, "peasantFarmingTask", GoalBDITypes.SURVIVAL);
        goals.add(peasantFarmingGoalBDI);

        PeasantHarvestingTask peasantHarvestingTask = new PeasantHarvestingTask();
        Plan HarvestingPlan = new Plan();
        HarvestingPlan.addTask(peasantHarvestingTask);
        RationalRole HarvestingPlanRole = new RationalRole("peasantHarvestingTask", HarvestingPlan);
        PeasantHarvestingGoalBDI peasantHarvestingGoalBDI = new PeasantHarvestingGoalBDI(wpsControl.getPlanID(), HarvestingPlanRole, "peasantHarvestingTask", GoalBDITypes.NEED);
        goals.add(peasantHarvestingGoalBDI);

        PeasantIrrigatingTask peasantIrrigatingTask = new PeasantIrrigatingTask();
        Plan IrrigatingPlan = new Plan();
        IrrigatingPlan.addTask(peasantIrrigatingTask);
        RationalRole IrrigatingPlanRole = new RationalRole("peasantIrrigatingTask", IrrigatingPlan);
        PeasantIrrigatingGoalBDI peasantIrrigatingGoalBDI = new PeasantIrrigatingGoalBDI(wpsControl.getPlanID(), IrrigatingPlanRole, "PeasantIrrigatingTask", GoalBDITypes.OPORTUNITY);
        goals.add(peasantIrrigatingGoalBDI);
         */
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

    private static PeasantBDIAgentBelieves createBelieves(PeasantProfile profile) {
        return new PeasantBDIAgentBelieves(profile);
    }

    /**
     *
     * @param alias
     * @param peasantProfile
     * @throws ExceptionBESA
     */
    public PeasantBDIAgent(String alias, PeasantProfile peasantProfile) throws ExceptionBESA {
        super(alias, createBelieves(peasantProfile), createGoals(), TH, createStruct(new StructBESA()));
        ReportBESA.info("PeasantAgent Iniciado");
        ReportBESA.info(alias + peasantProfile);
    }

    /**
     *
     */
    @Override
    public void setupAgentBDI() {
    }

    /**
     *
     */
    @Override
    public void shutdownAgentBDI() {
    }

    /**
     *
     */
    public void BDIPulse() {
        try {
            AgHandlerBESA agHandler = AdmBESA.getInstance().getHandlerByAlias(this.getAlias());
            EventBESA eventBesa = new EventBESA(InformationFlowGuard.class.getName(), null);
            agHandler.sendEvent(eventBesa);
            ReportBESA.info("BDIPulse InformationFlowGuard enviado");
        } catch (ExceptionBESA e) {
            ReportBESA.error(e);
        }
    }

}
