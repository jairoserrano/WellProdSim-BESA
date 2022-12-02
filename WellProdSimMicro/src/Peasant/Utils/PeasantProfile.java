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
public class PeasantProfile implements Serializable  {
    
    private String name;
    private FarmerProfile farmerProfile;
    
    public PeasantProfile(String name) {
        this.name = name;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeasantProfile)) {
            return false;
        }
        PeasantProfile other = (PeasantProfile) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Perfil[ name=" + name + " ]";
    }
    
    public FarmerProfile getFarmerProfile() {
        return farmerProfile;
    }

    public void setFarmerProfile(FarmerProfile farmerProfile) {
        this.farmerProfile = farmerProfile;
    }

}
