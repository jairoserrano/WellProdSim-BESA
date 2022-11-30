package wpsMain.agents.peasant;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import wpsMain.util.ReportBESA;

public class StartGoalCompletionGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA eventBESA) {
        ReportBESA.info("Start Goal!");
        ((PeasantBDIAgent) this.getAgent()).sparkBDI();
    }
}
