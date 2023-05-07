package rational.services;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.Social.ServiceProvider.agent.StateServiceProvider;

/**
 * A guard class to handle the activation of asynchronous services in a BESA
 * agent.
 */
public class ActivateAsynchronousServiceGuard extends GuardBESA {

    /**
     * Executes the guard functionality when an event is triggered.
     *
     * @param event The event containing the service activation data.
     */
    @Override
    public void funcExecGuard(EventBESA event) {
        ActivateServiceData data = (ActivateServiceData) event.getData();
        StateServiceProvider state = (StateServiceProvider) this.getAgent().getState();
        AsynchronousService servicio = (AsynchronousService) state.getDescriptor().getServiceAccessTable().get(
                data.getServiceName()
        );
        servicio.executeAsyncService(
                data,
                state.getAdapter(),
                state.getAgentsGuardsTableAsync()
        );
    }
}
