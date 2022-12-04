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
