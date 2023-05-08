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
package wpsPeasantFamily.Agent;

import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Log.ReportBESA;

/**
 *
 * @author jairo
 */
public class FromWorldGuard extends GuardBESA {

    /**
     *
     * @param event
     */
    @Override
    public void funcExecGuard(EventBESA event) {
        FromWorldMessage peasantCommMessage = (FromWorldMessage) event.getData();
        ReportBESA.debug("🤖🤖🤖 Recibido: " + peasantCommMessage.getPeasantAlias() + " getType=" + peasantCommMessage.getPayload());

        StateBDI state = (StateBDI) this.agent.getState();
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) state.getBelieves();

        FromWorldMessageType messageType = peasantCommMessage.getMessageType();// .getPayload();
        
        ReportBESA.info("\n\n\n🍙🍙🍙: " + peasantCommMessage.getPayload() + "\n\n");

        try {

            switch (messageType) {
                case NOTIFY_CROP_DISEASE:
                    believes.getPeasantProfile().setCropHealth(0.5);
                    break;
                case NOTIFY_CROP_WATER_STRESS:
                    believes.getPeasantProfile().setCropHealth(0.5);
                    break;
                case CROP_INFORMATION_NOTIFICATION:
                    ReportBESA.info("🍙🍙🍙: " + peasantCommMessage.getPayload());
                    break;
                case NOTIFY_CROP_READY_HARVEST:
                    believes.getPeasantProfile().setHarverstSeason(true);
                    believes.getPeasantProfile().setGrowingSeason(false);
                    break;
                case REQUEST_CROP_INFORMATION:
                    ReportBESA.info("🍙🍙🍙: " + peasantCommMessage.getPayload());
                    break;
                case CROP_INIT:
                    //believes.getPeasantProfile().setPlantingSeason(true);
                    break;
                case CROP_HARVEST:
                    believes.getPeasantProfile().setHarverstSeason(false);
                    believes.getPeasantProfile().setGrowingSeason(false);
                    break;
                default:
                    // Código a ejecutar si messageType no coincide con ninguno de los casos anteriores
                    break;
            }
        } catch (IllegalArgumentException e) {
            ReportBESA.error("Mensaje no reconocido de FromWorldMessageType");
        }

    }

}
