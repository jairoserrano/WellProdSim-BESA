/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant.EmotionalModel;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jairo
 */
class EmotionAxisConfig implements Serializable {

    
    private Long id;
    private String positiveName;
    private String negativeName;
    private float baseValue;
    private List<EventInfluence> eventInfluence;
    private float forgetFactor;



    public EmotionAxisConfig() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositiveName() {
        return positiveName;
    }

    public void setPositiveName(String positiveName) {
        this.positiveName = positiveName;
    }

    public String getNegativeName() {
        return negativeName;
    }

    public void setNegativeName(String negativeName) {
        this.negativeName = negativeName;
    }

    public float getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(float baseValue) {
        this.baseValue = baseValue;
    }

    public List<EventInfluence> getEventInfluence() {
        return eventInfluence;
    }

    public void setEventInfluence(List<EventInfluence> eventInfluence) {
        this.eventInfluence = eventInfluence;
    }

    public Float getForgetFactor() {
        return forgetFactor;
    }

    public void setForgetFactor(Float forgetFactor) {
        this.forgetFactor = forgetFactor;
    }

}