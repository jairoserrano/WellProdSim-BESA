/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author jairo
 */
public class PeasantFarmingTask extends Task  {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    public PeasantFarmingTask() {
        System.out.println("--- Task Siembra Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Seleccionar Cancion ---");

        PeasantAgentBelieves believes = (PeasantAgentBelieves) parameters;
        believes.getbEstadoActividad().setActividadActual(ResPwAActivity.MUSICOTERAPIA);
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
        believes.getbEstadoActividad().setTiempoInicioActividad(ts.getTime());
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("SAY", "Estoy seleccionando una cancion, quiero que cantes conmigo.");
        ServiceDataRequest data = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, hm);
        ResPwaUtils.requestService(data, believes);

        List<Preferenciaxcancion> canciones = believes.getbPerfilPwA().getPerfil().getPerfilPreferencia().getPreferenciaxcancionList();
        ModeloSeleccion<Preferenciaxcancion> modeloCancion = new ModeloSeleccion<Preferenciaxcancion>(canciones);
        Preferenciaxcancion cancionSelected = null;
        CromosomaCancion cromosoma = null;
        cromosoma = (CromosomaCancion) modeloCancion.selectCromosoma();

        if (cromosoma != null) {
            cancionSelected = cromosoma.getCancion();
            believes.getbEstadoActividad().setCancionActual(cancionSelected);
        }

        if (!believes.getbEstadoRobot().isStoryMode()) {
            believes.getbEstadoRobot().setStoryMode(true);
        }
        hm = new HashMap<>();
        hm.put("SAY", "La canción seleccionada es " + cancionSelected.getCancion().getNombre());
        data = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, hm);
        ResPwaUtils.requestService(data, believes);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Seleccionar Cancion ---");
        PeasantAgentBelieves blvs = (PeasantAgentBelieves) believes;

        blvs.getbEstadoRobot().setStoryMode(true);
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Seleccionar Cancion ---");
        PeasantAgentBelieves blvs = (PeasantAgentBelieves) believes;

        blvs.getbEstadoActividad().setCancionActual(null);
        blvs.getbEstadoRobot().setStoryMode(true);
    }

    @Override
    public boolean checkFinish(Believes believes) {

        PeasantAgentBelieves blvs = (PeasantAgentBelieves) believes;
        if (blvs.getbEstadoActividad().getCancionActual() != null) {
            return true;
        }
        return false;
    }
}
