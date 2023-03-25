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

    /**
     *
     */
    protected String person;

    /**
     *
     */
    protected String event;

    /**
     *
     */
    protected String object;

    /**
     *
     */
    public EmotionalEvent() {
    }

    /**
     *
     * @param person
     * @param event
     * @param object
     */
    public EmotionalEvent(String person, String event, String object) {
        this.person = person;
        this.event = event;
        this.object = object;
    }

    /**
     *
     * @return
     */
    public String getPerson() {
        return person;
    }

    /**
     *
     * @param person
     */
    public void setPerson(String person) {
        this.person = person;
    }

    /**
     *
     * @return
     */
    public String getEvent() {
        return event;
    }

    /**
     *
     * @param event
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     *
     * @return
     */
    public String getObject() {
        return object;
    }

    /**
     *
     * @param object
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Person: " + this.person + " Event: " + this.event + " Object: " + this.object;
    }
    
}
