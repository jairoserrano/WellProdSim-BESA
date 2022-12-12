/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant.Utils;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Log.ReportBESA;
import BESA.World.agents.ExternalComm.ExternalCommGuard;

/**
 *
 * @author jairo
 */
public class ReceiveMessagesFromWorldGuard extends ExternalCommGuard {

    @Override
    public void funcExecGuard(EventBESA event) {
        ReportBESA.debug(" >>> " + event.getSenderAgId());
    }
    
}
