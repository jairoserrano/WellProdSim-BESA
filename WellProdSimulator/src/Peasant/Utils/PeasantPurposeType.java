/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant.Utils;

/**
 *
 * @author jairo
 */
public enum PeasantPurposeType {
    FARMER("farmer"),
    OTHER("other");

    private String type;

    private PeasantPurposeType(String s) {
        type = s;
    }

    public String getType() {
        return type;
    }

    public void setType(String tipo) {
        this.type = tipo;
    }
}
