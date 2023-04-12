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

import wpsPeasant.Goals.L2Duty.FamilyTimeGoalBDI;
import BESA.BDI.AgentStructuralModel.Agent.AgentBDI;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import java.util.ArrayList;
import java.util.List;
import rational.guards.InformationFlowGuard;
import wpsPeasant.Goals.L1Survival.*;
import wpsPeasant.Goals.L2Duty.*;
import wpsPeasant.Goals.L3Oportunity.*;
import wpsPeasant.Goals.L4Requirement.*;
import wpsPeasant.Goals.L5Needs.*;
import wpsPeasant.Goals.L6Attention.*;
import wpsPeasant.Utils.PeasantProfile;
import wpsPeasant.Utils.ReceiveMessagesFromWorldGuard;

/**
 *
 * @author jairo
 */
@SuppressWarnings("unchecked")
public class PeasantBDIAgent extends AgentBDI {

    private static final double TH = 0.91;

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
        //ReportBESA.info("PeasantAgent Iniciado");
    }

    private static List<GoalBDI> createGoals() {
        //ReportBESA.info("");
        
        List<GoalBDI> goals = new ArrayList();  
        
        //Survival
        goals.add(HaveAPurposeGoalBDI.buildGoal());
        goals.add(HaveAJobGoalBDI.buildGoal());
        goals.add(HaveLandGoalBDI.buildGoal());
        goals.add(HealthCareGoalBD.buildGoal());
        
        //Duty
        goals.add(GenerateIncomeGoalBDI.buildGoal());
        goals.add(PayDebtsGoalBDI.buildGoal());
        goals.add(MaintainHouseGoalBDI.buildGoal());
        goals.add(FamilyTimeGoalBDI.buildGoal());
        
        //Oportunity
        goals.add(ControlWeedsGoalBDI.buildGoal());
        goals.add(IrrigateCropsGoalBDI.buildGoal());
        goals.add(ManagePestsGoalBDI.buildGoal());
        goals.add(ProcessProductsGoalBDI.buildGoal());
        goals.add(TrainingGoalBDI.buildGoal());
        
        //Requirements
        goals.add(ObtainToolsGoalBDI.buildGoal());
        goals.add(ObtainSuppliesGoalBDI.buildGoal());
        goals.add(PrepareLandGoalBDI.buildGoal());
        goals.add(PlantCropsGoalBDI.buildGoal());
        goals.add(HarvestCropsGoalBDI.buildGoal());
        
        //Needs
        goals.add(AttendToLivestockGoalBDI.buildGoal());
        goals.add(CollaborateGoalBDI.buildGoal());
        goals.add(AskForCollaborationGoalBDI.buildGoal());
        
        // Attention
        goals.add(LeisureGoalBDI.buildGoal());
        goals.add(CommunicateGoalBDI.buildGoal());
        
        return goals;
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
            ReportBESA.info("\n\nPeasant Heart Beat\n");
        } catch (ExceptionBESA e) {
            ReportBESA.error(e);
        }
    }

}
