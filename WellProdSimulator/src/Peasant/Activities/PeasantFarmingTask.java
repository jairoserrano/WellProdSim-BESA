/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant;

import BESA.Log.ReportBESA;
import Peasant.Utils.PeasantActivityType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author jairo
 */
public class PeasantFarmingTask extends Task {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    public PeasantFarmingTask() {
        ReportBESA.info("--- Task Siembra Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        ReportBESA.info("--- Execute Task Siembra ---");

        PeasantAgentBelieves believes = (PeasantAgentBelieves) parameters;
        believes.getPeasantAgentBelieveActivityState().setCurrentActivity(PeasantActivityType.FARMING);
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now()); 
        believes.getPeasantAgentBelieveActivityState().setStartedActivityTime(ts.getTime());
        
        //TODO: CONEXION CON EL MUNDO
        
        if (!believes.getPeasantAgentBelieveState().isRestMode()) {
            believes.getPeasantAgentBelieveState().setRestMode(true);
        }
    }

    @Override
    public void interruptTask(Believes believes) {
        ReportBESA.info("--- Interrupt Task Seleccionar Cancion ---");
        PeasantAgentBelieves blvs = (PeasantAgentBelieves) believes;

        blvs.getPeasantAgentBelieveState().setRestMode(true);
    }

    @Override
    public void cancelTask(Believes believes) {
        ReportBESA.info("--- Cancel Task Seleccionar Cancion ---");
        PeasantAgentBelieves blvs = (PeasantAgentBelieves) believes;

        blvs.getPeasantAgentBelieveActivityState().setCurrentFarming(null);
        blvs.getPeasantAgentBelieveState().setRestMode(true);
    }

    @Override
    public boolean checkFinish(Believes believes) {

        PeasantAgentBelieves blvs = (PeasantAgentBelieves) believes;
        if (blvs.getPeasantAgentBelieveActivityState().getCurrentFarming() != null) {
            return true;
        }
        return false;
    }
}
