package BESA.World.agent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.World.helper.DateSingleton;
import BESA.World.agents.messages.world.WorldMessage;
import BESA.World.agents.messages.world.WorldMessageType;

/**
 * BESA world's periodic guard, holds the behavior when calling the world agent
 */
public class WorldPeriodicGuard extends PeriodicGuardBESA {

    @Override
    public void funcPeriodicExecGuard(EventBESA eventBESA) {
        try {
            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAid(this.agent.getAid());
            DateSingleton currentDate = DateSingleton.getInstance();
            WorldMessage worldMessage = new WorldMessage(WorldMessageType.CROP_OBSERVE, null, currentDate.getCurrentDate(), null);
            EventBESA eventBESASend = new EventBESA(WorldGuard.class.getName(), worldMessage);
            ah.sendEvent(eventBESASend);
        } catch (ExceptionBESA exceptionBESA) {
            exceptionBESA.printStackTrace();
        }
    }
}
