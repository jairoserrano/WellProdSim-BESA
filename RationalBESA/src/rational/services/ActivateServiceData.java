package rational.services;

import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;

/**
 * A class to represent the data required for activating a service.
 */
public class ActivateServiceData extends SPServiceDataRequest {

    private String serviceName;

    /**
     * Constructs a new ActivateServiceData instance with the given service
     * name.
     *
     * @param serviceName The name of the service to be activated.
     */
    public ActivateServiceData(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * Retrieves the name of the service to be activated.
     *
     * @return The name of the service.
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets the name of the service to be activated.
     *
     * @param serviceName The name of the service.
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
