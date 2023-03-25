/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wpsPeasant.EmotionalModel;

import wpsPeasant.EmotionalModel.Personality.EmotionElementType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jairo
 */
public class SemanticDictionary {
     private static SemanticDictionary instance = null;

    private final Map<String, SemanticValue> objectRelationships;
    private final Map<String, SemanticValue> personRelationships;
    private final Map<String, SemanticValue> eventDesirability;

    private SemanticDictionary() {
        objectRelationships = new HashMap<>();
        personRelationships = new HashMap<>();
        eventDesirability = new HashMap<>();
    }

    /**
     *
     * @return
     */
    public synchronized static SemanticDictionary getInstance() {
        if (instance == null) {
            instance = new SemanticDictionary();
        }
        return instance;
    }

    private Map<String, SemanticValue> getList(EmotionElementType t) {
        switch (t) {
            case Object:
                return this.objectRelationships;
            case Person:
                return this.personRelationships;
            case Event:
                return this.eventDesirability;
            default:
                break;
        }
        return null;
    }

    /**
     *
     * @param t
     * @param s
     */
    public void addSemanticItem(EmotionElementType t, SemanticValue s) {
        getList(t).put(s.getName(), s);
    }

    /**
     *
     * @param t
     * @return
     */
    public Collection getSemanticItemList(EmotionElementType t) {
        return getList(t).values();
    }

    /**
     *
     * @param t
     * @param name
     * @return
     */
    public Float getSemanticValue(EmotionElementType t, String name) {
        Object o = getList(t).get(name);
        if (o != null) {
            return ((SemanticValue) o).getValue();
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String str = "Objects" + " -> " + objectRelationships.toString()
                + "\r\nPersons" + " -> " + personRelationships.toString()
                + "\r\nEvents" + " -> " + eventDesirability.toString();
        return str;

    }
}
