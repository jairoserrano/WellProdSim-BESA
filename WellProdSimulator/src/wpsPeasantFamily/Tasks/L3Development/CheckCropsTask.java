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
import static wpsWorld.Messages.WorldMessageType.CROP_INFORMATION;
import wpsControl.Agent.wpsCurrentDate;
import rational.mapping.Believes;
import rational.mapping.Task;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsPeasantFamily.Data.TimeConsumedBy;
import wpsViewer.Agent.wpsReport;
import static wpsWorld.Messages.WorldMessageType.CROP_OBSERVE;

/**
 *
 * @author jairo
 */
public class CheckCropsTask extends Task {

    private boolean finished;

    /**
     *
     */
    public CheckCropsTask() {
        ////wpsReport.info("");
        this.finished = false;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void executeTask(Believes parameters) {
        wpsReport.info("⚙️⚙️⚙️");
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        believes.getPeasantProfile().setCropCheckedToday();
        // @TODO: falta calcular el tiempo necesario para el cultivo
        believes.getPeasantProfile().useTime(TimeConsumedBy.CheckCropsTask);

        try {
            AdmBESA adm = AdmBESA.getInstance();
            AgHandlerBESA ah = adm.getHandlerByAlias(
                    believes.getPeasantProfile().getFarmName());

            WorldMessage worldMessage;

            if (Math.random() < 0.2) {
                worldMessage = new WorldMessage(
                        CROP_INFORMATION,
                        believes.getPeasantProfile().getCurrentCropName(),
                        wpsCurrentDate.getInstance().getCurrentDate(),
                        believes.getPeasantProfile().getPeasantFamilyAlias()
                );
                wpsReport.warn("enviado CROP_INFORMATION");
            } else {
                worldMessage = new WorldMessage(
                        CROP_OBSERVE,
                        believes.getPeasantProfile().getCurrentCropName(),
                        wpsCurrentDate.getInstance().getCurrentDate(),
                        believes.getPeasantProfile().getPeasantFamilyAlias()
                );
                wpsReport.warn("enviado CROP_OBSERVE");
            }

            EventBESA ev = new EventBESA(
                    WorldGuard.class.getName(),
                    worldMessage);
            ah.sendEvent(ev);

            //wpsReport.debug("🗓️🗓️🗓️ Date: " + wpsCurrentDate.getInstance().getCurrentDate());
            this.setFinished();
            //this.setTaskWaitingForExecution();

        } catch (ExceptionBESA ex) {
            wpsReport.error(ex);
        }
    }

    /**
     *
     * @return
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     *
     */
    public void setFinished() {
        this.setTaskFinalized();
        this.finished = true;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void interruptTask(Believes parameters) {
        this.setFinished();
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void cancelTask(Believes parameters) {
        this.setFinished();
    }

    /**
     *
     * @return
     */
    public boolean isExecuted() {
        return finished;
    }

    /**
     *
     * @param believes
     * @return
     */
    @Override
    public boolean checkFinish(Believes believes) {
        return isExecuted();
    }
}
