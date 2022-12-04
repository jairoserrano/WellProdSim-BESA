/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant;

import BESA.Log.ReportBESA;
import java.util.HashMap;
import java.util.Map;
import rational.data.InfoData;
import rational.mapping.Believes;

import Peasant.Utils.PeasantInteractionsNames;
import Peasant.Utils.SensorData;

/**
 *
 * @author jairo
 */
public class PeasantAgentBelieveInteractionState implements Believes {

    private Map<String, Boolean> interactions = new HashMap<>();
    private boolean confirmation = false;

    public PeasantAgentBelieveInteractionState() {
        interactions = new HashMap<>();
        for (PeasantInteractionsNames interaction : PeasantInteractionsNames.values()) {
            interactions.put(interaction.getInteractionName(), false);
        }
    }

    @Override
    public boolean update(InfoData si) {
        ReportBESA.info("PeasantAgentBelieveInteractionState update Received: " + si);
        SensorData infoRecibida = (SensorData) si;

        if (infoRecibida.getDataP().containsKey("endVideo")) {
            confirmation = (boolean) infoRecibida.getDataP().get("endVideo");
        }

        return true;
    }

    @Override
    public Believes clone() throws CloneNotSupportedException {
        super.clone();
        return this;
    }

}
