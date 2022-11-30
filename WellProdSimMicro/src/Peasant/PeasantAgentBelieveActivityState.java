/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant;

import Peasant.Utils.Crop;
import Peasant.Utils.PeasantActivity;
import Peasant.Utils.PeasantCropPreference;
import Peasant.Utils.Purpose;
import Peasant.Utils.SensorData;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author jairo
 */
public class PeasantAgentBelieveActivityState implements Believes {

    private long tiempoInicioActividad = 0;
    private PeasantAgentBelieves blvs = null;
    private Purpose purpose;
    private PeasantCropPreference currentCrop;
    private boolean estadoSembrando;
    private boolean actividadEnCurso = false;
    private PeasantActivity currentActivity = PeasantActivity.FARMING;

    public PeasantAgentBelieveActivityState(String purpose, PeasantAgentBelieves blvs) {
        this.purpose = new Purpose(purpose);
        this.blvs = blvs;
        this.estadoSembrando = false;
    }

    @Override
    public boolean update(InfoData si) {
        System.out.println("PeasantAgentBelieveActivityState update Received: " + si);
        SensorData infoRecibida = (SensorData) si;
        if (infoRecibida.getDataP().containsKey("actividadEnCurso")) {
            actividadEnCurso = Boolean.valueOf((String) infoRecibida.getDataP().get("actividadEnCurso"));
            if (actividadEnCurso) {
                tiempoInicioActividad = System.currentTimeMillis();
            } else {
                tiempoInicioActividad = 0;
            }
        }

        if (infoRecibida.getDataP().containsKey("finish")) {
            estadoSembrando = (boolean) infoRecibida.getDataP().get("finish");
        }
        return true;
    }

    public PeasantActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentCrop(PeasantCropPreference currentCrop) {
        this.currentCrop = currentCrop;
    }

    public PeasantCropPreference getCurrentCrop() {
        return currentCrop;
    }
    
    public double getGustoActividad(PeasantActivity actividad) {
        // TODO: Calcular el gusto real
        double gusto = 1;
        /*for (Actxpreferencia a : blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getActxpreferenciaList()) {
            if (a.getActividadpwa().getNombre().equalsIgnoreCase(actividad.toString())) {
                gusto = a.getGusto();
            }
        }*/
        return gusto;
    }

    @Override
    public Believes clone() throws CloneNotSupportedException {
        super.clone();
        return this;
    }

}
