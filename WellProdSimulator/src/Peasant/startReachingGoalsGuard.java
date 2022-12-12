/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Log.ReportBESA;

/**
 *
 * @author jairo
 */
public class startReachingGoalsGuard extends PeriodicGuardBESA{
    
    @Override
    public void funcPeriodicExecGuard(EventBESA event) {
        //ReportBESA.debug("------------------------------------------------> startReachingGoalsGuard --");
        ((PeasantBDIAgent)this.getAgent()).BDIPulse();
    }
    
}
