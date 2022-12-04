/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant;

import BESA.Log.ReportBESA;
import Peasant.EmotionalModel.EmotionAxis;
import Peasant.EmotionalModel.EmotionalData;
import Peasant.EmotionalModel.EmotionalEvent;
import Peasant.EmotionalModel.EmotionalEventType;
import Peasant.EmotionalModel.PeasantEmotionalModel;
import Peasant.Utils.SensorData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author jairo
 */
public class PeasantAgentBelieveState extends PeasantEmotionalModel implements Believes {

    private boolean restMode;
    private boolean harvestMode;
    private boolean farmingMode;

    private int valencia;
    private long tiempoEmocionPredominante;

    public boolean isFarmingMode() {
        return farmingMode;
    }

    public void setFarmingMode(boolean farmingMode) {
        this.farmingMode = farmingMode;
    }

    public boolean isHarvestMode() {
        return harvestMode;
    }

    public void setHarvestMode(boolean harvestMode) {
        this.harvestMode = harvestMode;
    }

    public boolean isRestMode() {
        return restMode;
    }

    public void setRestMode(boolean restMode) {
        this.restMode = restMode;
    }

    public PeasantAgentBelieveState() {
        super();
        valencia = 0;
        restMode = false;
    }

    @Override
    public boolean update(InfoData si) {
        if (si instanceof SensorData) {
            SensorData infoRecibida = (SensorData) si;
            if (infoRecibida.getDataP().containsKey("rest")) {
                restMode = Boolean.valueOf((String) infoRecibida.getDataP().get("restModeActivate"));
            }
            if (infoRecibida.getDataP().containsKey("farming")) {
                farmingMode = Boolean.valueOf((String) infoRecibida.getDataP().get("farmingModeActivate"));
            }
            if (infoRecibida.getDataP().containsKey("harvest")) {
                harvestMode = Boolean.valueOf((String) infoRecibida.getDataP().get("harvestModeActivate"));
            }
        } else if (si instanceof EmotionalData) {
            EmotionalData emoDat = (EmotionalData) si;

            List<EmotionalEvent> emoEv = emoDat.getEmoEv();
            for (EmotionalEvent emotionalEvent : emoEv) {
                this.processEmotionalEvent(emotionalEvent);
            }
        }
        return true;
    }

    @Override
    public Believes clone() throws CloneNotSupportedException {
        super.clone();
        return this;
    }

    @Override
    public void processEmotionalEvent(EmotionalEvent ev) {
        if (isRestMode()) {
            if (ev.getEvent() != null) {
                ReportBESA.info(ev.getEvent());
                if (!(ev.getEvent().equals(EmotionalEventType.RESTING.toString()))) {
                    ReportBESA.info("ENTRA " + ev.getEvent());
                    super.processEmotionalEvent(ev);
                }
            } else {
                super.processEmotionalEvent(ev);
            }

        } else {
            super.processEmotionalEvent(ev);

        }
    }

    //TODO: Poner a funcionar el cambio de estado
    @Override
    public void emotionalStateChanged() {
        try {
            HashMap<String, Object> infoServicio = new HashMap<>();
            EmotionAxis ea = getTopEmotionAxis();

            float state = ea.getCurrentValue();
            if (state > 0 && valencia != 1) {
                valencia = 1;
                tiempoEmocionPredominante = System.currentTimeMillis();
            } else if (state < 0 && valencia != -1) {
                valencia = -1;
                tiempoEmocionPredominante = System.currentTimeMillis();
            }

            ReportBESA.info("AfueraStoryMOde" + isRestMode());

            if (!restMode) {
                ReportBESA.info("StoryMOde" + isRestMode());
            }
            if (restMode) {
                //
            } else {
                //
            }
        } catch (CloneNotSupportedException ex) {
            ReportBESA.error(ex);
        }
    }

}
