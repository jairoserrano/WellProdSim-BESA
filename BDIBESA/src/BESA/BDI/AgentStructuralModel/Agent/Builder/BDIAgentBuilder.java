/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BESA.BDI.AgentStructuralModel.Agent.Builder;

import BESA.BDI.AgentStructuralModel.Agent.AgentBDI;
import BESA.BDI.AgentStructuralModel.BDIMachineParams;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import rational.mapping.Believes;

/**
 * <p>Class that represents the BDI especific Agent</p>
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class BDIAgentBuilder {

    protected AgentBDI agentBDI;
    private BDIMachineParams paramsMachine;
    private Believes believes;
    private String alias;
    private double password;

    public BDIAgentBuilder(String alias, double password) {
        this.alias = alias;
        this.password = password;
    }

    public synchronized String getAlias() {
        return alias;
    }

    public synchronized void setAlias(String alias) {
        this.alias = alias;
    }

    public synchronized double getPassword() {
        return password;
    }

    public synchronized void setPassword(double password) {
        this.password = password;
    }

    public synchronized void setAgentBDI(AgentBDI agentBDI) {
        this.agentBDI = agentBDI;
    }

    public BDIMachineParams getParamsMachine() {
        return paramsMachine;
    }

    public synchronized void setParamsMachine(BDIMachineParams paramsMachine) {
        this.paramsMachine = paramsMachine;
    }

    public synchronized AgentBDI getAgentBDI() {
        return agentBDI;
    }

    public synchronized Believes getBelieves() {
        return believes;
    }

    public synchronized void setBelieves(Believes believes) {
        this.believes = believes;
    }

    public abstract void buildDBIMachine();

    public abstract void buildAgentBelieves();

    public abstract void buildAgent() throws KernelAgentExceptionBESA, ExceptionBESA;
}
