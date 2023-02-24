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
package wpsPeasant.Utils;

import java.io.Serializable;

/**
 *
 * @author jairo
 */
public class PeasantFarmingPreference implements Serializable  {
    
    private Crop crop;
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crop != null ? crop.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeasantFarmingPreference)) {
            return false;
        }
        PeasantFarmingPreference other = (PeasantFarmingPreference) object;
        if ((this.crop == null && other.crop != null) || (this.crop != null && !this.crop.equals(other.crop))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PeasantFarmingPreference[ Preference=" + crop + " ]";
    }
}
