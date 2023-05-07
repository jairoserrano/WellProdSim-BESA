package rational;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.Social.ServiceProvider.agent.GuardServiceProviderSuscribe;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderBESA;
import BESA.Kernel.Social.ServiceProvider.agent.ServiceProviderDataSuscribe;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.guards.ChangeRationalRoleGuard;
import rational.guards.InformationFlowGuard;
import rational.guards.PlanCancelationGuard;
import rational.guards.PlanExecutionGuard;
import rational.mapping.Believes;

/**
 *
 * The RationalAgent class is an abstract class that extends AgentBESA.
 *
 * It provides a base implementation for rational agents in the Rational Agent
 * framework,
 *
 * including structure setup and agent lifecycle management.
 */
public abstract class RationalAgent extends AgentBESA {

    /**
     *
     * Constructs a RationalAgent with the specified alias, state, structure,
     * and password.
     *
     * @param alias The alias of the agent.
     * @param state The state of the agent.
     * @param structAgent The structure of the agent.
     * @param passwd The password of the agent.
     * @throws KernelAgentExceptionBESA
     * @throws ExceptionBESA
     */
    public RationalAgent(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernelAgentExceptionBESA, ExceptionBESA {
        super(alias, state, setupRationalStructure(structAgent), passwd);

    }

    /**
     *
     * Constructs a RationalAgent with the specified alias, state, and
     * structure.
     *
     * @param alias The alias of the agent.
     * @param state The state of the agent.
     * @param structAgent The structure of the agent.
     * @throws KernelAgentExceptionBESA
     * @throws ExceptionBESA
     */
    public RationalAgent(String alias, StateBESA state, StructBESA structAgent) throws KernelAgentExceptionBESA, ExceptionBESA {
        super(alias, state, setupRationalStructure(structAgent), 0.91f);

    }

    /**
     *
     * Constructs a RationalAgent with the specified alias, believes, and role.
     *
     * @param alias The alias of the agent.
     * @param believes The believes of the agent.
     * @param role The role of the agent.
     * @throws KernelAgentExceptionBESA
     * @throws ExceptionBESA
     */
    public RationalAgent(String alias, Believes believes, RationalRole role) throws KernelAgentExceptionBESA, ExceptionBESA {
        super(alias, new RationalState(believes, role), setupRationalStructure(), 0.91f);

    }

    /**
     *
     * Sets up the rational structure for the agent.
     *
     * @return A StructBESA object with the rational structure set up.
     * @throws ExceptionBESA
     */
    private static StructBESA setupRationalStructure() throws ExceptionBESA {
        StructBESA structure = new StructBESA();
        try {
            structure.addBehavior("InfoBehavior");
            structure.bindGuard("InfoBehavior", InformationFlowGuard.class);
            structure.addBehavior("RationalGuard");
            structure.bindGuard("RationalGuard", PlanExecutionGuard.class);
            structure.bindGuard("RationalGuard", ChangeRationalRoleGuard.class);
            structure.addBehavior("CancelationGuard");
            structure.bindGuard("CancelationGuard", PlanCancelationGuard.class);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(RationalAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

        return structure;
    }

    /**
     *
     * Sets up the rational structure for the agent using the specified
     * structure.
     *
     * @param structure The structure to be used for setting up the rational
     * structure.
     * @return A StructBESA object with the rational structure set up.
     * @throws ExceptionBESA
     */
    private static StructBESA setupRationalStructure(StructBESA structure) throws ExceptionBESA {
        try {

            structure.addBehavior("InfoBehavior");
            structure.bindGuard("InfoBehavior", InformationFlowGuard.class);
            structure.addBehavior("RationalGuard");
            structure.bindGuard("RationalGuard", PlanExecutionGuard.class);
            structure.bindGuard("RationalGuard", ChangeRationalRoleGuard.class);
            structure.addBehavior("CancelationGuard");
            structure.bindGuard("CancelationGuard", PlanCancelationGuard.class);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(RationalAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return structure;
    }

    /**
     *
     * Initializes the rational agent during setup.
     */
    @Override
    final public void setupAgent() {
        RationalState rationalState = (RationalState) getState();
        for (String serviceName : rationalState.getAsyncronicServices().keySet()) {
            for (Class guard : rationalState.getAsyncronicServices().get(serviceName)) {
                try {
                    String agId = getAdmLocal().lookupSPServiceInDirectory(rationalState.getSPservices().get(serviceName));
                    AgHandlerBESA agh = getAdmLocal().getHandlerByAid(agId);
                    ServiceProviderDataSuscribe data = new ServiceProviderDataSuscribe(guard.getName(), ServiceProviderBESA.ASYNCHRONIC_SERVICE, serviceName, null);
                    EventBESA event = new EventBESA(GuardServiceProviderSuscribe.class.getName(), data);
                    event.setSenderAgId(getAid());
                    agh.sendEvent(event);
                } catch (ExceptionBESA ex) {
                    Logger.getLogger(RationalAgent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        for (String serviceName : rationalState.getSyncronicServices().keySet()) {
            for (Class guard : rationalState.getSyncronicServices().get(serviceName)) {
                try {
                    String agId = getAdmLocal().lookupSPServiceInDirectory(serviceName);
                    AgHandlerBESA agh = getAdmLocal().getHandlerByAid(agId);
                    ServiceProviderDataSuscribe data = new ServiceProviderDataSuscribe(guard.getName(), ServiceProviderBESA.SYNCHRONIC_SERVICE, serviceName, null);
                    EventBESA event = new EventBESA(GuardServiceProviderSuscribe.class.getName(), data);
                    event.setSenderAgId(getAid());
                    agh.sendEvent(event);
                } catch (ExceptionBESA ex) {
                    Logger.getLogger(RationalAgent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        rationalState.subscribeGuardToUpdate(PlanExecutionGuard.class.getName());
        setupRationalAgent();
    }

    /**
     *
     * Shuts down the rational agent.
     */
    @Override
    final public void shutdownAgent() {
        shutdownRationalAgent();
    }

    /**
     *
     * Sets up the rational agent. This method should be implemented by
     * subclasses.
     */
    public abstract void setupRationalAgent();

    /**
     *
     * Shuts down the rational agent. This method should be implemented by
     * subclasses.
     */
    public abstract void shutdownRationalAgent();

//
}
