/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wpsPeasant.EmotionalModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jairo
 */
public class EmotionalState {

    private final List<EmotionAxis> emotions;

    /**
     *
     */
    public EmotionalState() {
        emotions = new ArrayList<>();
    }

    /**
     *
     * @param ea
     */
    protected void addEmotionAxis(EmotionAxis ea) {
        EmotionAxis x = getEmotion(ea.getPositiveName(), ea.getNegativeName());
        if (x == null) {
            emotions.add(ea);
        }else{
            x.setCurrentValue(ea.getCurrentValue());
            x.setBaseValue(ea.getBaseValue());
            x.setForgetFactor(ea.getForgetFactor());
            x.setEventInfluences(ea.getEventInfluences());
        }
    }

    /**
     *
     * @param event
     * @param intensity
     */
    protected void updateEmotions(String event, float intensity) {
        for (EmotionAxis e : emotions) {
            e.updateIntensity(event, intensity);
        }
    }

    /**
     *
     * @param positiveName
     * @param negativeName
     * @return
     */
    protected EmotionAxis getEmotion(String positiveName, String negativeName) {
        EmotionAxis ea = null;
        Iterator itr = emotions.iterator();
        if (itr != null) {
            while (itr.hasNext()) {
                EmotionAxis e = (EmotionAxis) itr.next();
                if (e.getPositiveName().toLowerCase().equals(positiveName.toLowerCase())
                        && e.getNegativeName().toLowerCase().equals(negativeName.toLowerCase())) {
                    ea = e;
                    break;
                }
            }
        }
        return ea;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return emotions.toString();
    }

    /**
     *
     * @return
     * @throws CloneNotSupportedException
     */
    protected EmotionAxis getMostActivatedEmotion() throws CloneNotSupportedException {
        EmotionAxis ea = null;
        Iterator itr = emotions.iterator();
        if (itr != null) {
            while (itr.hasNext()) {
                EmotionAxis e = (EmotionAxis) itr.next();
                if (ea == null || (e.getActivationValue() >= ea.getActivationValue())) {
                    ea = e;
                }
            }
        }
        if (ea != null) {
            return ea.clone();
        } else {
            return null;
        }
    }

    /**
     *
     * @return
     * @throws CloneNotSupportedException
     */
    protected List<EmotionAxis> getEmotionsListCopy() throws CloneNotSupportedException {
        List<EmotionAxis> list = new ArrayList<>();
        Iterator itr = emotions.iterator();
        if (itr != null) {
            while (itr.hasNext()) {
                list.add(((EmotionAxis) itr.next()).clone());
            }
        }
        return list;
    }

    /**
     *
     * @return
     */
    public List<EmotionAxis> getEmotions() {
        return emotions;
    }
    
    
    
}

