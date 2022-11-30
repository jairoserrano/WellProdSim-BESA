/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant.Utils;

/**
 *
 * @author jairo
 */
public enum PeasantInteractionsNames {
    FARMING("farming"), HARVEST("harvest");

    private String interactionName;

    private PeasantInteractionsNames(String interactionName) {
        interactionName = interactionName;
    }

    public String getInteractionName() {
        return interactionName;
    }
}
