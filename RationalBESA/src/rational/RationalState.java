package rational;

import BESA.Kernel.Agent.StateBESA;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rational.mapping.Believes;

/**
 * RationalState is a class that extends StateBESA, representing the state of a
 * rational agent. It contains information about the agent's believes, main
 * role, synchronous and asynchronous services, and subscriptions to update.
 */
public class RationalState extends StateBESA {

    /**
     * Enum representing the type of service, either synchronic or async.
     */
    public enum TYPE {
        SYNCHRONIC,
        ASYNC;
    }

    // Agent's believes.
    Believes believes;

    // Agent's main role.
    RationalRole mainRole;

    // Map of the agent's Soft Point (SP) services.
    Map<String, String> SPservices;

    // Map of the agent's synchronous services.
    Map<String, List<Class>> syncronicServices;

    // Map of the agent's asynchronous services.
    Map<String, List<Class>> asyncronicServices;

    // List of subscriptions that need to be updated.
    List<String> subscriptionsToUpdate;

    /**
     * Default constructor for RationalState.
     */
    public RationalState() {
        super();
        this.syncronicServices = new HashMap<>();
        this.asyncronicServices = new HashMap<>();
        this.subscriptionsToUpdate = new ArrayList<>();
    }

    /**
     * Constructor for RationalState with an initial set of believes.
     *
     * @param believes Initial set of believes for the agent.
     */
    public RationalState(Believes believes) {
        super();
        this.believes = believes;
        this.syncronicServices = new HashMap<>();
        this.asyncronicServices = new HashMap<>();
        this.subscriptionsToUpdate = new ArrayList<>();
    }

    /**
     * Constructor for RationalState with an initial set of believes and a main
     * role.
     *
     * @param believes Initial set of believes for the agent.
     * @param mainRole The main role of the agent.
     */
    public RationalState(Believes believes, RationalRole mainRole) {
        super();
        this.believes = believes;
        this.mainRole = mainRole;
        this.syncronicServices = new HashMap<>();
        this.asyncronicServices = new HashMap<>();
        this.subscriptionsToUpdate = new ArrayList<>();
    }

    // Getter and setter methods for the class properties.
    public RationalRole getMainRole() {
        return mainRole;
    }

    public Believes getBelieves() {
        return believes;
    }

    public void setBelieves(Believes believes) {
        this.believes = believes;
    }

    public void assignMainRole(RationalRole mainRole) {
        if (mainRole != null) {
            this.mainRole = mainRole;
        }
    }

    public List<String> getSubscriptionsToUpdate() {
        return subscriptionsToUpdate;
    }

    public void setMainRole(RationalRole mainRole) {
        this.mainRole = mainRole;
    }

    public Map<String, List<Class>> getAsyncronicServices() {
        return asyncronicServices;
    }

    public void setAsyncronicServices(Map<String, List<Class>> asyncronicServices) {
        this.asyncronicServices = asyncronicServices;
    }

    public Map<String, List<Class>> getSyncronicServices() {
        return syncronicServices;
    }

    public void setSyncronicServices(Map<String, List<Class>> syncronicServices) {
        this.syncronicServices = syncronicServices;
    }

    public Map<String, String> getSPservices() {
        return SPservices;
    }

    public void setSPservices(Map<String, String> SPservices) {
        this.SPservices = SPservices;
    }

    /**
     * Subscribes a guard to the update list.
     *
     * @param guardName The name of the guard to be subscribed.
     */
    public void subscribeGuardToUpdate(String guardName) {
        this.subscriptionsToUpdate.add(guardName);
    }

    /**
     * Adds a service to the agent's services map and associates it with a Soft
     * Point (SP), a type (synchronous or asynchronous), and a guard.
     *
     * @param service The name of the service to be added.
     * @param sp The Soft Point associated with the service.
     * @param type The type of the service (synchronous or asynchronous).
     * @param guard The guard associated with the service.
     */
    public void addService(String service, String sp, TYPE type, Class guard) {
        this.SPservices.put(service, sp);
        if (type == TYPE.SYNCHRONIC) {
            if (this.syncronicServices.containsKey(sp)) {
                this.syncronicServices.get(sp).add(guard);
            } else {
                List<Class> guards = new ArrayList<>();
                guards.add(guard);
                this.syncronicServices.put(sp, guards);
            }
        } else {
            if (this.asyncronicServices.containsKey(sp)) {
                this.asyncronicServices.get(sp).add(guard);
            } else {
                List<Class> guards = new ArrayList<>();
                guards.add(guard);
                this.asyncronicServices.put(sp, guards);
            }
        }
    }
}
