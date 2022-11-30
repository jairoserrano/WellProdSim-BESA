/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant;

import Peasant.Utils.PeasantProfile;
import Peasant.Utils.Purpose;
import java.util.List;
import java.util.Random;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author jairo
 */
public class PeasantAgentBelievesPurpose implements Believes {

    private PeasantAgentBelieves blvs;
    private PeasantProfile profile;
    private Purpose purpose;

    public PeasantProfile getProfile() {
        return profile;
    }

    public void setProfile(PeasantProfile profile) {
        this.profile = profile;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public PeasantAgentBelievesPurpose(PeasantAgentBelieves blvs) {
        this.blvs = blvs;
    }


    @Override
    public boolean update(InfoData si) {
        return true;
    }

    public Purpose selectPurpose() {
        return new Purpose("Agricultor");
    }

    @Override
    public Believes clone() throws CloneNotSupportedException {
        super.clone();
        return this;
    }

    /*public OpcionesAtencion getAtencionStrategy() {
        Random rand = new Random();
        OpcionesAtencion[] opcs = OpcionesAtencion.values();
        return opcs[rand.nextInt(opcs.length)];
    }*/

    @Override
    public String toString() {
        return "Profile and purpose{" + "profile=" + profile + ", blvs=" + blvs + '}';
    }
}
