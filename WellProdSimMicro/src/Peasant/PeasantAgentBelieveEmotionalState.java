/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant;

import Peasant.Utils.Emotion;
import Peasant.Utils.EmotionPwA;
import static Peasant.Utils.EmotionPwA.ANGER;
import static Peasant.Utils.EmotionPwA.SADNESS;
import Peasant.Utils.EmotionalData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author jairo
 */
public class PeasantAgentBelieveEmotionalState implements Believes {

    private Map<EmotionPwA, List<Emotion>> emoMap;
    private long tiempoEmocionPredominante;
    private float valencia;

    public PeasantAgentBelieveEmotionalState() {
        emoMap = new HashMap<>();
        for (EmotionPwA epwa : EmotionPwA.values()) {
            emoMap.put(epwa, new ArrayList<>());
        }
    }

    @Override
    public boolean update(InfoData si) {
        System.out.println("BEstadoEmocionalPwA update Received: " + si);
        EmotionalData infoRecibida = (EmotionalData) si;
        /*if (infoRecibida.getInfo().containsKey("trabajando")) {
            atencion = (double) infoRecibida.getInfo().get("trabajando");
            if ((double) infoRecibida.getInfo().get("trabajando") < 0.5) {
                tiempoSinAtencion = tiempoSinAtencion != null ? tiempoSinAtencion : System.currentTimeMillis();
            } else {
                tiempoSinAtencion = null;
            }
        }*/

        if (infoRecibida.getInfo().containsKey("emotions")) {
            Map<EmotionPwA, Float> emo = (Map<EmotionPwA, Float>) infoRecibida.getInfo().get("emotions");
            Emotion e;
            float val;
            if (emo != null) {
                for (EmotionPwA epwa : emo.keySet()) {
                    val = emo.get(epwa);
                    e = new Emotion(val);
                    emoMap.get(epwa).add(e);
                }
            }
        }
        return true;
    }

    public double getEmocionPredominante() {
        double aux = getFeedbackEmotion();
        if (aux > 0 && valencia != 1) {
            valencia = 1;
            tiempoEmocionPredominante = System.currentTimeMillis();
        } else if (aux < 0 && valencia != -1) {
            valencia = -1;
            tiempoEmocionPredominante = System.currentTimeMillis();
        }
        return aux;
    }

    public double getFeedbackEmotion() {
        double emotionFeedback = 0.0;
        double auxEmotionAverage = 0.0;
        for (EmotionPwA entry : emoMap.keySet()) {
            auxEmotionAverage = getEmotionAverage(emoMap.get(entry));
            if (entry.equals(ANGER) || entry.equals(SADNESS)) {
                auxEmotionAverage *= -1;
            }
            if (Math.abs(auxEmotionAverage) > Math.abs(emotionFeedback)) {
                emotionFeedback = auxEmotionAverage;
            }
        }
        return emotionFeedback;
    }

    public Map<EmotionPwA, List<Emotion>> getEmoMap() {
        return emoMap;
    }

    public void setEmoMap(Map<EmotionPwA, List<Emotion>> emoMap) {
        this.emoMap = emoMap;
    }

    private float getEmotionAverage(List<Emotion> historyEmotionsInActivity) {
        float emotionAverage = 0.0f;
        for (Emotion emotion : historyEmotionsInActivity) {
            emotionAverage += emotion.getValence();
        }
        return emotionAverage / historyEmotionsInActivity.size();
    }

    @Override
    public Believes clone() throws CloneNotSupportedException {
        super.clone();
        return this;
    }

}
