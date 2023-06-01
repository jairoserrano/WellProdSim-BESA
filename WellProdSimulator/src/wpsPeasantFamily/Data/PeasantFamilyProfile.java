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
package wpsPeasantFamily.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import wpsControl.Agent.wpsCurrentDate;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class PeasantFamilyProfile implements Serializable {

    private String purpose;
    private String peasantFamilyAlias;
    private String internalCurrentDate;
    private double peasantFamilyMinimalVital;
    private int health;
    private double productivity;
    private double wellBeging;
    private boolean worker;
    private double peasantQualityFactor;
    private double liveStockAffinity;
    private boolean farm;
    private int cropSize;
    private double housing;
    private double servicesPresence;
    private double housingSize;
    private double housingCondition;
    private double housingLocation;
    private double farmDistance;
    private double money;
    private double totalIncome;
    private int loanAmountToPay;
    private double housingQuailty;
    private double timeSpentOnMaintenance;
    private boolean busy;
    private double cropHealth;
    private double farmReady;
    private double harvestedWeightExpected;
    private boolean processedCrop;
    private double cropEficiency;
    private double processedWeight;
    private double processingTime;
    private double trainingLevel;
    private double trainingAvailability;
    private double trainingRelevance;
    private double trainingCost;
    private double irrigation;
    private double irrigationTime;
    private double pestControl;
    private double diseasedCrop;
    private double weedControl;
    private double infestedCrop;
    private double suppliesAvailability;
    private double toolsAvailability;
    private boolean associated;
    private int neighbors;
    private double collaborationValue;
    private double healthProgramsAvailability;
    private boolean livestockFarming;
    private double livestockHealth;
    private int livestockNumber;
    private double familyTime;
    private double peasantFamilyAffinity;
    private double familyTimeAvailability;
    private double communications;
    private double socialCompatibility;
    private double restingTimeAvailibility;
    private double peasantRestAffinity;
    private double leisureOptions;
    private boolean sellDone;

    // Resources
    private Map<String, FarmingResource> priceList = new HashMap<>();
    private int waterAvailable;
    private int pesticidesAvailable;
    private int tools;
    private int supplies;
    private int riceSeedsByHectare;
    private int seeds;
    private int harvestedWeight;
    private String startRiceSeason;
    private String endRiceSeason;
    private String currentCropName;

    // Flags for Goals
    private boolean preparationSeason;
    private boolean plantingSeason;
    private boolean growingSeason;
    private boolean pesticideSeason;
    private boolean irrigateSeason;
    private boolean harverstSeason;
    private boolean formalLoanSeason;
    private boolean informalLoanSeason;
    private boolean cropCheckedToday;
    private boolean weekBlock;

    // Day Time
    private double timeLeftOnDay;
    private int currentDay;
    private boolean newDay;

    /**
     *
     */
    public PeasantFamilyProfile() {
        this.formalLoanSeason = false;
        this.timeLeftOnDay = 24;
        this.newDay = true;
        this.currentDay = 1;
        this.preparationSeason = false;
        this.irrigateSeason = false;
        this.weekBlock = false;
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
     * @return
     */
    public String getStartRiceSeason() {
        return startRiceSeason;
    }

    /**
     *
     * @param startRiceSeason
     */
    public void setStartRiceSeason(String startRiceSeason) {
        this.startRiceSeason = startRiceSeason;
    }

    /**
     *
     * @return
     */
    public String getEndRiceSeason() {
        return endRiceSeason;
    }

    /**
     *
     * @param endRiceSeason
     */
    public void setEndRiceSeason(String endRiceSeason) {
        this.endRiceSeason = endRiceSeason;
    }

    /**
     *
     * @return
     */
    public synchronized boolean getPreparationSeason() {
        return this.preparationSeason;
    }

    /**
     *
     * @param preparationSeason
     */
    public synchronized void setPreparationSeason(boolean preparationSeason) {
        this.preparationSeason = preparationSeason;
    }

    /**
     *
     * @return
     */
    public synchronized String getCurrentCropName() {
        return this.currentCropName;
    }

    /**
     *
     * @param currentCropName
     */
    public synchronized void setCurrentCropName(String currentCropName) {
        this.currentCropName = currentCropName;
    }

    /**
     *
     * @return
     */
    public synchronized int getRiceSeedsByHectare() {
        return riceSeedsByHectare;
    }

    /**
     *
     * @param riceSeedsByHectare
     */
    public synchronized void setRiceSeedsByHectare(int riceSeedsByHectare) {
        this.riceSeedsByHectare = riceSeedsByHectare;
    }

    /**
     *
     * @return
     */
    public synchronized int getPesticidesAvailable() {
        return pesticidesAvailable;
    }

    /**
     *
     * @param pesticidesAvailable
     */
    public synchronized void setPesticidesAvailable(int pesticidesAvailable) {
        this.pesticidesAvailable = pesticidesAvailable;
    }

    /**
     *
     */
    public synchronized void setCropCheckedToday() {
        this.cropCheckedToday = true;
    }

    /**
     *
     * @return
     */
    public synchronized boolean isCropCheckedToday() {
        return this.cropCheckedToday;
    }

    /**
     * Time unit defined by hours spent on activities.
     *
     * @param time
     */
    public synchronized void useTime(TimeConsumedBy time) {
        double timeLeft = this.timeLeftOnDay - time.getTime();
        this.timeLeftOnDay = timeLeft;
        if (timeLeft <= 0) {
            this.makeNewDay();
        } else {
            //wpsReport.info("⏳⏳ Le quedan " + timeLeft + " horas del día " + wpsCurrentDate.getInstance().getCurrentDate());
        }
        try {
            Thread.sleep(time.getTime() * 100);
        } catch (InterruptedException ex) {
            wpsReport.error(ex);
        }
    }

    /**
     *
     * @param time
     * @return
     */
    public synchronized boolean haveTimeAvailable(TimeConsumedBy time) {
        return this.timeLeftOnDay - time.getTime() >= 0;
        //wpsReport.info("⏳🚩⏳🚩⏳ No alcanza le tiempo " + time.getTime() + " tiene " + this.timeLeftOnDay + " del día " + wpsCurrentDate.getInstance().getCurrentDate());
        //wpsReport.info("⏳ ⏳ ⏳ Todavía tiene " + this.timeLeftOnDay + " en el día " + wpsCurrentDate.getInstance().getCurrentDate());
    }

    /**
     * Check if is a new Day
     *
     * @return true if is a new day
     */
    public synchronized boolean isNewDay() {
        return this.newDay;
    }

    /**
     * Set a new Day false
     */
    public synchronized void setNewDayFalse() {
        this.newDay = false;
    }

    /**
     * Make variable reset Every Day
     */
    public synchronized void makeNewDay() {
        this.currentDay++;
        this.timeLeftOnDay = 24;
        this.cropCheckedToday = false;
        this.newDay = true;
        this.internalCurrentDate = wpsCurrentDate.getInstance().getDatePlusOneDayAndUpdate();
    }

    /**
     *
     * @return
     */
    public synchronized boolean isFormalLoanNeeded() {
        return formalLoanSeason;
    }

    /**
     *
     * @param formalLoanSeason
     */
    public synchronized void setFormalLoanSeason(boolean formalLoanSeason) {
        this.formalLoanSeason = formalLoanSeason;
    }

    /**
     *
     * @return
     */
    public synchronized double getPeasantFamilyMinimalVital() {
        return peasantFamilyMinimalVital;
    }

    /**
     *
     * @param peasantFamilyMinimalVital
     */
    public synchronized void setPeasantFamilyMinimalVital(double peasantFamilyMinimalVital) {
        this.peasantFamilyMinimalVital = peasantFamilyMinimalVital;
    }

    /**
     *
     * @return
     */
    public synchronized boolean isSellDone() {
        return sellDone;
    }

    /**
     *
     */
    public synchronized void discountDailyMoney() {
        this.money = this.money - this.peasantFamilyMinimalVital;
    }

    /**
     *
     * @param sellDone
     */
    public synchronized void setSellDone(boolean sellDone) {
        this.sellDone = sellDone;
    }

    /**
     *
     * @return
     */
    public synchronized String getPurpose() {
        return purpose;
    }

    /**
     *
     * @param purpose
     */
    public synchronized void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     *
     * @return
     */
    public synchronized String getPeasantFamilyAlias() {
        return peasantFamilyAlias;
    }

    /**
     *
     * @return
     */
    public synchronized String getPeasantFamilyLandAlias() {
        return peasantFamilyAlias + "_land";
    }

    /**
     *
     * @param peasantFamilyAlias
     */
    public synchronized void setPeasantFamilyAlias(String peasantFamilyAlias) {
        this.peasantFamilyAlias = peasantFamilyAlias;
    }

    /**
     *
     * @return
     */
    public synchronized int getHealth() {
        return health;
    }

    /**
     *
     * @param health
     */
    public synchronized void setHealth(int health) {
        this.health = health;
    }

    /**
     *
     */
    public synchronized void increaseHealth() {
        if (this.health == 100) {
            // @TODO: Incrementar felicidad
            this.health = 100;
        } else if (this.health <= 0) {
            this.health = 0;
        } else {
            this.health = this.health + 5;
        }
    }

    /**
     *
     * @return
     */
    public synchronized double getProductivity() {
        return productivity;
    }

    /**
     *
     * @param productivity
     */
    public synchronized void setProductivity(double productivity) {
        this.productivity = productivity;
    }

    /**
     *
     * @return
     */
    public synchronized double getWellBeging() {
        return wellBeging;
    }

    /**
     *
     * @param wellBeging
     */
    public synchronized void setWellBeging(double wellBeging) {
        this.wellBeging = wellBeging;
    }

    /**
     *
     * @return
     */
    public synchronized boolean isAWorker() {
        return worker;
    }

    /**
     *
     * @param worker
     */
    public synchronized void setWorker(boolean worker) {
        this.worker = worker;
    }

    /**
     *
     * @return
     */
    public synchronized double getPeasantQualityFactor() {
        return peasantQualityFactor;
    }

    /**
     *
     * @param peasantQualityFactor
     */
    public synchronized void setPeasantQualityFactor(double peasantQualityFactor) {
        this.peasantQualityFactor = peasantQualityFactor;
    }

    /**
     *
     * @return
     */
    public synchronized int getTools() {
        return tools;
    }

    /**
     *
     * @param tools
     */
    public synchronized void setTools(int tools) {
        this.tools += tools;
    }

    /**
     *
     * @return
     */
    public synchronized int getSupplies() {
        return supplies;
    }

    /**
     *
     * @param supplies
     */
    public synchronized void setSupplies(int supplies) {
        this.supplies += supplies;
    }

    /**
     *
     * @return
     */
    public synchronized double getLiveStockAffinity() {
        return liveStockAffinity;
    }

    /**
     *
     * @param liveStockAffinity
     */
    public synchronized void setLiveStockAffinity(double liveStockAffinity) {
        this.liveStockAffinity = liveStockAffinity;
    }

    /**
     *
     * @return
     */
    public synchronized boolean haveAFarm() {
        return farm;
    }

    /**
     *
     * @param farm
     */
    public synchronized void setLand(boolean farm) {
        this.farm = farm;
    }

    /**
     *
     * @return
     */
    public synchronized int getCropSize() {
        return cropSize;
    }

    /**
     *
     * @param cropSize
     */
    public synchronized void setFarmSize(int cropSize) {
        this.cropSize = cropSize;
    }

    /**
     *
     * @return
     */
    public synchronized double getHousing() {
        return housing;
    }

    /**
     *
     * @param housing
     */
    public synchronized void setHousing(double housing) {
        this.housing = housing;
    }

    /**
     *
     * @return
     */
    public synchronized double getServicesPresence() {
        return servicesPresence;
    }

    /**
     *
     * @param servicesPresence
     */
    public synchronized void setServicesPresence(double servicesPresence) {
        this.servicesPresence = servicesPresence;
    }

    /**
     *
     * @return
     */
    public synchronized double getHousingSize() {
        return housingSize;
    }

    /**
     *
     * @param housingSize
     */
    public synchronized void setHousingSize(double housingSize) {
        this.housingSize = housingSize;
    }

    /**
     *
     * @return
     */
    public synchronized double getHousingCondition() {
        return housingCondition;
    }

    /**
     *
     * @param housingCondition
     */
    public synchronized void setHousingCondition(double housingCondition) {
        this.housingCondition = housingCondition;
    }

    /**
     *
     * @return
     */
    public synchronized double getHousingLocation() {
        return housingLocation;
    }

    /**
     *
     * @param housingLocation
     */
    public synchronized void setHousingLocation(double housingLocation) {
        this.housingLocation = housingLocation;
    }

    /**
     *
     * @return
     */
    public synchronized double getFarmDistance() {
        return farmDistance;
    }

    /**
     *
     * @param farmDistance
     */
    public synchronized void setFarmDistance(double farmDistance) {
        this.farmDistance = farmDistance;
    }

    /**
     *
     * @return
     */
    public synchronized double getMoney() {
        return money;
    }

    /**
     *
     * @param money
     */
    public synchronized void setMoney(Integer money) {
        this.money = money;
    }

    /**
     *
     * @param money
     */
    public synchronized void increaseMoney(Integer money) {
        this.money += money;
    }

    /**
     *
     * @return
     */
    public synchronized double getTotalIncome() {
        return totalIncome;
    }

    /**
     *
     * @param totalIncome
     */
    public synchronized void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    /**
     *
     * @return
     */
    public synchronized double getLoanAmountToPay() {
        return loanAmountToPay;
    }

    /**
     *
     * @param loanAmountToPay
     */
    public synchronized void setLoanAmountToPay(int loanAmountToPay) {
        this.loanAmountToPay = loanAmountToPay;
    }

    /**
     *
     * @return
     */
    public synchronized int getHarvestedWeight() {
        return harvestedWeight;
    }

    /**
     *
     * @param harvestedWeight
     */
    public synchronized void setHarvestedWeight(int harvestedWeight) {
        this.harvestedWeight = harvestedWeight;
    }

    /**
     *
     * @return
     */
    public synchronized boolean isHarverstSeason() {
        return harverstSeason;
    }

    /**
     *
     * @param harverstSeason
     */
    public synchronized void setHarverstSeason(boolean harverstSeason) {
        this.harverstSeason = harverstSeason;
    }

    /**
     *
     * @return
     */
    public synchronized double getHousingQuailty() {
        return housingQuailty;
    }

    /**
     *
     * @param housingQuailty
     */
    public synchronized void setHousingQuailty(double housingQuailty) {
        this.housingQuailty = housingQuailty;
    }

    /**
     *
     * @return
     */
    public synchronized double getTimeSpentOnMaintenance() {
        return timeSpentOnMaintenance;
    }

    /**
     *
     * @param timeSpentOnMaintenance
     */
    public synchronized void setTimeSpentOnMaintenance(double timeSpentOnMaintenance) {
        this.timeSpentOnMaintenance = timeSpentOnMaintenance;
    }

    /**
     *
     * @return
     */
    public synchronized int getSeeds() {
        return seeds;
    }

    /**
     *
     * @param seeds
     */
    public synchronized void setSeeds(int seeds) {
        this.seeds = seeds;
    }

    /**
     *
     * @return
     */
    public synchronized boolean isPlantingSeason() {
        return plantingSeason;
    }

    /**
     *
     * @param plantingSeason
     */
    public synchronized void setPlantingSeason(boolean plantingSeason) {
        this.plantingSeason = plantingSeason;
    }

    /**
     *
     * @return
     */
    public synchronized boolean isGrowingSeason() {
        return growingSeason;
    }

    /**
     *
     * @param growingSeason
     */
    public synchronized void setGrowingSeason(boolean growingSeason) {
        this.growingSeason = growingSeason;
    }

    /**
     *
     * @return
     */
    public synchronized boolean isBusy() {
        return busy;
    }

    /**
     *
     * @return
     */
    public synchronized boolean isFree() {
        return !busy;
    }

    /**
     *
     * @param busy
     */
    public synchronized void setBusy(boolean busy) {
        this.busy = busy;
    }

    /**
     *
     * @return
     */
    public synchronized double getCropHealth() {
        return cropHealth;
    }

    /**
     *
     * @param cropHealth
     */
    public synchronized void setCropHealth(double cropHealth) {
        this.cropHealth = cropHealth;
    }

    /**
     *
     * @return
     */
    public synchronized double getFarmReady() {
        return farmReady;
    }

    /**
     *
     * @param farmReady
     */
    public synchronized void setFarmReady(double farmReady) {
        this.farmReady = farmReady;
    }

    /**
     *
     * @return
     */
    public synchronized double getHarvestedWeightExpected() {
        return harvestedWeightExpected;
    }

    /**
     *
     * @param harvestedWeightExpected
     */
    public synchronized void setHarvestedWeightExpected(double harvestedWeightExpected) {
        this.harvestedWeightExpected = harvestedWeightExpected;
    }

    /**
     *
     * @return
     */
    public synchronized boolean isProcessedCrop() {
        return processedCrop;
    }

    /**
     *
     * @param processedCrop
     */
    public synchronized void setProcessedCrop(boolean processedCrop) {
        this.processedCrop = processedCrop;
    }

    /**
     *
     * @return
     */
    public synchronized double getCropEficiency() {
        return cropEficiency;
    }

    /**
     *
     * @param cropEficiency
     */
    public synchronized void setCropEficiency(double cropEficiency) {
        this.cropEficiency = cropEficiency;
    }

    /**
     *
     * @return
     */
    public synchronized double getProcessedWeight() {
        return processedWeight;
    }

    /**
     *
     * @param processedWeight
     */
    public synchronized void setProcessedWeight(double processedWeight) {
        this.processedWeight = processedWeight;
    }

    /**
     *
     * @return
     */
    public synchronized double getProcessingTime() {
        return processingTime;
    }

    /**
     *
     * @param processingTime
     */
    public synchronized void setProcessingTime(double processingTime) {
        this.processingTime = processingTime;
    }

    /**
     *
     * @return
     */
    public synchronized double getTrainingLevel() {
        return trainingLevel;
    }

    /**
     *
     * @param trainingLevel
     */
    public synchronized void setTrainingLevel(double trainingLevel) {
        this.trainingLevel = trainingLevel;
    }

    /**
     *
     * @return
     */
    public synchronized double getTrainingAvailability() {
        return trainingAvailability;
    }

    /**
     *
     * @param trainingAvailability
     */
    public synchronized void setTrainingAvailability(double trainingAvailability) {
        this.trainingAvailability = trainingAvailability;
    }

    /**
     *
     * @return
     */
    public synchronized double getTrainingRelevance() {
        return trainingRelevance;
    }

    /**
     *
     * @param trainingRelevance
     */
    public synchronized void setTrainingRelevance(double trainingRelevance) {
        this.trainingRelevance = trainingRelevance;
    }

    /**
     *
     * @return
     */
    public synchronized double getTrainingCost() {
        return trainingCost;
    }

    /**
     *
     * @param trainingCost
     */
    public synchronized void setTrainingCost(double trainingCost) {
        this.trainingCost = trainingCost;
    }

    /**
     *
     * @return
     */
    public synchronized double getIrrigation() {
        return irrigation;
    }

    /**
     *
     * @param irrigation
     */
    public synchronized void setIrrigation(double irrigation) {
        this.irrigation = irrigation;
    }

    /**
     *
     * @return
     */
    public synchronized double getWaterAvailable() {
        return waterAvailable;
    }

    /**
     *
     * @param waterAvailable
     */
    public synchronized void setWaterAvailable(double waterAvailable) {
        this.waterAvailable += waterAvailable;
    }

    /**
     *
     * @return
     */
    public synchronized double getIrrigationTime() {
        return irrigationTime;
    }

    /**
     *
     * @param irrigationTime
     */
    public synchronized void setIrrigationTime(double irrigationTime) {
        this.irrigationTime = irrigationTime;
    }

    /**
     *
     * @return
     */
    public synchronized double getPestControl() {
        return pestControl;
    }

    /**
     *
     * @param pestControl
     */
    public synchronized void setPestControl(double pestControl) {
        this.pestControl = pestControl;
    }

    /**
     *
     * @return
     */
    public synchronized double getDiseasedCrop() {
        return diseasedCrop;
    }

    /**
     *
     * @param diseasedCrop
     */
    public synchronized void setDiseasedCrop(double diseasedCrop) {
        this.diseasedCrop = diseasedCrop;
    }

    /**
     *
     * @return
     */
    public synchronized double getWeedControl() {
        return weedControl;
    }

    /**
     *
     * @param weedControl
     */
    public synchronized void setWeedControl(double weedControl) {
        this.weedControl = weedControl;
    }

    /**
     *
     * @return
     */
    public synchronized double getInfestedCrop() {
        return infestedCrop;
    }

    /**
     *
     * @param infestedCrop
     */
    public synchronized void setInfestedCrop(double infestedCrop) {
        this.infestedCrop = infestedCrop;
    }

    /**
     *
     * @return
     */
    public synchronized double getSuppliesAvailability() {
        return suppliesAvailability;
    }

    /**
     *
     * @param suppliesAvailability
     */
    public synchronized void setSuppliesAvailability(double suppliesAvailability) {
        this.suppliesAvailability = suppliesAvailability;
    }

    /**
     *
     * @return
     */
    public synchronized double getToolsAvailability() {
        return toolsAvailability;
    }

    /**
     *
     * @param toolsAvailability
     */
    public synchronized void setToolsAvailability(double toolsAvailability) {
        this.toolsAvailability = toolsAvailability;
    }

    /**
     *
     * @return
     */
    public synchronized boolean isAssociated() {
        return associated;
    }

    /**
     *
     * @param associated
     */
    public synchronized void setAssociated(boolean associated) {
        this.associated = associated;
    }

    /**
     *
     * @return
     */
    public synchronized int getNeighbors() {
        return neighbors;
    }

    /**
     *
     * @param neighbors
     */
    public synchronized void setNeighbors(int neighbors) {
        this.neighbors = neighbors;
    }

    /**
     *
     * @return
     */
    public synchronized double getCollaborationValue() {
        return collaborationValue;
    }

    /**
     *
     * @param collaborationValue
     */
    public synchronized void setCollaborationValue(double collaborationValue) {
        this.collaborationValue = collaborationValue;
    }

    /**
     *
     * @return
     */
    public synchronized double getHealthProgramsAvailability() {
        return healthProgramsAvailability;
    }

    /**
     *
     * @param healthProgramsAvailability
     */
    public synchronized void setHealthProgramsAvailability(double healthProgramsAvailability) {
        this.healthProgramsAvailability = healthProgramsAvailability;
    }

    /**
     *
     * @return
     */
    public synchronized boolean isLivestockFarming() {
        return livestockFarming;
    }

    /**
     *
     * @param livestockFarming
     */
    public synchronized void setLivestockFarming(boolean livestockFarming) {
        this.livestockFarming = livestockFarming;
    }

    /**
     *
     * @return
     */
    public synchronized double getLivestockHealth() {
        return livestockHealth;
    }

    /**
     *
     * @param livestockHealth
     */
    public synchronized void setLivestockHealth(double livestockHealth) {
        this.livestockHealth = livestockHealth;
    }

    /**
     *
     * @return
     */
    public synchronized int getLivestockNumber() {
        return livestockNumber;
    }

    /**
     *
     * @param livestockNumber
     */
    public synchronized void setLivestockNumber(int livestockNumber) {
        this.livestockNumber += livestockNumber;
    }

    /**
     *
     * @return
     */
    public synchronized double getFamilyTime() {
        return familyTime;
    }

    /**
     *
     * @param familyTime
     */
    public synchronized void setFamilyTime(double familyTime) {
        this.familyTime = familyTime;
    }

    /**
     *
     * @return
     */
    public synchronized double getPeasantFamilyAffinity() {
        return peasantFamilyAffinity;
    }

    /**
     *
     * @param peasantFamilyAffinity
     */
    public synchronized void setPeasantFamilyAffinity(double peasantFamilyAffinity) {
        this.peasantFamilyAffinity = peasantFamilyAffinity;
    }

    /**
     *
     * @return
     */
    public synchronized double getFamilyTimeAvailability() {
        return familyTimeAvailability;
    }

    /**
     *
     * @param familyTimeAvailability
     */
    public synchronized void setFamilyTimeAvailability(double familyTimeAvailability) {
        this.familyTimeAvailability = familyTimeAvailability;
    }

    /**
     *
     */
    public synchronized void increaseFamilyTimeAvailability() {
        wpsReport.info("");
        /*if (this.familyTimeAvailability == 1) {
            this.familyTimeAvailability = 1;
        } else {
            this.familyTimeAvailability = this.familyTimeAvailability + 0.1;
        }*/
        this.familyTimeAvailability = 1;
    }

    /**
     *
     */
    public synchronized void useFamilyTimeAvailability() {
        //wpsReport.info("");
        /*if (this.familyTimeAvailability > 0) {
            this.familyTimeAvailability = this.familyTimeAvailability - 0.1;
        } else {
            this.familyTimeAvailability = 0;
        }*/
        this.familyTimeAvailability = 0;
    }

    /**
     *
     * @return
     */
    public synchronized double getCommunications() {
        return communications;
    }

    /**
     *
     * @param communications
     */
    public synchronized void setCommunications(double communications) {
        this.communications = communications;
    }

    /**
     *
     * @return
     */
    public synchronized double getSocialCompatibility() {
        return socialCompatibility;
    }

    /**
     *
     * @param socialCompatibility
     */
    public synchronized void setSocialCompatibility(double socialCompatibility) {
        this.socialCompatibility = socialCompatibility;
    }

    /**
     *
     * @return
     */
    public synchronized double getRestingTimeAvailibility() {
        return restingTimeAvailibility;
    }

    /**
     *
     * @param restingTimeAvailibility
     */
    public synchronized void setRestingTimeAvailibility(double restingTimeAvailibility) {
        this.restingTimeAvailibility = restingTimeAvailibility;
    }

    /**
     *
     * @return
     */
    public synchronized double getPeasantRestAffinity() {
        return peasantRestAffinity;
    }

    /**
     *
     * @param peasantRestAffinity
     */
    public synchronized void setPeasantRestAffinity(double peasantRestAffinity) {
        this.peasantRestAffinity = peasantRestAffinity;
    }

    /**
     *
     * @return
     */
    public synchronized double getLeisureOptions() {
        return leisureOptions;
    }

    /**
     *
     * @param leisureOptions
     */
    public synchronized void setLeisureOptions(double leisureOptions) {
        this.leisureOptions = leisureOptions;
    }

    /**
     *
     */
    public synchronized void useLeisureOptions() {
        /*wpsReport.info("");
        if (this.leisureOptions > 0) {
            this.leisureOptions = this.leisureOptions - 0.1;
        }*/
        this.leisureOptions = 0;
    }

    /**
     *
     */
    public synchronized void increaseLeisureOptions() {
        wpsReport.info("");
        /*if (this.leisureOptions >= 1) {
            this.leisureOptions = 1;
        } else {
            this.leisureOptions = this.leisureOptions + 0.1;
        }*/
        this.leisureOptions = 1;
    }

    /**
     *
     */
    public synchronized void reduceHouseCondition() {
        //wpsReport.info("");
        /*if (this.housingCondition > 0) {
            this.housingCondition = this.housingCondition - 0.1;
        }*/
        this.housingCondition = 0;
    }

    /**
     *
     */
    public synchronized void increaseHouseCondition() {
        //wpsReport.info("");
        /*if (this.housingCondition >= 1) {
            this.housingCondition = 1;
        } else {
            this.housingCondition = this.housingCondition + 0.1;
        }*/
        this.housingCondition = 1;
    }

    /**
     *
     */
    public synchronized void increaseTools() {
        //wpsReport.info("");
        /*if (this.tools >= 1) {
            this.tools = 1;
        } else {
            this.tools = this.housingCondition + 0.1;
        }*/
        this.tools = 1;
    }

    /**
     *
     */
    public synchronized void useTools() {
        /*if (this.tools > 0) {
            this.tools = this.tools - 0.1;
        }*/
        this.tools = 0;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purpose != null ? purpose.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public synchronized boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeasantFamilyProfile)) {
            return false;
        }
        PeasantFamilyProfile other = (PeasantFamilyProfile) object;
        if ((this.purpose == null && other.purpose != null) || (this.purpose != null && !this.purpose.equals(other.purpose))) {
            return false;
        }
        return true;
    }

    /**
     *
     */
    public synchronized void increaseFarmReady() {
        /*
        if (this.farmReady == 1) {
            this.farmReady = 1;
        } else {
            this.farmReady = this.farmReady + 0.1;
        }*/
        this.farmReady = 1;
    }

    /**
     *
     */
    public synchronized void increaseTrainingLevel() {
        this.trainingLevel = 1;
    }

    /**
     *
     * @param internalCurrentDate
     */
    public synchronized void setInternalCurrentDate(String internalCurrentDate) {
        this.internalCurrentDate = internalCurrentDate;
    }

    /**
     *
     * @return
     */
    public synchronized String getInternalCurrentDate() {
        return this.internalCurrentDate;
    }

    /**
     *
     * @param loan
     */
    public synchronized void setInformalLoanSeason(boolean loan) {
        this.informalLoanSeason = loan;
    }

    /**
     *
     * @return
     */
    public synchronized boolean isInformalLoanNeeded() {
        return this.informalLoanSeason;
    }

    /**
     *
     * @param pesticidesAvailable
     */
    public synchronized void setPesticidesAvailable(Integer pesticidesAvailable) {
        this.pesticidesAvailable += pesticidesAvailable;
    }

    /**
     *
     * @param priceList
     */
    public synchronized void setPriceList(Map<String, FarmingResource> priceList) {
        this.priceList = priceList;
    }

    /**
     *
     * @return
     */
    public synchronized Map<String, FarmingResource> getPriceList() {
        return priceList;
    }

    /**
     *
     * @return
     */
    public synchronized boolean needAPriceList() {
        // @TODO: Falta saber si tiene la lista de precios actualizada
        return this.priceList.isEmpty();
    }

    /**
     *
     * @param discount
     */
    public synchronized void useMoney(int discount) {
        this.money -= discount;
    }

    /**
     *
     * @param seeds
     */
    public synchronized void useSeeds(int seeds) {
        this.seeds -= seeds;
    }

    /**
     *
     * @return
     */
    public synchronized boolean isPreparationSeason() {
        return this.preparationSeason;
    }

    /**
     *
     * @return
     */
    public synchronized boolean isPesticideSeason() {
        return this.pesticideSeason;
    }

    /**
     *
     * @param pesticideSeason
     */
    public synchronized void setPesticideSeason(boolean pesticideSeason) {
        this.pesticideSeason = pesticideSeason;
    }

    /**
     *
     * @param irrigateSeason
     */
    public void setIrrigateSeason(boolean irrigateSeason) {
        this.irrigateSeason = irrigateSeason;
    }

    /**
     *
     * @return
     */
    public boolean isIrrigateSeason() {
        return this.irrigateSeason;
    }

    /**
     *
     * @param water
     */
    public void useWater(int water) {
        this.waterAvailable -= water;
    }

    /**
     *
     * @return
     */
    public boolean isWaterNeeded() {
        return this.waterAvailable == 0;
    }

    /**
     *
     */
    public void decreaseHealth() {
        this.health -= 5;
    }

    /**
     *
     * @return
     */
    public String toJson() {
        return "PeasantFamilyProfile{" + "purpose=" + purpose + ", peasantFamilyAlias=" + peasantFamilyAlias + ", internalCurrentDate=" + internalCurrentDate + ", peasantFamilyMinimalVital=" + peasantFamilyMinimalVital + ", health=" + health + ", productivity=" + productivity + ", wellBeging=" + wellBeging + ", worker=" + worker + ", peasantQualityFactor=" + peasantQualityFactor + ", liveStockAffinity=" + liveStockAffinity + ", farm=" + farm + ", cropSize=" + cropSize + ", housing=" + housing + ", servicesPresence=" + servicesPresence + ", housingSize=" + housingSize + ", housingCondition=" + housingCondition + ", housingLocation=" + housingLocation + ", farmDistance=" + farmDistance + ", money=" + money + ", totalIncome=" + totalIncome + ", loanAmountToPay=" + loanAmountToPay + ", housingQuailty=" + housingQuailty + ", timeSpentOnMaintenance=" + timeSpentOnMaintenance + ", busy=" + busy + ", cropHealth=" + cropHealth + ", farmReady=" + farmReady + ", harvestedWeightExpected=" + harvestedWeightExpected + ", processedCrop=" + processedCrop + ", cropEficiency=" + cropEficiency + ", processedWeight=" + processedWeight + ", processingTime=" + processingTime + ", trainingLevel=" + trainingLevel + ", trainingAvailability=" + trainingAvailability + ", trainingRelevance=" + trainingRelevance + ", trainingCost=" + trainingCost + ", irrigation=" + irrigation + ", irrigationTime=" + irrigationTime + ", pestControl=" + pestControl + ", diseasedCrop=" + diseasedCrop + ", weedControl=" + weedControl + ", infestedCrop=" + infestedCrop + ", suppliesAvailability=" + suppliesAvailability + ", toolsAvailability=" + toolsAvailability + ", associated=" + associated + ", neighbors=" + neighbors + ", collaborationValue=" + collaborationValue + ", healthProgramsAvailability=" + healthProgramsAvailability + ", livestockFarming=" + livestockFarming + ", livestockHealth=" + livestockHealth + ", livestockNumber=" + livestockNumber + ", familyTime=" + familyTime + ", peasantFamilyAffinity=" + peasantFamilyAffinity + ", familyTimeAvailability=" + familyTimeAvailability + ", communications=" + communications + ", socialCompatibility=" + socialCompatibility + ", restingTimeAvailibility=" + restingTimeAvailibility + ", peasantRestAffinity=" + peasantRestAffinity + ", leisureOptions=" + leisureOptions + ", sellDone=" + sellDone + ", priceList=" + priceList + ", waterAvailable=" + waterAvailable + ", pesticidesAvailable=" + pesticidesAvailable + ", tools=" + tools + ", supplies=" + supplies + ", riceSeedsByHectare=" + riceSeedsByHectare + ", seeds=" + seeds + ", harvestedWeight=" + harvestedWeight + ", startRiceSeason=" + startRiceSeason + ", endRiceSeason=" + endRiceSeason + ", currentCropName=" + currentCropName + ", preparationSeason=" + preparationSeason + ", plantingSeason=" + plantingSeason + ", growingSeason=" + growingSeason + ", pesticideSeason=" + pesticideSeason + ", irrigateSeason=" + irrigateSeason + ", harverstSeason=" + harverstSeason + ", formalLoanSeason=" + formalLoanSeason + ", informalLoanSeason=" + informalLoanSeason + ", cropCheckedToday=" + cropCheckedToday + ", timeLeftOnDay=" + timeLeftOnDay + ", currentDay=" + currentDay + ", newDay=" + newDay + '}';
    }

    /**
     *
     * @return
     */
    @Override
    public synchronized String toString() {
        //return "PeasantFamilyProfile{" + "purpose=" + purpose + ", peasantFamilyAlias=" + peasantFamilyAlias + ", agentID=" + agentID + ", internalCurrentDate=" + internalCurrentDate + ", peasantFamilyMinimalVital=" + peasantFamilyMinimalVital + ", health=" + health + ", productivity=" + productivity + ", wellBeging=" + wellBeging + ", worker=" + worker + ", peasantQualityFactor=" + peasantQualityFactor + ", liveStockAffinity=" + liveStockAffinity + ", farm=" + farm + ", farmName=" + farmName + ", cropSize=" + cropSize + ", housing=" + housing + ", servicesPresence=" + servicesPresence + ", housingSize=" + housingSize + ", housingCondition=" + housingCondition + ", housingLocation=" + housingLocation + ", farmDistance=" + farmDistance + ", money=" + money + ", totalIncome=" + totalIncome + ", loanAmountToPay=" + loanAmountToPay + ", harvestedWeight=" + harvestedWeight + ", housingQuailty=" + housingQuailty + ", timeSpentOnMaintenance=" + timeSpentOnMaintenance + ", busy=" + busy + ", cropHealth=" + cropHealth + ", farmReady=" + farmReady + ", harvestedWeightExpected=" + harvestedWeightExpected + ", processedCrop=" + processedCrop + ", cropEficiency=" + cropEficiency + ", processedWeight=" + processedWeight + ", processingTime=" + processingTime + ", trainingLevel=" + trainingLevel + ", trainingAvailability=" + trainingAvailability + ", trainingRelevance=" + trainingRelevance + ", trainingCost=" + trainingCost + ", irrigation=" + irrigation + ", irrigationTime=" + irrigationTime + ", pestControl=" + pestControl + ", diseasedCrop=" + diseasedCrop + ", weedControl=" + weedControl + ", infestedCrop=" + infestedCrop + ", suppliesAvailability=" + suppliesAvailability + ", toolsAvailability=" + toolsAvailability + ", associated=" + associated + ", neighbors=" + neighbors + ", collaborationValue=" + collaborationValue + ", healthProgramsAvailability=" + healthProgramsAvailability + ", livestockFarming=" + livestockFarming + ", livestockHealth=" + livestockHealth + ", livestockNumber=" + livestockNumber + ", familyTime=" + familyTime + ", peasantFamilyAffinity=" + peasantFamilyAffinity + ", familyTimeAvailability=" + familyTimeAvailability + ", communications=" + communications + ", socialCompatibility=" + socialCompatibility + ", restingTimeAvailibility=" + restingTimeAvailibility + ", peasantRestAffinity=" + peasantRestAffinity + ", leisureOptions=" + leisureOptions + ", sellDone=" + sellDone + ", priceList=" + priceList + ", waterAvailable=" + waterAvailable + ", pesticidesAvailable=" + pesticidesAvailable + ", tools=" + tools + ", supplies=" + supplies + ", riceSeedsByHectare=" + riceSeedsByHectare + ", seeds=" + seeds + ", startRiceSeason=" + startRiceSeason + ", endRiceSeason=" + endRiceSeason + ", currentCropName=" + currentCropName + ", preparationSeason=" + preparationSeason + ", plantingSeason=" + plantingSeason + ", growingSeason=" + growingSeason + ", pesticideSeason=" + pesticideSeason + ", irrigateSeason=" + irrigateSeason + ", harverstSeason=" + harverstSeason + ", formalLoanSeason=" + formalLoanSeason + ", informalLoanSeason=" + informalLoanSeason + ", cropCheckedToday=" + cropCheckedToday + ", timeLeftOnDay=" + timeLeftOnDay + ", currentDay=" + currentDay + ", newDay=" + newDay + '}';
        return this.peasantFamilyAlias
                + "\nPeasant Current Date = " + internalCurrentDate + "\nhealth=" + health + ", farm=" + farm + ", money=" + money + ", loanAmountToPay=" + loanAmountToPay
                + ",\nHarvestedWeight = " + harvestedWeight + ", cropHealth=" + cropHealth + ", processedWeight=" + processedWeight + ", diseasedCrop=" + diseasedCrop + ", weedControl=" + weedControl + ", infestedCrop=" + infestedCrop + ", sellDone=" + sellDone
                //+ ",\n priceList=" + priceList + ",\n waterAvailable=" + waterAvailable + ", pesticidesAvailable=" + pesticidesAvailable + ", tools=" + tools + ", supplies=" + supplies +", seeds=" + seeds + ", startRiceSeason=" + startRiceSeason 
                + ",\nformalLoanSeason=" + formalLoanSeason + ", informalLoanSeason=" + informalLoanSeason + ", cropCheckedToday=" + cropCheckedToday + ", timeLeftOnDay=" + timeLeftOnDay + ", currentDay=" + currentDay + ", newDay=" + newDay
                + ",\nwaterAvailable=" + waterAvailable + ", pesticidesAvailable=" + pesticidesAvailable + ", tools=" + tools + ", supplies=" + supplies + ", seeds=" + seeds + ", startRiceSeason=" + startRiceSeason
                + ",\ncurrentCropName   = " + currentCropName + "\npreparationSeason = " + preparationSeason + "\nplantingSeason    = " + plantingSeason + "\ngrowingSeason     = " + growingSeason + "\npesticideSeason   = " + pesticideSeason + "\nirrigateSeason    = " + irrigateSeason + " \nharverstSeason    = " + harverstSeason
                + "\n------------------------------------------------------------------------------------------------ \n";
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

}
