/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wpsPeasant.EmotionalModel;

import wpsPeasant.Utils.SensorData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rational.data.InfoData;

/**
 *
 * @author jairo
 */
public class EmotionalData extends InfoData {

    private Map<String, Object> info;
    private List<EmotionalEvent> emoEv;
    public static EmotionalData fromSensorData(SensorData infoRecibida) {
        EmotionalData em = new EmotionalData();
        em.info = infoRecibida.getDataP();
        em.emoEv = null;
        return em;
    }

    public static EmotionalData getPeriodicData() {
        EmotionalData em = new EmotionalData();
        em.info = new HashMap<>();
        return em;

    }

    public EmotionalData() {
        super("emodata");
        info = new HashMap<>();
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public List<EmotionalEvent> getEmoEv() {
        return emoEv;
    }

    public void setEmoEv(List<EmotionalEvent> emoEv) {
        this.emoEv = emoEv;
    }

}
