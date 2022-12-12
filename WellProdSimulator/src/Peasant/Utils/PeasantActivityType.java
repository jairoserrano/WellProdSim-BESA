/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant.Utils;

/**
 *
 * @author jairo
 */
public enum PeasantActivityType {
    FARMING("farming"),
    IRRIGATING("irrigating"),
    HARVESTING("harvesting"),
    RESTING("resting");

    private String type;

    private PeasantActivityType(String s) {
        type = s;
    }

    public String getType() {
        return type;
    }

    public void setType(String tipo) {
        this.type = tipo;
    }
}
