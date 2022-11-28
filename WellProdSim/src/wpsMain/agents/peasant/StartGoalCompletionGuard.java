package wpsMain.agents.peasant;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;

public class StartGoalCompletionGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA eventBESA) {
        System.out.println("Start Goal!");
        ((PeasantBDIAgent) this.getAgent()).sparkBDI();
    }
}
