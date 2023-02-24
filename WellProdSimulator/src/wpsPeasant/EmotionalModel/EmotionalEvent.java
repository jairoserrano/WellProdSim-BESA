/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wpsPeasant.EmotionalModel;

/**
 *
 * @author jairo
 */
public class EmotionalEvent {

    protected String person;
    protected String event;
    protected String object;

    public EmotionalEvent() {
    }

    public EmotionalEvent(String person, String event, String object) {
        this.person = person;
        this.event = event;
        this.object = object;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Person: " + this.person + " Event: " + this.event + " Object: " + this.object;
    }
    
}
