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
package wpsPeasantFamily.Agent.Guards;

import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import org.json.JSONObject;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import static wpsPeasantFamily.Agent.Guards.FromWorldMessageType.*;
import wpsPeasantFamily.Data.CropCareType;
import wpsPeasantFamily.Data.SeasonType;
import wpsViewer.Agent.wpsReport;

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
        wpsReport.warn("🤖🤖🤖 Recibido: " + peasantCommMessage.getPeasantAlias() + " getType=" + peasantCommMessage.getPayload());
        StateBDI state = (StateBDI) this.agent.getState();
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) state.getBelieves();
        FromWorldMessageType messageType = peasantCommMessage.getMessageType();
        //wpsReport.info("🍙🍙🍙: " + peasantCommMessage.getPayload() + ":🍙🍙🍙");
        try {

            switch (messageType) {
                case NOTIFY_CROP_DISEASE:
                    believes.getPeasantProfile().setCropHealth(0.5);
                    break;
                case CROP_PESTICIDE:
                    believes.setCurrentCropCare(CropCareType.PESTICIDE);
                    //wpsReport.info("   🍙🤖   PESTICIDAAAAA  🤖 🍙  ");
                    break;
                case NOTIFY_CROP_WATER_STRESS:
                    believes.setCurrentCropCare(CropCareType.IRRIGATION);
                    break;
                case CROP_INFORMATION_NOTIFICATION:
                    //believes.getProfile().setPesticideSeason(true);
                    //wpsReport.info("🍙🍙🍙: PESTICIDAAAAA");
                    break;
                case NOTIFY_CROP_READY_HARVEST:
                    believes.setCurrentSeason(SeasonType.HARVEST);
                    break;
                case REQUEST_CROP_INFORMATION:
                    wpsReport.info("🍙🍙🍙: " + peasantCommMessage.getPayload());
                    break;
                case CROP_INIT:
                    //believes.getProfile().setPlantingSeason(true);
                    break;
                case CROP_HARVEST:
                    JSONObject cropData = new JSONObject(
                            peasantCommMessage.getPayload()
                    );
                    believes.getPeasantProfile().setHarvestedWeight(
                            Integer.parseInt(
                                    cropData.get("aboveGroundBiomass").toString()
                            )
                    );
                    break;
                default:
                    // Código a ejecutar si messageType no coincide con ninguno de los casos anteriores
                    break;
            }
        } catch (IllegalArgumentException e) {
            wpsReport.error(e.getStackTrace());
        }

    }

}