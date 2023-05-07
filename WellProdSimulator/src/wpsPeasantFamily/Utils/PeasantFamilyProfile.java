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
package wpsPeasantFamily.Utils;

import BESA.Log.ReportBESA;
import java.io.Serializable;
import wpsControl.Agent.wpsCurrentDate;

/**
 *
 * @author jairo
 */
public class PeasantFamilyProfile implements Serializable {

    private String purpose;
    private String profileName;
    private String agentID;
    private String internalCurrentDate;
    private double peasantFamilyMinimalVital;
    private double health;
    private double productivity;
    private double wellBeging;
    private boolean worker;
    private double peasantQualityFactor;
    private double tools;
    private double supplies;
    private double liveStockAffinity;
    private boolean farm;
    private String farmName;
    private double farmSize;
    private double housing;
    private double servicesPresence;
    private double housingSize;
    private double housingCondition;
    private double housingLocation;
    private double farmDistance;
    private double money;
    private double totalIncome;
    private double debtPayment;
    private double harvestedWeight;
    private double housingQuailty;
    private double timeSpentOnMaintenance;
    private double seeds;
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
    private double waterAvailable;
    private double irrigationTime;
    private double pestControl;
    private double diseasedCrop;
    private double weedControl;
    private double infestedCrop;
    private double suppliesAvailability;
    private double suppliesCost;
    private double toolsAvailability;
    private double toolsCost;
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

    // Flags for Goals
    private boolean plantingSeason;
    private boolean growingSeason;
    private boolean harverstSeason;
    private boolean loanSeason;

