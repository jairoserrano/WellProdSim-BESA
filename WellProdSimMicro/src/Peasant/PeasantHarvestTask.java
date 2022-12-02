package Peasant;

import Peasant.Utils.PeasantActivity;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author jairo
 */
public class PeasantHarvestTask extends Task {

        private HashMap<String, Object> infoServicio = new HashMap<>();

    public PeasantHarvestTask() {
        System.out.println("--- Task Recolecta Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Recolecta ---");

        PeasantAgentBelieves believes = (PeasantAgentBelieves) parameters;
        believes.getPeasantAgentBelieveActivityState().setCurrentActivity(PeasantActivity.HARVEST);
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
        believes.getPeasantAgentBelieveActivityState().setStartedActivityTime(ts.getTime());
        
        //TODO: CONEXION CON EL MUNDO
        
        if (!believes.getPeasantAgentBelieveState().isRestMode()) {
            believes.getPeasantAgentBelieveState().setRestMode(true);
        }
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Cancion ---");
        PeasantAgentBelieves blvs = (PeasantAgentBelieves) believes;

        blvs.getPeasantAgentBelieveState().setRestMode(true);
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Cancion ---");
        PeasantAgentBelieves blvs = (PeasantAgentBelieves) believes;

        blvs.getPeasantAgentBelieveActivityState().setCurrentHarvest(null);
        blvs.getPeasantAgentBelieveState().setRestMode(true);
    }

    @Override
    public boolean checkFinish(Believes believes) {

        PeasantAgentBelieves blvs = (PeasantAgentBelieves) believes;
        if (blvs.getPeasantAgentBelieveActivityState().getCurrentHarvest() != null) {
            return true;
        }
        return false;
    }
    
}
