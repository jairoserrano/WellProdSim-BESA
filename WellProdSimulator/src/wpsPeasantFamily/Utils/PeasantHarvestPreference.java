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
package wpsPeasantFamily.Utils;

import java.io.Serializable;

/**
 *
 * @author jairo
 */
public class PeasantHarvestPreference implements Serializable  {
    
    private Crop crop;
    
    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crop != null ? crop.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeasantHarvestPreference)) {
            return false;
        }
        PeasantHarvestPreference other = (PeasantHarvestPreference) object;
        if ((this.crop == null && other.crop != null) || (this.crop != null && !this.crop.equals(other.crop))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "ResPwAEntities.Preferenciaxcuento[ preferenciaxcuentoPK=" + crop + " ]";
    }
}
