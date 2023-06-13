/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wpsPeasantFamily.Agent.Guards;

import BESA.Kernel.Agent.Event.DataBESA;

/**
 *
 * @author jairo
 */
public class ToControlMessage extends DataBESA {

    String peasantFamilyAlias;
    int days;
    boolean peasantOff;

    public ToControlMessage(String peasantFamilyAlias, int days) {
        this.days = days;
        this.peasantFamilyAlias = peasantFamilyAlias;
    }

    public ToControlMessage(String peasantFamilyAlias, int days, boolean peasantOff) {
        this.peasantOff = peasantOff;
        this.peasantFamilyAlias = peasantFamilyAlias;
        this.days = days;
    }

    public boolean isPeasantOff() {
        return peasantOff;
    }

    public void setPeasantOff(boolean peasantOff) {
        this.peasantOff = peasantOff;
    }

    public String getPeasantFamilyAlias() {
        return peasantFamilyAlias;
    }

    public void setPeasantFamilyAlias(String peasantFamilyAlias) {
        this.peasantFamilyAlias = peasantFamilyAlias;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

}
