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
package wpsPeasantFamily.Tasks.L3Development;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import wpsWorld.Agent.WorldGuard;
import wpsWorld.Messages.WorldMessage;
import static wpsWorld.Messages.WorldMessageType.CROP_HARVEST;
import wpsControl.Agent.wpsCurrentDate;
import rational.mapping.Believes;
import rational.mapping.Task;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsPeasantFamily.Agent.UpdateBelievesPeasantFamilyAgent;
import static wpsPeasantFamily.Data.SeasonType.NONE;
import wpsPeasantFamily.Data.TimeConsumedBy;
import static wpsPeasantFamily.Data.UpdateType.USE_TIME;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class HarvestCropsTask extends Task {

    /**
     *
     */
    public HarvestCropsTask() {
    }

    /**
     *
     * @param parameters
     */
    @Override
    public synchronized void executeTask(Believes parameters) {
        //wpsReport.info("⚙️⚙️⚙️");
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        String peasantFamilyAlias = believes.getPeasantProfile().getPeasantFamilyAlias();

        UpdateBelievesPeasantFamilyAgent.send(
                peasantFamilyAlias,
                USE_TIME,
                TimeConsumedBy.valueOf(this.getClass().getSimpleName())
        );
        UpdateBelievesPeasantFamilyAgent.send(
                peasantFamilyAlias,
                NONE
        );

        try {
            AdmBESA adm = AdmBESA.getInstance();
            AgHandlerBESA ah = adm.getHandlerByAlias(
                    believes.getPeasantProfile().getPeasantFamilyLandAlias()
            );

            WorldMessage worldMessage = new WorldMessage(
                    CROP_HARVEST,
                    believes.getPeasantProfile().getCurrentCropName(),
                    wpsCurrentDate.getInstance().getCurrentDate(),
                    peasantFamilyAlias
            );
            EventBESA ev = new EventBESA(
                    WorldGuard.class.getName(),
                    worldMessage);
            ah.sendEvent(ev);

        } catch (ExceptionBESA ex) {
            wpsReport.error(ex);
        }
        this.setTaskFinalized();
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void interruptTask(Believes parameters) {
        this.setTaskFinalized();
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void cancelTask(Believes parameters) {
        this.setTaskFinalized();
    }

    /**
     *
     * @param believes
     * @return
     */
    @Override
    public boolean checkFinish(Believes believes) {
        return true;
    }
}
