/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant.Utils;

import java.io.Serializable;

/**
 *
 * @author jairo
 */
public class PeasantHarvestPreference implements Serializable  {
    
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
        if (!(object instanceof PeasantHarvestPreference)) {
            return false;
        }
        PeasantHarvestPreference other = (PeasantHarvestPreference) object;
        if ((this.crop == null && other.crop != null) || (this.crop != null && !this.crop.equals(other.crop))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Preferenciaxcuento[ preferenciaxcuentoPK=" + crop + " ]";
    }
}
