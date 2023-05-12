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
package wpsPeasantFamily.Tasks.L4SkillsResources;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import rational.mapping.Believes;
import rational.mapping.Task;
import wpsActivator.wpsStart;
import wpsPeasantFamily.Agent.PeasantFamilyBDIAgentBelieves;
import wpsPeasantFamily.Utils.TimeConsumedBy;
import wpsSocietyMarket.MarketAgentGuard;
import wpsSocietyMarket.MarketMessage;
import static wpsSocietyMarket.MarketMessageType.BUY_LIVESTOCK;
import static wpsSocietyMarket.MarketMessageType.BUY_SEEDS;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class ObtainLivestockTask extends Task {

    private boolean finished;

    /**
     *
     */
    public ObtainLivestockTask() {
        ////wpsReport.info("");
        this.finished = false;
    }

    /**
     *
     * @param parameters
     */
    @Override
    public void executeTask(Believes parameters) {
        PeasantFamilyBDIAgentBelieves believes = (PeasantFamilyBDIAgentBelieves) parameters;
        //wpsReport.info("$ Asking for a LOAN to the Bank " + believes.getPeasantProfile().getMoney());

        // @TODO: Se debe calcular cuanto necesitas prestar hasta que se coseche.
        try {
            AdmBESA adm = AdmBESA.getInstance();
            AgHandlerBESA ah = adm.getHandlerByAlias(wpsStart.aliasMarketAgent);

            MarketMessage marketMessage = new MarketMessage(
                    BUY_LIVESTOCK,
                    believes.getPeasantProfile().getProfileName(),
                    1);

            EventBESA ev = new EventBESA(
                    MarketAgentGuard.class.getName(),
                    marketMessage);
            ah.sendEvent(ev);

            believes.getPeasantProfile().useTime(TimeConsumedBy.ObtainLivestock);

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
