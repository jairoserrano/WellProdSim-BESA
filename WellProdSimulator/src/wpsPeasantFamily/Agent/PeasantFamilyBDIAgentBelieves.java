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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import wpsPeasantFamily.Data.PeasantFamilyProfile;
import rational.data.InfoData;
import rational.mapping.Believes;
import wpsControl.Agent.ControlCurrentDate;
import wpsPeasant.EmotionalModel.EmotionalState;
import wpsPeasantFamily.Data.CropCareType;
import wpsPeasantFamily.Data.FarmingResource;
import wpsPeasantFamily.Data.MoneyOriginType;
import wpsPeasantFamily.Data.PeasantActivityType;
import wpsPeasantFamily.Data.PeasantLeisureType;
import wpsPeasantFamily.Data.ResourceNeededType;
import wpsPeasantFamily.Data.SeasonType;
import wpsPeasantFamily.Data.TimeConsumedBy;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class PeasantFamilyBDIAgentBelieves implements Believes {

    private PeasantFamilyProfile peasantProfile;
    private EmotionalState peasantEmotionalState;
    private SeasonType currentSeason;
    private CropCareType currentCropCare;
    private MoneyOriginType currentMoneyOrigin;
    private PeasantActivityType currentPeasantActivityType;
    private PeasantLeisureType currentPeasantLeisureType;
    private ResourceNeededType currentResourceNeededType;

    private int currentDay;
    private int roberyAccount;

    private double timeLeftOnDay;
    private boolean newDay;
    private boolean weekBlock;
    private boolean busy;

    private String internalCurrentDate;
    private String ptwDate;

    private Map<String, FarmingResource> priceList = new HashMap<>();

    /**
     *
     * @param alias
     * @param peasantProfile
     */
    public PeasantFamilyBDIAgentBelieves(String alias, PeasantFamilyProfile peasantProfile) {
        this.setPeasantProfile(peasantProfile);
        this.internalCurrentDate = ControlCurrentDate.getInstance().getCurrentDate();
        this.peasantProfile.setPeasantFamilyAlias(alias);
        this.peasantEmotionalState = new EmotionalState();

        this.busy = false;
        this.currentDay = 1;
        this.timeLeftOnDay = 24;
        this.newDay = true;
        this.weekBlock = false;
        this.priceList.clear();

        this.currentSeason = SeasonType.NONE;
        this.currentCropCare = CropCareType.NONE;
        this.currentMoneyOrigin = MoneyOriginType.NONE;
        this.currentPeasantActivityType = PeasantActivityType.NONE;
        this.currentPeasantLeisureType = PeasantLeisureType.NONE;

    }
    public int getRoberyAccount() {
        return roberyAccount;
    }
    public void increaseRoberyAccount() {
        this.roberyAccount++;
    }
    public String getPtwDate() {
        return ptwDate;
    }
    public void setPtwDate(String ptwDate) {
        this.ptwDate = ptwDate;
    }

    /**
     *
     * Make variable reset Every Day
     */
    public void makeNewDay() {
        this.currentDay++;
        this.timeLeftOnDay = 24;
        this.newDay = true;
        //wpsReport.warn("internalCurrentDate= " + internalCurrentDate);
        this.internalCurrentDate = ControlCurrentDate.getInstance().getDatePlusOneDay(internalCurrentDate);
        //wpsReport.warn("internalCurrentDate2= " + internalCurrentDate);
        /*if (this.currentSeason == SeasonType.GROWING) {
            this.currentCropCare = CropCareType.CHECK;
        }*/
        //wpsReport.debug(this.peasantProfile.getPeasantFamilyAlias() + " NEW DAY internalCurrentDate: " + internalCurrentDate);
    }

    /**
     *
     * @return
     */
    public int getCurrentDay() {
        return currentDay;
    }

    /**
     *
     * @param currentDay
     */
    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    /**
     *
     * @return
     */
    public double getTimeLeftOnDay() {
        return timeLeftOnDay;
    }

    /**
     *
     * @param timeLeftOnDay
     */
    public void setTimeLeftOnDay(double timeLeftOnDay) {
        this.timeLeftOnDay = timeLeftOnDay;
    }

    /**
     *
     * @return
     */
    public String getInternalCurrentDate() {
        return internalCurrentDate;
    }

    /**
     *
     * @param internalCurrentDate
     */
    public void setInternalCurrentDate(String internalCurrentDate) {
        this.internalCurrentDate = internalCurrentDate;
    }

    /**
     * Time unit defined by hours spent on activities.
     *
     * @param time
     */
    public void useTime(TimeConsumedBy time) {
        decreaseTime(time.getTime());
    }

    /**
     * Time unit defined by hours spent on activities.
     *
     * @param time
     */
    public void useTime(double time) {
        decreaseTime(time);
    }

    /**
     * Time unit defined by hours spent on activities.
     *
     * @param time
     */
    public void decreaseTime(double time) {
        //wpsReport.warn("time " + time.getTime());
        this.timeLeftOnDay = this.timeLeftOnDay - time;
        //wpsReport.warn("time " + timeLeft);
        if (this.timeLeftOnDay <= 0) {
            wpsReport.info("⏳ NewDay para "
                    + this.peasantProfile.getPeasantFamilyAlias()
                    + " con "
                    + this.peasantProfile.getHealth()
                    + " de Salud."
            );
            this.makeNewDay();
        } else {
            wpsReport.info("⏳⏳ "
                    + this.peasantProfile.getPeasantFamilyAlias()
                    + " Le quedan "
                    + this.timeLeftOnDay
                    + " horas del día "
                    + internalCurrentDate
                    + " con "
                    + this.peasantProfile.getHealth()
                    + " de Salud."
            );
        }
    }

    /**
     *
     * @param time
     * @return
     */
    public boolean haveTimeAvailable(TimeConsumedBy time) {
        return this.timeLeftOnDay - time.getTime() >= 0;
        //wpsReport.info("⏳🚩⏳🚩⏳ No alcanza le tiempo " + time.getTime() + " tiene " + this.timeLeftOnDay + " del día " + wpsCurrentDate.getInstance().getCurrentDate());
        //wpsReport.info("⏳ ⏳ ⏳ Todavía tiene " + this.timeLeftOnDay + " en el día " + wpsCurrentDate.getInstance().getCurrentDate());
    }

    /**
     * Check if is a new Day
     *
     * @return true if is a new day
     */
    public boolean isNewDay() {
        return this.newDay;
    }

    /**
     * Set a new Day false
     *
     * @param newDay
     */
    public void setNewDay(boolean newDay) {
        this.newDay = newDay;
    }

    /**
     *
     */
    public void releaseWeekBlock() {
        this.weekBlock = false;
    }

    /**
     *
     */
    public void setWeekBlock() {
        this.weekBlock = true;
    }

    /**
     *
     * @return
     */
    public boolean getWeekBlock() {
        return this.weekBlock;
    }

    /**
     *
     * @return
     */
    public ResourceNeededType getCurrentResourceNeededType() {
        return currentResourceNeededType;
    }

    /**
     *
     *
     */
    public void setCurrentResourceNeededType(ResourceNeededType currentResourceNeededType) {
        this.currentResourceNeededType = currentResourceNeededType;
    }

    /**
     *
     * @return
     */
    public PeasantLeisureType getCurrentPeasantLeisureType() {
        return currentPeasantLeisureType;
    }

    /**
     *
     *
     * @param currentPeasantLeisureType
     */
    public void setCurrentPeasantLeisureType(PeasantLeisureType currentPeasantLeisureType) {
        this.currentPeasantLeisureType = currentPeasantLeisureType;
    }

    /**
     *
     *
     */
    public void setRandomCurrentPeasantLeisureType() {
        Random rand = new Random();

        switch (rand.nextInt(2)) {
            case 0:
                this.currentPeasantLeisureType = PeasantLeisureType.LEISURE;
                break;
            case 1:
                this.currentPeasantLeisureType = PeasantLeisureType.WASTERESOURCE;
                break;
            case 2:
                this.currentPeasantLeisureType = PeasantLeisureType.WASTERESOURCE;
                break;
        }
    }

    /**
     *
     * @return
     */
    public SeasonType getCurrentSeason() {
        return currentSeason;
    }

    /**
     *
     * @param currentSeason
     */
    public void setCurrentSeason(SeasonType currentSeason) {
        this.currentSeason = currentSeason;
    }

    /**
     *
     * @return
     */
    public CropCareType getCurrentCropCare() {
        return currentCropCare;
    }

    /**
     *
     * @param currentCropCare
     */
    public void setCurrentCropCare(CropCareType currentCropCare) {
        this.currentCropCare = currentCropCare;
    }

    /**
     *
     * @return
     */
    public MoneyOriginType getCurrentMoneyOrigin() {
        return currentMoneyOrigin;
    }

    /**
     *
     * @param currentMoneyOrigin
     */
    public void setCurrentMoneyOrigin(MoneyOriginType currentMoneyOrigin) {
        this.currentMoneyOrigin = currentMoneyOrigin;
    }

    public PeasantActivityType getCurrentActivity() {
        return this.currentPeasantActivityType;
    }

    public void setCurrentActivity(PeasantActivityType peasantActivityType) {
        this.currentPeasantActivityType = peasantActivityType;
    }

    /**
     *
     * @return
     */
    public EmotionalState getPeasantEmotionalState() {
        return peasantEmotionalState;
    }

    /**
     *
     * @return
     */
    public PeasantFamilyProfile getPeasantProfile() {
        return peasantProfile;
    }

    /**
     *
     * @param peasantProfile
     */
    private void setPeasantProfile(PeasantFamilyProfile peasantProfile) {
        this.peasantProfile = peasantProfile;
    }

    /**
     *
     * @param infoData
     * @return
     */
    @Override
    public boolean update(InfoData infoData) {
        return true;
    }

    /**
     *
     * @param priceList
     */
    public void setPriceList(Map<String, FarmingResource> priceList) {
        this.priceList = priceList;
    }

    /**
     *
     * @return
     */
    public Map<String, FarmingResource> getPriceList() {
        return priceList;
    }

    /**
     *
     * @return @throws CloneNotSupportedException
     */
    @Override
    public Believes clone() throws CloneNotSupportedException {
        return this;
    }

    /**
     *
     * @return
     */
    public boolean isFree() {
        return !this.busy;
    }

    /**
     *
     * @return
     */
    public boolean isBusy() {
        return this.busy;
    }

    /**
     *
     * @param busy
     */
    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    /**
     *
     * @return
     */
    public String toJson() {
        return "PeasantFamilyBDIAgentBelieves{" + "peasantProfile=" + peasantProfile.getPeasantFamilyAlias()
                + ", peasantEmotionalState=" + peasantEmotionalState + ", currentSeason="
                + currentSeason + ", currentCropCare=" + currentCropCare
                + ", roberyAccount=" + roberyAccount + ", ptwDate="+ ptwDate
                + ", currentMoneyOrigin=" + currentMoneyOrigin + ", currentPeasantActivityType="
                + currentPeasantActivityType + ", currentPeasantLeisureType=" + currentPeasantLeisureType
                + ", currentResourceNeededType=" + currentResourceNeededType + ", currentDay="
                + currentDay + ", timeLeftOnDay=" + timeLeftOnDay
                + ", newDay=" + newDay + ", weekBlock="
                + weekBlock + ", busy=" + busy
                + ", internalCurrentDate=" + internalCurrentDate + ", priceList=" + priceList
                + peasantProfile.toJson();
    }

    @Override
    public String toString() {
        return "\n"
                + " * ==========================================================================\n"
                + " * wpsPeasantFamilyProfile: " + peasantProfile.getPeasantFamilyAlias() + "\n"
                + " * ==========================================================================\n"
                + " * PeasantEmotionalState: " + peasantEmotionalState + "\n"
                + " * CurrentSeason: " + currentSeason + "\n"
                + " * CurrentCropCare: " + currentCropCare + "\n"
                + " * CurrentMoneyOrigin: " + currentMoneyOrigin + "\n"
                + " * PeasantActivityType: " + currentPeasantActivityType + "\n"
                + " * currentPeasantLeisureType: " + currentPeasantLeisureType + "\n"
                + " * roberyAccount: " + roberyAccount + "\n"
                + " * ptwDate: " + ptwDate + "\n"
                + " * CurrentDay: " + currentDay + "\n"
                + " * TimeLeftOnDay: " + timeLeftOnDay + "\n"
                + " * NewDay: " + newDay + "\n"
                + " * WeekBlock: " + weekBlock + "\n"
                + " * Busy: " + busy + "\n"
                + " * InternalCurrentDate: " + internalCurrentDate + "\n"
                + " * Price List: " + priceList + "\n"
                + " * ==========================================================================\n"
                + peasantProfile.toString();
    }

}