    // Day Time
    private double timeLeftOnDay;
    private int currentDay;
    private boolean newDay;

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
            ReportBESA.info("⏳⏳ Le quedan " + timeLeft + " horas del día " + wpsCurrentDate.getInstance().getCurrentDate());
        }
    }

    /**
     *
     * @param time
     * @return
     */
    public synchronized boolean haveTimeAvailable(TimeConsumedBy time) {
        if (this.timeLeftOnDay - time.getTime() < 0) {
            ReportBESA.info("⏳🚩⏳🚩⏳ No alcanza le tiempo " + time.getTime() + " tiene "+ this.timeLeftOnDay + " del día " + wpsCurrentDate.getInstance().getCurrentDate());
            return false;
        } else {
            ReportBESA.info("⏳ ⏳ ⏳ Todavía tiene " + this.timeLeftOnDay + " en el día " + wpsCurrentDate.getInstance().getCurrentDate());
            return true;
        }
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
     *
     */
    public synchronized void makeNewDay() {
        this.currentDay++;
        this.timeLeftOnDay = 24;
        this.newDay = true;
        ReportBESA.info(
                "\n\n🔆 New Day # "
                + this.currentDay + " - 🔆 "
                + wpsCurrentDate.getInstance().getDatePlusOneDayAndUpdate()
                + "\n");
    }

    /**
     *
     */
    public PeasantFamilyProfile() {
        this.loanSeason = false;
        this.timeLeftOnDay = 24;
        this.newDay = true;
        this.currentDay = 1;
    }

    public boolean isLoanNeeded() {
        return loanSeason;
    }

    public void setLoanSeason(boolean loanSeason) {
        this.loanSeason = loanSeason;
    }

    public double getPeasantFamilyMinimalVital() {
        return peasantFamilyMinimalVital;
    }

    public void setPeasantFamilyMinimalVital(double peasantFamilyMinimalVital) {
        this.peasantFamilyMinimalVital = peasantFamilyMinimalVital;
    }

    /**
     *
     * @return
     */
    public boolean isSellDone() {
        return sellDone;
    }

    public void discountDailyMoney() {
        this.money = this.money - this.peasantFamilyMinimalVital;
    }

    /**
     *
     * @param sellDone
     */
    public void setSellDone(boolean sellDone) {
        this.sellDone = sellDone;
    }

    /**
     *
     * @return
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     *
     * @param purpose
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     *
     * @return
     */
    public String getProfileName() {
        return profileName;
    }

    /**
     *
     * @param profileName
     */
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    /**
     *
     * @return
     */
    public double getHealth() {
        return health;
    }

    /**
     *
     * @param health
     */
    public void setHealth(double health) {
        this.health = health;
    }

    /**
     *
     */
    public void increaseHealth() {
        if (this.health == 1) {
            this.health = 1;
        } else if (this.health <= 0) {
            this.health = 0;
        } else {
            this.health = this.health + 0.1;
        }
    }

    /**
     *
     * @return
     */
    public double getProductivity() {
        return productivity;
    }

    /**
     *
     * @param productivity
     */
    public void setProductivity(double productivity) {
        this.productivity = productivity;
    }

    /**
     *
     * @return
     */
    public double getWellBeging() {
        return wellBeging;
    }

    /**
     *
     * @param wellBeging
     */
    public void setWellBeging(double wellBeging) {
        this.wellBeging = wellBeging;
    }

    /**
     *
     * @return
     */
    public boolean isAWorker() {
        return worker;
    }

    /**
     *
     * @param worker
     */
    public void setWorker(boolean worker) {
        this.worker = worker;
    }

    /**
     *
     * @return
     */
    public double getPeasantQualityFactor() {
        return peasantQualityFactor;
    }

    /**
     *
     * @param peasantQualityFactor
     */
    public void setPeasantQualityFactor(double peasantQualityFactor) {
        this.peasantQualityFactor = peasantQualityFactor;
    }

    /**
     *
     * @return
     */
    public double getTools() {
        return tools;
    }

    /**
     *
     * @param tools
     */
    public void setTools(double tools) {
        this.tools = tools;
    }

    /**
     *
     * @return
     */
    public double getSupplies() {
        return supplies;
    }

    /**
     *
     * @param supplies
     */
    public void setSupplies(double supplies) {
        this.supplies = supplies;
    }

    /**
     *
     * @return
     */
    public double getLiveStockAffinity() {
        return liveStockAffinity;
    }

    /**
     *
     * @param liveStockAffinity
     */
    public void setLiveStockAffinity(double liveStockAffinity) {
        this.liveStockAffinity = liveStockAffinity;
    }

    /**
     *
     * @return
     */
    public boolean haveAFarm() {
        return farm;
    }

    /**
     *
     * @param farm
     */
    public void setFarm(boolean farm) {
        this.farm = farm;
    }

    /**
     *
     * @return
     */
    public String getFarmName() {
        return farmName;
    }

    /**
     *
     * @param farmName
     */
    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    /**
     *
     * @return
     */
    public double getFarmSize() {
        return farmSize;
    }

    /**
     *
     * @param farmSize
     */
    public void setFarmSize(double farmSize) {
        this.farmSize = farmSize;
    }

    /**
     *
     * @return
     */
    public double getHousing() {
        return housing;
    }

    /**
     *
     * @param housing
     */
    public void setHousing(double housing) {
        this.housing = housing;
    }

    /**
     *
     * @return
     */
    public double getServicesPresence() {
        return servicesPresence;
    }

    /**
     *
     * @param servicesPresence
     */
    public void setServicesPresence(double servicesPresence) {
        this.servicesPresence = servicesPresence;
    }

    /**
     *
     * @return
     */
    public double getHousingSize() {
        return housingSize;
    }

    /**
     *
     * @param housingSize
     */
    public void setHousingSize(double housingSize) {
        this.housingSize = housingSize;
    }

    /**
     *
     * @return
     */
    public double getHousingCondition() {
        return housingCondition;
    }

    /**
     *
     * @param housingCondition
     */
    public void setHousingCondition(double housingCondition) {
        this.housingCondition = housingCondition;
    }

    /**
     *
     * @return
     */
    public double getHousingLocation() {
        return housingLocation;
    }

    /**
     *
     * @param housingLocation
     */
    public void setHousingLocation(double housingLocation) {
        this.housingLocation = housingLocation;
    }

    /**
     *
     * @return
     */
    public double getFarmDistance() {
        return farmDistance;
    }

    /**
     *
     * @param farmDistance
     */
    public void setFarmDistance(double farmDistance) {
        this.farmDistance = farmDistance;
    }

    /**
     *
     * @return
     */
    public double getMoney() {
        return money;
    }

    /**
     *
     * @param money
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     *
     * @param money
     */
    public void increaseMoney(double money) {
        this.money += money;
    }

    /**
     *
     * @return
     */
    public double getTotalIncome() {
        return totalIncome;
    }

    /**
     *
     * @param totalIncome
     */
    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    /**
     *
     * @return
     */
    public double getDebtPayment() {
        return debtPayment;
    }

    /**
     *
     * @param debtPayment
     */
    public void setDebtPayment(double debtPayment) {
        this.debtPayment = debtPayment;
    }

    /**
     *
     * @return
     */
    public double getHarvestedWeight() {
        return harvestedWeight;
    }

    /**
     *
     * @param harvestedWeight
     */
    public void setHarvestedWeight(double harvestedWeight) {
        this.harvestedWeight = harvestedWeight;
    }

    /**
     *
     * @return
     */
    public boolean isHarverstSeason() {
        return harverstSeason;
    }

    /**
     *
     * @param harverstSeason
     */
    public void setHarverstSeason(boolean harverstSeason) {
        this.harverstSeason = harverstSeason;
    }

    /**
     *
     * @return
     */
    public double getHousingQuailty() {
        return housingQuailty;
    }

    /**
     *
     * @param housingQuailty
     */
    public void setHousingQuailty(double housingQuailty) {
        this.housingQuailty = housingQuailty;
    }

    /**
     *
     * @return
     */
    public double getTimeSpentOnMaintenance() {
        return timeSpentOnMaintenance;
    }

    /**
     *
     * @param timeSpentOnMaintenance
     */
    public void setTimeSpentOnMaintenance(double timeSpentOnMaintenance) {
        this.timeSpentOnMaintenance = timeSpentOnMaintenance;
    }

    /**
     *
     * @return
     */
    public double getSeeds() {
        return seeds;
    }

    /**
     *
     * @param seeds
     */
    public void setSeeds(double seeds) {
        this.seeds = seeds;
    }

    /**
     *
     * @return
     */
    public boolean isPlantingSeason() {
        return plantingSeason;
    }

    /**
     *
     * @param plantingSeason
     */
    public void setPlantingSeason(boolean plantingSeason) {
        this.plantingSeason = plantingSeason;
    }

    /**
     *
     * @return
     */
    public boolean isGrowingSeason() {
        return growingSeason;
    }

    /**
     *
     * @param growingSeason
     */
    public void setGrowingSeason(boolean growingSeason) {
        this.growingSeason = growingSeason;
    }

    /**
     *
     * @return
     */
    public boolean isBusy() {
        return busy;
    }

    /**
     *
     * @return
     */
    public boolean isFree() {
        return !busy;
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
    public double getCropHealth() {
        return cropHealth;
    }

    /**
     *
     * @param cropHealth
     */
    public void setCropHealth(double cropHealth) {
        this.cropHealth = cropHealth;
    }

    /**
     *
     * @return
     */
    public double getFarmReady() {
        return farmReady;
    }

    /**
     *
     * @param farmReady
     */
    public void setFarmReady(double farmReady) {
        this.farmReady = farmReady;
    }

    /**
     *
     * @return
     */
    public double getHarvestedWeightExpected() {
        return harvestedWeightExpected;
    }

    /**
     *
     * @param harvestedWeightExpected
     */
    public void setHarvestedWeightExpected(double harvestedWeightExpected) {
        this.harvestedWeightExpected = harvestedWeightExpected;
    }

    /**
     *
     * @return
     */
    public boolean isProcessedCrop() {
        return processedCrop;
    }

    /**
     *
     * @param processedCrop
     */
    public void setProcessedCrop(boolean processedCrop) {
        this.processedCrop = processedCrop;
    }

    /**
     *
     * @return
     */
    public double getCropEficiency() {
        return cropEficiency;
    }

    /**
     *
     * @param cropEficiency
     */
    public void setCropEficiency(double cropEficiency) {
        this.cropEficiency = cropEficiency;
    }

    /**
     *
     * @return
     */
    public double getProcessedWeight() {
        return processedWeight;
    }

    /**
     *
     * @param processedWeight
     */
    public void setProcessedWeight(double processedWeight) {
        this.processedWeight = processedWeight;
    }

    /**
     *
     * @return
     */
    public double getProcessingTime() {
        return processingTime;
    }

    /**
     *
     * @param processingTime
     */
    public void setProcessingTime(double processingTime) {
        this.processingTime = processingTime;
    }

    /**
     *
     * @return
     */
    public double getTrainingLevel() {
        return trainingLevel;
    }

    /**
     *
     * @param trainingLevel
     */
    public void setTrainingLevel(double trainingLevel) {
        this.trainingLevel = trainingLevel;
    }

    /**
     *
     * @return
     */
    public double getTrainingAvailability() {
        return trainingAvailability;
    }

    /**
     *
     * @param trainingAvailability
     */
    public void setTrainingAvailability(double trainingAvailability) {
        this.trainingAvailability = trainingAvailability;
    }

    /**
     *
     * @return
     */
    public double getTrainingRelevance() {
        return trainingRelevance;
    }

    /**
     *
     * @param trainingRelevance
     */
    public void setTrainingRelevance(double trainingRelevance) {
        this.trainingRelevance = trainingRelevance;
    }

    /**
     *
     * @return
     */
    public double getTrainingCost() {
        return trainingCost;
    }

    /**
     *
     * @param trainingCost
     */
    public void setTrainingCost(double trainingCost) {
        this.trainingCost = trainingCost;
    }

    /**
     *
     * @return
     */
    public double getIrrigation() {
        return irrigation;
    }

    /**
     *
     * @param irrigation
     */
    public void setIrrigation(double irrigation) {
        this.irrigation = irrigation;
    }

    /**
     *
     * @return
     */
    public double getWaterAvailable() {
        return waterAvailable;
    }

    /**
     *
     * @param waterAvailable
     */
    public void setWaterAvailable(double waterAvailable) {
        this.waterAvailable = waterAvailable;
    }

    /**
     *
     * @return
     */
    public double getIrrigationTime() {
        return irrigationTime;
    }

    /**
     *
     * @param irrigationTime
     */
    public void setIrrigationTime(double irrigationTime) {
        this.irrigationTime = irrigationTime;
    }

    /**
     *
     * @return
     */
    public double getPestControl() {
        return pestControl;
    }

    /**
     *
     * @param pestControl
     */
    public void setPestControl(double pestControl) {
        this.pestControl = pestControl;
    }

    /**
     *
     * @return
     */
    public double getDiseasedCrop() {
        return diseasedCrop;
    }

    /**
     *
     * @param diseasedCrop
     */
    public void setDiseasedCrop(double diseasedCrop) {
        this.diseasedCrop = diseasedCrop;
    }

    /**
     *
     * @return
     */
    public double getWeedControl() {
        return weedControl;
    }

    /**
     *
     * @param weedControl
     */
    public void setWeedControl(double weedControl) {
        this.weedControl = weedControl;
    }

    /**
     *
     * @return
     */
    public double getInfestedCrop() {
        return infestedCrop;
    }

    /**
     *
     * @param infestedCrop
     */
    public void setInfestedCrop(double infestedCrop) {
        this.infestedCrop = infestedCrop;
    }

    /**
     *
     * @return
     */
    public double getSuppliesAvailability() {
        return suppliesAvailability;
    }

    /**
     *
     * @param suppliesAvailability
     */
    public void setSuppliesAvailability(double suppliesAvailability) {
        this.suppliesAvailability = suppliesAvailability;
    }

    /**
     *
     * @return
     */
    public double getSuppliesCost() {
        return suppliesCost;
    }

    /**
     *
     * @param suppliesCost
     */
    public void setSuppliesCost(double suppliesCost) {
        this.suppliesCost = suppliesCost;
    }

    /**
     *
     * @return
     */
    public double getToolsAvailability() {
        return toolsAvailability;
    }

    /**
     *
     * @param toolsAvailability
     */
    public void setToolsAvailability(double toolsAvailability) {
        this.toolsAvailability = toolsAvailability;
    }

    /**
     *
     * @return
     */
    public double getToolsCost() {
        return toolsCost;
    }

    /**
     *
     * @param toolsCost
     */
    public void setToolsCost(double toolsCost) {
        this.toolsCost = toolsCost;
    }

    /**
     *
     * @return
     */
    public boolean isAssociated() {
        return associated;
    }

    /**
     *
     * @param associated
     */
    public void setAssociated(boolean associated) {
        this.associated = associated;
    }

    /**
     *
     * @return
     */
    public int getNeighbors() {
        return neighbors;
    }

    /**
     *
     * @param neighbors
     */
    public void setNeighbors(int neighbors) {
        this.neighbors = neighbors;
    }

    /**
     *
     * @return
     */
    public double getCollaborationValue() {
        return collaborationValue;
    }

    /**
     *
     * @param collaborationValue
     */
    public void setCollaborationValue(double collaborationValue) {
        this.collaborationValue = collaborationValue;
    }

    /**
     *
     * @return
     */
    public double getHealthProgramsAvailability() {
        return healthProgramsAvailability;
    }

    /**
     *
     * @param healthProgramsAvailability
     */
    public void setHealthProgramsAvailability(double healthProgramsAvailability) {
        this.healthProgramsAvailability = healthProgramsAvailability;
    }

    /**
     *
     * @return
     */
    public boolean isLivestockFarming() {
        return livestockFarming;
    }

    /**
     *
     * @param livestockFarming
     */
    public void setLivestockFarming(boolean livestockFarming) {
        this.livestockFarming = livestockFarming;
    }

    /**
     *
     * @return
     */
    public double getLivestockHealth() {
        return livestockHealth;
    }

    /**
     *
     * @param livestockHealth
     */
    public void setLivestockHealth(double livestockHealth) {
        this.livestockHealth = livestockHealth;
    }

    /**
     *
     * @return
     */
    public int getLivestockNumber() {
        return livestockNumber;
    }

    /**
     *
     * @param livestockNumber
     */
    public void setLivestockNumber(int livestockNumber) {
        this.livestockNumber = livestockNumber;
    }

    /**
     *
     * @return
     */
    public double getFamilyTime() {
        return familyTime;
    }

    /**
     *
     * @param familyTime
     */
    public void setFamilyTime(double familyTime) {
        this.familyTime = familyTime;
    }

    /**
     *
     * @return
     */
    public double getPeasantFamilyAffinity() {
        return peasantFamilyAffinity;
    }

    /**
     *
     * @param peasantFamilyAffinity
     */
    public void setPeasantFamilyAffinity(double peasantFamilyAffinity) {
        this.peasantFamilyAffinity = peasantFamilyAffinity;
    }

    /**
     *
     * @return
     */
    public double getFamilyTimeAvailability() {
        return familyTimeAvailability;
    }

    /**
     *
     * @param familyTimeAvailability
     */
    public void setFamilyTimeAvailability(double familyTimeAvailability) {
        this.familyTimeAvailability = familyTimeAvailability;
    }

    /**
     *
     */
    public void increaseFamilyTimeAvailability() {
        ReportBESA.info("");
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
    public void useFamilyTimeAvailability() {
        //ReportBESA.info("");
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
    public double getCommunications() {
        return communications;
    }

    /**
     *
     * @param communications
     */
    public void setCommunications(double communications) {
        this.communications = communications;
    }

    /**
     *
     * @return
     */
    public double getSocialCompatibility() {
        return socialCompatibility;
    }

    /**
     *
     * @param socialCompatibility
     */
    public void setSocialCompatibility(double socialCompatibility) {
        this.socialCompatibility = socialCompatibility;
    }

    /**
     *
     * @return
     */
    public double getRestingTimeAvailibility() {
        return restingTimeAvailibility;
    }

    /**
     *
     * @param restingTimeAvailibility
     */
    public void setRestingTimeAvailibility(double restingTimeAvailibility) {
        this.restingTimeAvailibility = restingTimeAvailibility;
    }

    /**
     *
     * @return
     */
    public double getPeasantRestAffinity() {
        return peasantRestAffinity;
    }

    /**
     *
     * @param peasantRestAffinity
     */
    public void setPeasantRestAffinity(double peasantRestAffinity) {
        this.peasantRestAffinity = peasantRestAffinity;
    }

    /**
     *
     * @return
     */
    public double getLeisureOptions() {
        return leisureOptions;
    }

    /**
     *
     * @param leisureOptions
     */
    public void setLeisureOptions(double leisureOptions) {
        this.leisureOptions = leisureOptions;
    }

    /**
     *
     */
    public void useLeisureOptions() {
        /*ReportBESA.info("");
        if (this.leisureOptions > 0) {
            this.leisureOptions = this.leisureOptions - 0.1;
        }*/
        this.leisureOptions = 0;
    }

    /**
     *
     */
    public void increaseLeisureOptions() {
        ReportBESA.info("");
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
    public void reduceHouseCondition() {
        //ReportBESA.info("");
        /*if (this.housingCondition > 0) {
            this.housingCondition = this.housingCondition - 0.1;
        }*/
        this.housingCondition = 0;
    }

    /**
     *
     */
    public void increaseHouseCondition() {
        //ReportBESA.info("");
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
    public void increaseTools() {
        //ReportBESA.info("");
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
    public void useTools() {
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
    public boolean equals(Object object) {
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
     * @return
     */
    @Override
    public String toString() {
        return "{"
                + "purpose='" + purpose + '\''
                + ", health=" + health
                + ", productivity=" + productivity
                + ", wellBeging=" + wellBeging
                + ", worker=" + worker
                + ", peasantQualityFactor=" + peasantQualityFactor
                + ", tools=" + tools
                + ", supplies=" + supplies
                + ", liveStockAffinity=" + liveStockAffinity
                + ", farm=" + farm
                + ", farmName='" + farmName + '\''
                + ", farmSize=" + farmSize
                + ", housing=" + housing
                + ", servicesPresence=" + servicesPresence
                + ", housingSize=" + housingSize
                + ", housingCondition=" + housingCondition
                + ", housingLocation=" + housingLocation
                + ", farmDistance=" + farmDistance
                + ", money=" + money
                + ", totalIncome=" + totalIncome
                + ", debtPayment=" + debtPayment
                + ", harvestedWeight=" + harvestedWeight
                + ", harverstSeason=" + harverstSeason
                + ", housingQuailty=" + housingQuailty
                + ", timeSpentOnMaintenance=" + timeSpentOnMaintenance
                + ", seeds=" + seeds
                + ", plantingSeason=" + plantingSeason
                + ", growingSeason=" + growingSeason
                + ", busy=" + busy
                + ", cropHealth=" + cropHealth
                + ", farmReady=" + farmReady
                + '}';
    }

    /**
     *
     */
    public void increaseFarmReady() {
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
    public void increaseTrainingLevel() {
        this.trainingLevel = 1;
    }

    /**
     *
     * @param internalCurrentDate
     */
    public void setInternalCurrentDate(String internalCurrentDate) {
        this.internalCurrentDate = internalCurrentDate;
    }

    /**
     *
     * @return
     */
    public String getInternalCurrentDate() {
        return this.internalCurrentDate;
    }

}
