package rational.guards;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import rational.RationalState;
import rational.data.InfoData;

/**
 *
 * The InformationFlowGuard class extends GuardBESA and is responsible for
 *
 * handling information flow events in the rational agent framework.
 */
public class InformationFlowGuard extends GuardBESA {

    /**
     *
     * Executes the guard functionality when an event is received.
     *
     * @param ebesa The event that triggered the guard.
     */
    @Override
    public void funcExecGuard(EventBESA ebesa) {
        RationalState state = (RationalState) this.getAgent().getState();
        InfoData info = (InfoData) ebesa.getData();
        state.getBelieves().update(info);
        try {
            for (String subscriptionsToUpdate : state.getSubscriptionsToUpdate()) {
                AgHandlerBESA handler = AdmBESA.getInstance().getHandlerByAid(this.getAgent().getAid());
                handler.sendEvent(new EventBESA(subscriptionsToUpdate));
            }
        } catch (ExceptionBESA ex) {

        }
    }

}
