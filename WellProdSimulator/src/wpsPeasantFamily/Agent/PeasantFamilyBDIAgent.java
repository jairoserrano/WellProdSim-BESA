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
package wpsPeasantFamily.Agent;

import wpsPeasantFamily.Utils.PeasantFamilyProfile;
import wpsPeasantFamily.Goals.L1Survival.SeekPurposeGoal;
import wpsPeasantFamily.Goals.L4SkillsResources.LookForALandGoal;
import wpsPeasantFamily.Goals.L1Survival.DoHealthCareGoal;
import wpsPeasantFamily.Goals.L6Leisure.EngageInLeisureActivitiesGoal;
import wpsPeasantFamily.Goals.L5Social.CommunicateGoal;
import wpsPeasantFamily.Goals.L3Development.AttendToLivestockGoal;
import wpsPeasantFamily.Goals.L5Social.LookForCollaborationGoal;
import wpsPeasantFamily.Goals.L5Social.ProvideCollaborationGoal;
import wpsPeasantFamily.Goals.L3Development.CheckCropsGoal;
import wpsPeasantFamily.Goals.L3Development.PrepareLandGoal;
import wpsPeasantFamily.Goals.L4SkillsResources.ObtainSuppliesGoal;
import wpsPeasantFamily.Goals.L3Development.PlantCropsGoal;
import wpsPeasantFamily.Goals.L4SkillsResources.ObtainToolsGoal;
import wpsPeasantFamily.Goals.L3Development.HarvestCropsGoal;
import wpsPeasantFamily.Goals.L3Development.ProcessProductsGoal;
import wpsPeasantFamily.Goals.L4SkillsResources.GetTrainingGoal;
import wpsPeasantFamily.Goals.L3Development.ManagePestsGoal;
import wpsPeasantFamily.Goals.L3Development.IrrigateCropsGoal;
import wpsPeasantFamily.Goals.L2Obligation.PayDebtsGoal;
import wpsPeasantFamily.Goals.L3Development.MaintainHouseGoal;
import wpsPeasantFamily.Goals.L2Obligation.LookForLoanGoal;
import wpsPeasantFamily.Goals.L3Development.SpendFamilyTimeGoal;
import BESA.BDI.AgentStructuralModel.Agent.AgentBDI;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import java.util.ArrayList;
import java.util.List;
import rational.guards.InformationFlowGuard;
import wpsControl.Agent.wpsCurrentDate;
import wpsPeasantFamily.Goals.L1Survival.DoVitalsGoal;
import wpsPeasantFamily.Goals.L1Survival.SelfEvaluationGoal;
import wpsPeasantFamily.Goals.L3Development.SellCropsGoal;
import wpsPeasantFamily.Goals.L3Development.SellProductsGoal;
import wpsPeasantFamily.Goals.L4SkillsResources.ObtainLivestockGoal;
import wpsPeasantFamily.Goals.L4SkillsResources.ObtainSeedsGoal;
import wpsPeasantFamily.Goals.L4SkillsResources.ObtainWaterGoal;
import wpsPeasantFamily.Goals.L4SkillsResources.ObtainPesticidesGoal;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
@SuppressWarnings("unchecked")
public class PeasantFamilyBDIAgent extends AgentBDI {

    private static final double BDITHRESHOLD = 0.5;

    private static StructBESA createStruct(StructBESA structBESA) throws ExceptionBESA {
        structBESA.addBehavior("PeasantFamilyHeartBeatGuard");
        structBESA.bindGuard("PeasantFamilyHeartBeatGuard", PeasantFamilyHeartBeatGuard.class);
        structBESA.addBehavior("FromWorldGuard");
        structBESA.bindGuard("FromWorldGuard", FromWorldGuard.class);
        structBESA.addBehavior("FromBankGuard");
        structBESA.bindGuard("FromBankGuard", FromBankGuard.class);
        structBESA.addBehavior("FromMarketGuard");
        structBESA.bindGuard("FromMarketGuard", FromMarketGuard.class);
        return structBESA;
    }

    private static PeasantFamilyBDIAgentBelieves createBelieves(PeasantFamilyProfile profile) {
        return new PeasantFamilyBDIAgentBelieves(profile);
    }

    /**
     *
     * @param alias
     * @param peasantProfile
     * @throws ExceptionBESA
     */
    public PeasantFamilyBDIAgent(String alias, PeasantFamilyProfile peasantProfile) throws ExceptionBESA {
        super(alias, createBelieves(peasantProfile), createGoals(), BDITHRESHOLD, createStruct(new StructBESA()));
        //wpsReport.info("PeasantAgent Iniciado");
    }

    private static List<GoalBDI> createGoals() {
        //wpsReport.info("");

        List<GoalBDI> goals = new ArrayList();

        //Level 1 Goals: Survival        
        goals.add(DoVitalsGoal.buildGoal());
        goals.add(DoHealthCareGoal.buildGoal());
        goals.add(SeekPurposeGoal.buildGoal());
        goals.add(SelfEvaluationGoal.buildGoal());

        //Level 2 Goals: Obligations
        goals.add(LookForLoanGoal.buildGoal());
        goals.add(PayDebtsGoal.buildGoal());

        //Level 3 Goals: Development        
        goals.add(AttendToLivestockGoal.buildGoal());
        goals.add(CheckCropsGoal.buildGoal());
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
            //wpsReport.info("💞 Peasant Family Heart Beat 💞");
        } catch (ExceptionBESA e) {
            wpsReport.error(e);
        }
    }

}
