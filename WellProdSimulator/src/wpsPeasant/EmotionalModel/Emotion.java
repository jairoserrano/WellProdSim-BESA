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

import java.time.LocalTime;

/**
 *
 * @author jairo
 */
public class Emotion {
    private LocalTime time;
    private float valence;

    /**
     *
     * @param valence
     */
    public Emotion(float valence) {
        this.valence = valence;
        this.time= LocalTime.now();
    }
    
    /**
     *
     * @return
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     *
     * @return
     */
    public float getValence() {
        return valence;
    }

    /**
     *
     * @param valence
     */
    public void setValence(float valence) {
        this.valence = valence;
    }
}
