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
public class FarmerProfile implements Serializable {

    private int fast;
    private String profileName = "farmer";

    
    public FarmerProfile(int fast) {
        this.fast = fast;
    }
    
    public int getFast() {
        return fast;
    }

    public void setFast(int fast) {
        this.fast = fast;
    }
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (profileName != null ? profileName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FarmerProfile)) {
            return false;
        }
        FarmerProfile other = (FarmerProfile) object;
        if ((this.profileName == null && other.profileName != null) || (this.profileName != null && !this.profileName.equals(other.profileName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "profileName[ profileName=" + profileName + " ]";
    }
    
}
