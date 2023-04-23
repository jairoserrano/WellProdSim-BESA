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

import wpsPeasant.L1SurvivalGoals.SeekPurposeGoal;
import wpsPeasant.L4SkillsResourcesGoals.LookForALandGoal;
import wpsPeasant.L1SurvivalGoals.DoHealthCareGoal;
import wpsPeasant.L6LeisureGoals.EngageInLeisureActivitiesGoal;
import wpsPeasant.L5SocialGoals.CommunicateGoal;
import wpsPeasant.L3DevelopmentGoals.AttendToLivestockGoal;
import wpsPeasant.L5SocialGoals.LookForCollaborationGoal;
import wpsPeasant.L5SocialGoals.ProvideCollaborationGoal;
import wpsPeasant.L3DevelopmentGoals.CheckCropsGoal;
import wpsPeasant.L3DevelopmentGoals.PrepareLandGoal;
import wpsPeasant.L4SkillsResourcesGoals.ObtainSuppliesGoal;
import wpsPeasant.L3DevelopmentGoals.PlantCropsGoal;
import wpsPeasant.L4SkillsResourcesGoals.ObtainToolsGoal;
import wpsPeasant.L3DevelopmentGoals.HarvestCropsGoal;
import wpsPeasant.L3DevelopmentGoals.ProcessProductsGoal;
import wpsPeasant.L4SkillsResourcesGoals.GetTrainingGoal;
import wpsPeasant.L3DevelopmentGoals.ControlWeedsGoal;
import wpsPeasant.L3DevelopmentGoals.ManagePestsGoal;
import wpsPeasant.L3DevelopmentGoals.IrrigateCropsGoal;
import wpsPeasant.L2ObligationGoals.PayDebtsGoal;
import wpsPeasant.L3DevelopmentGoals.MaintainHouseGoal;
import wpsPeasant.L2ObligationGoals.LookForMoneyGoal;
import wpsPeasant.L6LeisureGoals.SpendFamilyTimeGoal;
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
import jdk.jshell.EvalException;
import rational.guards.InformationFlowGuard;
import wpsPeasant.L1SurvivalGoals.DoVitalsGoal;
import wpsPeasant.L1SurvivalGoals.SelfEvaluationGoal;
import wpsPeasant.L3DevelopmentGoals.SellCropsGoal;
import wpsPeasant.L3DevelopmentGoals.SellProductsGoal;
import wpsPeasant.L4SkillsResourcesGoals.ObtainLivestockGoal;
import wpsPeasant.L4SkillsResourcesGoals.ObtainSeedsGoal;
import wpsPeasant.L4SkillsResourcesGoals.ObtainWaterGoal;
import wpsPeasant.L4SkillsResourcesGoals.ObtainingPesticidesGoal;
import wpsPeasant.Utils.PeasantProfile;
import wpsWorld.Guards.PeasantCommGuard;

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
        structBESA.addBehavior("PeasantCommGuard");
        structBESA.bindGuard("PeasantCommGuard", PeasantCommGuard.class);
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
        
        //Level 1 Goals: Survival        
        goals.add(DoVitalsGoal.buildGoal());        
        goals.add(DoHealthCareGoal.buildGoal());
        goals.add(SeekPurposeGoal.buildGoal());
        goals.add(SelfEvaluationGoal.buildGoal());        
        
        //Level 2 Goals: Obligations
        goals.add(LookForMoneyGoal.buildGoal());
        goals.add(PayDebtsGoal.buildGoal());
        
        //Level 3 Goals: Development        
        goals.add(AttendToLivestockGoal.buildGoal());
        goals.add(CheckCropsGoal.buildGoal());
        goals.add(ControlWeedsGoal.buildGoal());
        goals.add(HarvestCropsGoal.buildGoal());
        goals.add(IrrigateCropsGoal.buildGoal());
        goals.add(MaintainHouseGoal.buildGoal());
        goals.add(ManagePestsGoal.buildGoal());
        goals.add(PlantCropsGoal.buildGoal());
        goals.add(PrepareLandGoal.buildGoal());
        goals.add(ProcessProductsGoal.buildGoal());
        goals.add(SellCropsGoal.buildGoal());
        goals.add(SellProductsGoal.buildGoal());
        
        //Level 4 Goals: Skills And Resources
        goals.add(GetTrainingGoal.buildGoal());
        goals.add(LookForALandGoal.buildGoal());
        goals.add(ObtainLivestockGoal.buildGoal());
        goals.add(ObtainSeedsGoal.buildGoal());
        goals.add(ObtainSuppliesGoal.buildGoal());
        goals.add(ObtainToolsGoal.buildGoal());
        goals.add(ObtainWaterGoal.buildGoal());
        goals.add(ObtainingPesticidesGoal.buildGoal());
        
        //Level 5 Goals: Social
        goals.add(CommunicateGoal.buildGoal());        
        goals.add(LookForCollaborationGoal.buildGoal());
        goals.add(ProvideCollaborationGoal.buildGoal());

        //Level 6 Goals: Leisure
        goals.add(EngageInLeisureActivitiesGoal.buildGoal());        
        goals.add(SpendFamilyTimeGoal.buildGoal());
        
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
