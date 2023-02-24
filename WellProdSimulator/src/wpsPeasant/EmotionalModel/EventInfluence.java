/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wpsPeasant.EmotionalModel;

import java.io.Serializable;

/**
 *
 * @author jairo
 */
public class EventInfluence  implements Serializable {

    private Long id;    
    String eventName;
    Float eventInfluence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Float getEventInfluence() {
        return eventInfluence;
    }

    public void setEventInfluence(Float eventInfluence) {
        this.eventInfluence = eventInfluence;
    }
    
}   