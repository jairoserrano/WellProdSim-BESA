/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant;

import BESA.BDI.AgentStructuralModel.Agent.AgentBDI;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Log.ReportBESA;
import Peasant.Utils.PeasantPurpose;
import java.util.List;

/**
 *
 * @author jairo
 */
public class PeasantAgent extends AgentBDI {

    private static final double TH = 0.91;

    public PeasantAgent(String alias, List<GoalBDI> PeasantGoals, PeasantPurpose purpose) throws ExceptionBESA {
        super(alias, new PeasantAgentBelieves(purpose), PeasantGoals, TH, new StructBESA());
        ReportBESA.info("PeasantAgent Iniciado");
    }

    @Override
    public void setupAgentBDI() {
    }

    @Override
    public void shutdownAgentBDI() {

    }
}
