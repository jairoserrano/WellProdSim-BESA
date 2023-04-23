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

import wpsPeasant.Goals.L1Survival.SeekPurposeGoal;
import wpsPeasant.Goals.L4SkillsResources.LookForALandGoal;
import wpsPeasant.Goals.L1Survival.DoHealthCareGoal;
import wpsPeasant.Goals.L6Leisure.EngageInLeisureActivitiesGoal;
import wpsPeasant.Goals.L5Social.CommunicateGoal;
import wpsPeasant.Goals.L3Development.AttendToLivestockGoal;
import wpsPeasant.Goals.L5Social.LookForCollaborationGoal;
import wpsPeasant.Goals.L5Social.ProvideCollaborationGoal;
import wpsPeasant.Goals.L3Development.CheckCropsGoal;
import wpsPeasant.Goals.L3Development.PrepareLandGoal;
import wpsPeasant.Goals.L4SkillsResources.ObtainSuppliesGoal;
import wpsPeasant.Goals.L3Development.PlantCropsGoal;
import wpsPeasant.Goals.L4SkillsResources.ObtainToolsGoal;
import wpsPeasant.Goals.L3Development.HarvestCropsGoal;
import wpsPeasant.Goals.L3Development.ProcessProductsGoal;
import wpsPeasant.Goals.L4SkillsResources.GetTrainingGoal;
import wpsPeasant.Goals.L3Development.ControlWeedsGoal;
import wpsPeasant.Goals.L3Development.ManagePestsGoal;
import wpsPeasant.Goals.L3Development.IrrigateCropsGoal;
import wpsPeasant.Goals.L2Obligation.PayDebtsGoal;
import wpsPeasant.Goals.L3Development.MaintainHouseGoal;
import wpsPeasant.Goals.L2Obligation.LookForMoneyGoal;
import wpsPeasant.Goals.L3Development.SpendFamilyTimeGoal;
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
import wpsPeasant.Goals.L1Survival.DoVitalsGoal;
import wpsPeasant.Goals.L1Survival.SelfEvaluationGoal;
import wpsPeasant.Goals.L3Development.SellCropsGoal;
import wpsPeasant.Goals.L3Development.SellProductsGoal;
import wpsPeasant.Goals.L4SkillsResources.ObtainLivestockGoal;
import wpsPeasant.Goals.L4SkillsResources.ObtainSeedsGoal;
import wpsPeasant.Goals.L4SkillsResources.ObtainWaterGoal;
import wpsPeasant.Goals.L4SkillsResources.ObtainPesticidesGoal;
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
        goals.add(ObtainPesticidesGoal.buildGoal());
        
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
