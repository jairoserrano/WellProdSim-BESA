/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant.EmotionalModel;

import java.time.LocalTime;

/**
 *
 * @author jairo
 */
public class Emotion {
    private LocalTime time;
    private float valence;

    public Emotion(float valence) {
        this.valence = valence;
        this.time= LocalTime.now();
    }
    

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public float getValence() {
        return valence;
    }

    public void setValence(float valence) {
        this.valence = valence;
    }
}
