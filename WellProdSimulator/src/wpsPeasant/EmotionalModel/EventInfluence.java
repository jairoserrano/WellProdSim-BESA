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

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getEventName() {
        return eventName;
    }

    /**
     *
     * @param eventName
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     *
     * @return
     */
    public Float getEventInfluence() {
        return eventInfluence;
    }

    /**
     *
     * @param eventInfluence
     */
    public void setEventInfluence(Float eventInfluence) {
        this.eventInfluence = eventInfluence;
    }
    
}   