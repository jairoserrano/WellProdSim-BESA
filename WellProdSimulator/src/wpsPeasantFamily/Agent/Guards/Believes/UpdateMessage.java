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
package wpsPeasantFamily.Agent.Guards.Believes;

import BESA.Kernel.Agent.Event.DataBESA;
import wpsPeasantFamily.Data.CropCareType;
import wpsPeasantFamily.Data.MoneyOriginType;
import wpsPeasantFamily.Data.TimeConsumedBy;
import wpsPeasantFamily.Data.UpdateType;

/**
 *
 * @author jairo
 */
public class UpdateMessage extends DataBESA {

    private UpdateType updateType;
    private TimeConsumedBy usedTime;
    private MoneyOriginType moneyOriginType;
    private CropCareType cropCareType;
    
    public UpdateMessage(UpdateType updateType, CropCareType cropCareType) {
        this.updateType = updateType;
        this.cropCareType = cropCareType;
    }
    /**
     *
     * @param updateType
     */
    public UpdateMessage(UpdateType updateType) {
        this.updateType = updateType;
    }
    /**
     *
     * @param moneyOriginType
     */
    public UpdateMessage(UpdateType updateType, MoneyOriginType moneyOriginType) {
        this.updateType = updateType;
        this.moneyOriginType = moneyOriginType;
    }
    /**
     *
     * @param updateType
     * @param usedTime
     */
    public UpdateMessage(UpdateType updateType, TimeConsumedBy usedTime) {
        this.updateType = updateType;
        this.usedTime = usedTime;
    }

    public CropCareType getCropCareType() {
        return cropCareType;
    }

    public void setCropCareType(UpdateType updateType, CropCareType cropCareType) {
        this.updateType = updateType;
        this.cropCareType = cropCareType;
    }


    public MoneyOriginType getMoneyOriginType() {
        return moneyOriginType;
    }

    public void setMoneyOriginType(UpdateType updateType, MoneyOriginType moneyOriginType) {
        this.moneyOriginType = moneyOriginType;
    }

    public UpdateType getUpdateType() {
        return updateType;
    }

    public void setUpdateTypeType(UpdateType updateType) {
        this.updateType = updateType;
    }

    public TimeConsumedBy getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(TimeConsumedBy usedTime) {
        this.usedTime = usedTime;
    }


}
