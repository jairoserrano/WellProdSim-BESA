/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant;

import BESA.Log.ReportBESA;
import Peasant.Utils.Crop;
import Peasant.Utils.PeasantActivityType;
import Peasant.Utils.PeasantFarmingPreference;
import Peasant.Utils.PeasantHarvestPreference;
import Peasant.Utils.PeasantPurpose;
import Peasant.Utils.SensorData;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author jairo
 */
public class PeasantAgentBelieveActivityState implements Believes {

    private long startedActivityTime = 0;

    private PeasantAgentBelieves blvs = null;
    private PeasantPurpose purpose;

    private PeasantFarmingPreference currentFarming;
    private boolean farmingStatus;

    private PeasantHarvestPreference currentHarvest;
    private boolean harvestStatus;

    private boolean actividadEnCurso = false;
    private PeasantActivityType currentActivity = PeasantActivityType.FARMING;

    public PeasantAgentBelieveActivityState(PeasantPurpose purpose, PeasantAgentBelieves blvs) {
        this.purpose = purpose;
        this.blvs = blvs;
        this.farmingStatus = false;
    }

    public PeasantFarmingPreference getCurrentFarming() {
        return currentFarming;
    }

    public void setCurrentFarming(PeasantFarmingPreference currentFarming) {
        this.currentFarming = currentFarming;
    }

    public boolean isFarmingStatus() {
        return farmingStatus;
    }

    public void setFarmingStatus(boolean farmingStatus) {
        this.farmingStatus = farmingStatus;
    }

    public long getStartedActivityTime() {
        return startedActivityTime;
    }

    public void setStartedActivityTime(long startedActivityTime) {
        this.startedActivityTime = startedActivityTime;
    }

    public PeasantHarvestPreference getCurrentHarvest() {
        return currentHarvest;
    }

    public void setCurrentHarvest(PeasantHarvestPreference currentHarvest) {
        this.currentHarvest = currentHarvest;
    }

    public boolean isHarvestStatus() {
        return harvestStatus;
    }

    public void setHarvestStatus(boolean harvestStatus) {
        this.harvestStatus = harvestStatus;
    }

    @Override
    public boolean update(InfoData si) {
        ReportBESA.info("PeasantAgentBelieveActivityState update Received: " + si);
        SensorData infoRecibida = (SensorData) si;
        if (infoRecibida.getDataP().containsKey("actividadEnCurso")) {
            actividadEnCurso = Boolean.valueOf((String) infoRecibida.getDataP().get("actividadEnCurso"));
            if (actividadEnCurso) {
                startedActivityTime = System.currentTimeMillis();
            } else {
                startedActivityTime = 0;
            }
        }

        if (infoRecibida.getDataP().containsKey("finish")) {
            farmingStatus = (boolean) infoRecibida.getDataP().get("finish");
        }
        return true;
    }

    public PeasantActivityType getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(PeasantActivityType currentActivity) {
        this.currentActivity = currentActivity;
    }

    public double getGustoActividad(PeasantActivityType actividad) {
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

    public long calcTiempoActividad() {
        long time = -1;
        if (startedActivityTime != 0) {
            Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
            time = (ts.getTime() - startedActivityTime) / 1000;
        }

        return time;
    }

}
