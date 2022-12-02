/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant;

import Peasant.EmotionalModel.Emotion;
import Peasant.EmotionalModel.PeasantEmotions;
import static Peasant.EmotionalModel.PeasantEmotions.ANGER;
import static Peasant.EmotionalModel.PeasantEmotions.SADNESS;
import Peasant.EmotionalModel.EmotionalData;
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

    private Map<PeasantEmotions, List<Emotion>> emoMap;
    private long tiempoEmocionPredominante;
    private float valencia;

    public PeasantAgentBelieveEmotionalState() {
        emoMap = new HashMap<>();
        for (PeasantEmotions epwa : PeasantEmotions.values()) {
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
            Map<PeasantEmotions, Float> emo = (Map<PeasantEmotions, Float>) infoRecibida.getInfo().get("emotions");
            Emotion e;
            float val;
            if (emo != null) {
                for (PeasantEmotions epwa : emo.keySet()) {
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
        for (PeasantEmotions entry : emoMap.keySet()) {
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

    public Map<PeasantEmotions, List<Emotion>> getEmoMap() {
        return emoMap;
    }

    public void setEmoMap(Map<PeasantEmotions, List<Emotion>> emoMap) {
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
