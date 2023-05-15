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
import static wpsWorld.Messages.WorldMessageType.CROP_INIT;
import rational.mapping.Believes;
import rational.mapping.Task;
import wpsControl.Agent.wpsCurrentDate;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsPeasantFamily.Utils.TimeConsumedBy;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class PlantCropTask extends Task {

    private boolean finished;

    /**
     *
     */
    public PlantCropTask() {
        ////wpsReport.info("");
        this.finished = false;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void executeTask(Believes parameters) {
        wpsReport.info("");
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        believes.getPeasantProfile().setGrowingSeason(true);
        believes.getPeasantProfile().setPreparationSeason(false);
        believes.getPeasantProfile().setPlantingSeason(false);

        try {
            AdmBESA adm = AdmBESA.getInstance();
            AgHandlerBESA ah = adm.getHandlerByAlias(
                    believes.getPeasantProfile().getFarmName());

            WorldMessage worldMessage = new WorldMessage(
                    CROP_INIT,
                    believes.getPeasantProfile().getProfileName(),
                    wpsCurrentDate.getInstance().getCurrentDate(),
                    believes.getPeasantProfile().getProfileName()
            );

            wpsReport.info(worldMessage);

            EventBESA ev = new EventBESA(
                    WorldGuard.class.getName(),
                    worldMessage);
            ah.sendEvent(ev);

            believes.getPeasantProfile().useSeeds(
                    believes.getPeasantProfile().getRiceSeedsByHectare()
            );
            // 2500 kilo * 20 kilos de semillas
            believes.getPeasantProfile().useMoney(
                    believes.getPeasantProfile().getPriceList().get("seeds").getCost()
                    * 20
            );
            // Comienza la temporada de siembra
            believes.getPeasantProfile().useTime(TimeConsumedBy.PlantCrops);

        } catch (ExceptionBESA ex) {
            wpsReport.error(ex);
        }
        this.setTaskWaitingForExecution();
    }

    /**
     *
     * @return
     */
    public boolean isFinished() {
        ////wpsReport.info("");
        return finished;
    }

    /**
     *
     * @param finished
     */
    public void setFinished(boolean finished) {
        ////wpsReport.info("");
        this.finished = finished;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void interruptTask(Believes parameters) {
        ////wpsReport.info("");
        this.setFinished(true);
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void cancelTask(Believes parameters) {
        ////wpsReport.info("");
        this.setFinished(true);
    }

    /**
     *
     * @return
     */
    public boolean isExecuted() {
        ////wpsReport.info("");
        return finished;
    }

    /**
     *
     * @param believes
     * @return
     */
    @Override
    public boolean checkFinish(Believes believes) {
        ////wpsReport.info("");
        return isExecuted();
    }
}