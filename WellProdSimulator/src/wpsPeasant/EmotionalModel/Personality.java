/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wpsPeasant.EmotionalModel;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jairo
 */
public class Personality {

    public enum EmotionElementType {
        Object, Person, Event
    }
    private final Map<String, String> objectRelationships;
    private final Map<String, String> personRelationships;
    private final Map<String, String> eventDesirability;

    public Personality() {
        personRelationships = new HashMap<>();
        objectRelationships = new HashMap<>();
        eventDesirability = new HashMap<>();
    }

    private Map getList(EmotionElementType t) {
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

    public void setPersonRelationship(String person, String relationship) {
        personRelationships.put(person, relationship);
    }

    public void setObjectRelationship(String object, String relationship) {
        objectRelationships.put(object, relationship);
    }

    public void setEventDesirability(String event, String desirability) {
        eventDesirability.put(event, desirability);
    }

    public Float getElementSemanticValue(EmotionElementType t, String name) {
        String val = (String) getList(t).get(name);
        if (val != null) {
            return SemanticDictionary.getInstance().getSemanticValue(t, val);
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "Objects" + " -> " + objectRelationships.toString()
                + "\r\nPersons" + " -> " + personRelationships.toString()
                + "\r\nEvents" + " -> " + eventDesirability.toString();
        return str;
    }
}
