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
package wpsPerturbation.Agent;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;

/**
 *
 * @author jairo
 */
public class wpsPerturbationAgent extends AgentBESA {

    /**
     *
     * @param alias
     * @param state
     * @param structAgent
     * @param passwd
     * @throws KernelAgentExceptionBESA
     */
    public wpsPerturbationAgent(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernelAgentExceptionBESA {
        super(alias, state, structAgent, passwd);
    }

    public static wpsPerturbationAgent createPerturbationAgent(double passwd) throws ExceptionBESA{      
        wpsPerturbationAgent perturbationAgent = new wpsPerturbationAgent("wpsPerturbation", createState(), createStruct(new StructBESA()), passwd);
        return perturbationAgent;
        
    }
    
    private static StructBESA createStruct(StructBESA structBESA) throws ExceptionBESA {
        structBESA.addBehavior("PerturbationAgentGuard");
        structBESA.bindGuard("PerturbationAgentGuard", wpsPerturbationGuard.class);
        return structBESA;
    }
    
    private static wpsPerturbationState createState() throws ExceptionBESA {
        wpsPerturbationState perturbationAgentState = new wpsPerturbationState();
        return perturbationAgentState;
    }
    
    /**
     *
     */
    @Override
    public void setupAgent() {
        
    }

    /**
     *
     */
    @Override
    public void shutdownAgent() {
        
    }
    
}
