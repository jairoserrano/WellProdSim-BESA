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

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import wpsActivator.wpsStart;
import wpsPeasantFamily.Agent.Guards.Believes.SeasonGuard;
import wpsPeasantFamily.Agent.Guards.Believes.SeasonMessage;
import wpsPeasantFamily.Agent.Guards.Believes.UpdateGuard;
import wpsPeasantFamily.Agent.Guards.Believes.UpdateMessage;
import wpsPeasantFamily.Data.CropCareType;
import wpsPeasantFamily.Data.MoneyOriginType;
import wpsPeasantFamily.Data.SeasonType;
import wpsPeasantFamily.Data.TimeConsumedBy;
import wpsPeasantFamily.Data.UpdateType;
import wpsSocietyBank.Agent.BankAgentGuard;
import wpsSocietyBank.Agent.BankMessage;
import wpsSocietyBank.Agent.BankMessageType;
import static wpsSocietyBank.Agent.BankMessageType.ASK_CURRENT_TERM;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class UpdateBelievesPeasantFamilyAgent {

    public static void send(String peasantFamilyAlias, UpdateType updateType, TimeConsumedBy timeConsumedBy) {
        try {
            AdmBESA adm = AdmBESA.getInstance();
            UpdateMessage updateMessage = new UpdateMessage(updateType, timeConsumedBy);
            EventBESA eventBesa = new EventBESA(UpdateGuard.class.getName(), updateMessage);
            AgHandlerBESA agHandler = adm.getHandlerByAlias(peasantFamilyAlias);
            agHandler.sendEvent(eventBesa);
        } catch (ExceptionBESA ex) {
            wpsReport.error(ex);
        }
    }

    public static void send(String peasantFamilyAlias, UpdateType updateType) {
        try {
            AdmBESA adm = AdmBESA.getInstance();
            UpdateMessage updateMessage = new UpdateMessage(updateType);
            EventBESA eventBesa = new EventBESA(UpdateGuard.class.getName(), updateMessage);
            AgHandlerBESA agHandler = adm.getHandlerByAlias(peasantFamilyAlias);
            agHandler.sendEvent(eventBesa);
        } catch (ExceptionBESA ex) {
            wpsReport.error(ex);
        }
    }

    public static void send(String peasantFamilyAlias, SeasonType seasonType) {
        try {
            AdmBESA adm = AdmBESA.getInstance();
            SeasonMessage seasonMessage = new SeasonMessage(seasonType);
            EventBESA eventBesa = new EventBESA(SeasonGuard.class.getName(), seasonMessage);
            AgHandlerBESA agHandler = adm.getHandlerByAlias(peasantFamilyAlias);
            agHandler.sendEvent(eventBesa);
        } catch (ExceptionBESA ex) {
            wpsReport.error(ex);
        }
    }

    public static void send(String peasantFamilyAlias, BankMessageType bankMessageType) {
        try {
            AdmBESA adm = AdmBESA.getInstance();
            AgHandlerBESA ah = adm.getHandlerByAlias(wpsStart.config.getBankAgentName());

            BankMessage bankMessage = new BankMessage(
                    bankMessageType,
                    peasantFamilyAlias
            );

            EventBESA ev = new EventBESA(
                    BankAgentGuard.class.getName(),
                    bankMessage);
            ah.sendEvent(ev);

        } catch (ExceptionBESA ex) {
            wpsReport.error(ex);
        }
    }

    public static void send(String peasantFamilyAlias, MoneyOriginType moneyOriginType) {
        try {
            AdmBESA adm = AdmBESA.getInstance();
            UpdateMessage updateMessage = new UpdateMessage(UpdateType.MONEY_ORIGIN, moneyOriginType);
            EventBESA eventBesa = new EventBESA(UpdateGuard.class.getName(), updateMessage);
            AgHandlerBESA agHandler = adm.getHandlerByAlias(peasantFamilyAlias);
            agHandler.sendEvent(eventBesa);
        } catch (ExceptionBESA ex) {
            wpsReport.error(ex);
        }
    }

    public static void send(String peasantFamilyAlias, CropCareType cropCareType) {
        try {
            AdmBESA adm = AdmBESA.getInstance();
            UpdateMessage updateMessage = new UpdateMessage(UpdateType.CHANGE_CROP_CARE, cropCareType);
            EventBESA eventBesa = new EventBESA(UpdateGuard.class.getName(), updateMessage);
            AgHandlerBESA agHandler = adm.getHandlerByAlias(peasantFamilyAlias);
            agHandler.sendEvent(eventBesa);
        } catch (ExceptionBESA ex) {
            wpsReport.error(ex);
        }
    }
}
