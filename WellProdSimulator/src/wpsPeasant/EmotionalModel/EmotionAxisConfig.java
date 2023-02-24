/**
 * ==========================================================================
 * __      __ _ __   ___  *    WellProdSim                                  *
 * \ \ /\ / /| '_ \ / __| *    @version 1.0                                 *
 *  \ V  V / | |_) |\__ \ *    @since 2023                                  *
 *   \_/\_/  | .__/ |___/ *                                                 *
 *           | |          *    @author Jairo Serrano                        *
 *           |_|          *    @author Enrique Gonzalez                     *
 * ==========================================================================
 * Social Simulator used to estimate productivity and well-being of peasant *
 * families. It is event oriented, high concurrency, heterogeneous time     *
 * management and emotional reasoning BDI.                                  *
 * ==========================================================================
 */
package wpsPeasant.EmotionalModel;

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