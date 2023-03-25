/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wpsPeasant.EmotionalModel;

/**
 *
 * @author jairo
 */
public class SemanticValue {
    private final String name;
    private final float value;
    
    /**
     *
     * @param name
     * @param value
     */
    public SemanticValue(String name, float value) {
        this.name = name;
        this.value = Utils.checkNegativeOneToOneLimits(value);
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public float getValue() {
        return value;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return name + ": " + value;
    }
}

