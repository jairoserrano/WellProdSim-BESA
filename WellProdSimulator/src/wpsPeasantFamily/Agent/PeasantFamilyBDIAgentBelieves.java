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
package wpsPeasantFamily.Agent;

import BESA.Log.ReportBESA;
import rational.data.InfoData;
import rational.mapping.Believes;
import wpsPeasant.EmotionalModel.EmotionalState;
import wpsPeasantFamily.Utils.PeasantFamilyProfile;

/**
 *
 * @author jairo
 */
public class PeasantFamilyBDIAgentBelieves implements Believes {

    private PeasantFamilyProfile peasantProfile;
    private EmotionalState peasantEmotionalState;


    /**
     *
     * @param peasantProfile
     */
    public PeasantFamilyBDIAgentBelieves(PeasantFamilyProfile peasantProfile) {
        ReportBESA.debug(">> New Peasant " + peasantProfile.getProfileName());
        this.setPeasantProfile(peasantProfile);
        this.peasantEmotionalState = new EmotionalState();
    }

    /**
     *
     * @return
     */
    public EmotionalState getPeasantEmotionalState() {
        //ReportBESA.info("");
        return peasantEmotionalState;
    }

    /**
     *
     * @return
     */
    public PeasantFamilyProfile getPeasantProfile() {
        //ReportBESA.info("");
        return peasantProfile;
    }

    /**
     *
     * @param peasantProfile
     */
    private void setPeasantProfile(PeasantFamilyProfile peasantProfile) {
        this.peasantProfile = peasantProfile;
    }

    /**
     *
     * @param infoData
     * @return
     */
    @Override
    public boolean update(InfoData infoData) {
        //ReportBESA.info("");
        return true;
    }

    /**
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Believes clone() throws CloneNotSupportedException {
        return this;
    }

}