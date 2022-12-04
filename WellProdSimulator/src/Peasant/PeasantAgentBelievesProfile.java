/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant;

import Peasant.Utils.PeasantProfile;
import Peasant.Utils.PeasantPurpose;
import Peasant.Utils.PeasantPurposeType;
import java.util.List;
import java.util.Random;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author jairo
 */
public class PeasantAgentBelievesProfile implements Believes {

    private PeasantAgentBelieves blvs;
    private PeasantProfile profile;

    public PeasantProfile getProfile() {
        return profile;
    }

    public void setProfile(PeasantProfile profile) {
        this.profile = profile;
    }

    public PeasantAgentBelievesProfile(PeasantAgentBelieves blvs) {
        this.blvs = blvs;
    }


    @Override
    public boolean update(InfoData si) {
        return true;
    }

    public PeasantPurpose selectPurpose() {
        return new PeasantPurpose(PeasantPurposeType.FARMER);
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
