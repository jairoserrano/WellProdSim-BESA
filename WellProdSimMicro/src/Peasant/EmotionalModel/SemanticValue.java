/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant.EmotionalModel;

/**
 *
 * @author jairo
 */
public class SemanticValue {
    private final String name;
    private final float value;
    
    public SemanticValue(String name, float value) {
        this.name = name;
        this.value = Utils.checkNegativeOneToOneLimits(value);
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return name + ": " + value;
    }
}

