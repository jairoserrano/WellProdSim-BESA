/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wpsmicro;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.ExceptionBESA;
import BESA.Kernel.System.AdmBESA;
import Peasant.PeasantAgent;
import Peasant.PeasantAgroGoal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jairo
 */
public class SimulatorMicro {

    private static int PLANID = 0;
    public static String aliasPeasantAgent = "JUAN";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            AdmBESA.getInstance();
            System.out.println("Iniciando WellProdSim-Micro");
            PeasantAgent Peasant = new PeasantAgent(aliasPeasantAgent, createPeasantAgentGoals(), "Agricultor");
            Peasant.start();
        } catch (ExceptionBESA ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static List<GoalBDI> createPeasantAgentGoals() {
        List<GoalBDI> PeasantGoals = new ArrayList<>();
        PeasantAgroGoal peasantAgroGoal = PeasantAgroGoal.buildGoal();
        PeasantGoals.add(peasantAgroGoal);
        return PeasantGoals;
    }

    public static int getPlanID() {
        return ++PLANID;
    }
}
