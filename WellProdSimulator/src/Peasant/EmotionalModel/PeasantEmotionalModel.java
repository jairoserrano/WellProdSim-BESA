/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant.EmotionalModel;

import Peasant.EmotionalModel.EmotionalConfig;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jairo
 */
public abstract class PeasantEmotionalModel extends EmotionalModel {

    public PeasantEmotionalModel() {
        super();
    }

    @Override
    public void loadSemanticDictionary() {

        SemanticDictionary sd = SemanticDictionary.getInstance();
        for (EmotionalConfig.People who : EmotionalConfig.People.values()) {
            sd.addSemanticItem(Personality.EmotionElementType.Person, new SemanticValue(who.toString(), who.getValue()));
        }

        for (EmotionalConfig.Events evt : EmotionalConfig.Events.values()) {
            sd.addSemanticItem(Personality.EmotionElementType.Event, new SemanticValue(evt.toString(), evt.getValue()));
        }

        for (EmotionalConfig.Objects obj : EmotionalConfig.Objects.values()) {
            sd.addSemanticItem(Personality.EmotionElementType.Object, new SemanticValue(obj.toString(), obj.getValue()));
        }

    }

    @Override
    public void loadCharacterDescriptor() {

        for (EmotionalSubjectType who : EmotionalSubjectType.values()) {
            setPersonRelationship(who.toString(), who.getConfig());
        }

        for (EmotionalEventType evt : EmotionalEventType.values()) {
            setEventDesirability(evt.toString(), evt.getConfig());
        }

        for (EmotionalObjectType obj : EmotionalObjectType.values()) {
            if (!obj.equals(EmotionalObjectType.NULL)) {
                setObjectRelationship(obj.toString(), obj.getConfig());
            }
        }

    }

    @Override
    public void loadEmotionalAxes() {
        List<EmotionAxis> emoax = new ArrayList<>();
        EmotionAxis emoAxis;
        /**
         * TODO: CARGAR EMOCIONES List<EmotionAxisConfig> aux =
         * RESPwABDInterface.getEmotionalAxisConfig(); List<EventInfluence>
         * evtinf; for (EmotionAxisConfig emotionAxisConfig : aux) { emoAxis =
         * new EmotionAxis(emotionAxisConfig.getPositiveName(),
         * emotionAxisConfig.getNegativeName(),
         * emotionAxisConfig.getBaseValue(), emotionAxisConfig.getBaseValue(),
         * emotionAxisConfig.getForgetFactor()); evtinf =
         * emotionAxisConfig.getEventInfluence(); for (EventInfluence
         * eventInfluence : evtinf) {
         * emoAxis.setEventInfluence(eventInfluence.getEventName(), (float)
         * eventInfluence.getEventInfluence()); } this.addEmotionAxis(emoAxis);
         * }
         */
    }
}
