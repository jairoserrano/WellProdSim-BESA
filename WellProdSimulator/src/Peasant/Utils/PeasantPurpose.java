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
public class PeasantPurpose implements Serializable  {
    private PeasantPurposeType purposeType;

    public PeasantPurpose() {
    }

    public PeasantPurpose(PeasantPurposeType purposeType) {
        this.purposeType = purposeType;
    }
    
    public String getName() {
        return purposeType.getType();
    }

    public void setName(String purposeType) {
        this.purposeType.setType(purposeType);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purposeType != null ? purposeType.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeasantPurpose)) {
            return false;
        }
        PeasantPurpose other = (PeasantPurpose) object;
        if ((this.purposeType == null && other.purposeType != null) || (this.purposeType != null && !this.purposeType.equals(other.purposeType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Purpose[ nombre=" + purposeType + " ]";
    }
    
}
