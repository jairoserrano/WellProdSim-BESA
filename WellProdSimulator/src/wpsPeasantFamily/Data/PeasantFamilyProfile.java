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
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public class PeasantFamilyProfile implements Serializable, Cloneable {

    private String purpose;
    private String peasantKind;
    private String peasantFamilyAlias;
    private double peasantFamilyMinimalVital;
    private int health;
    private double productivity;
    private double wellBeging;
    private boolean worker;
    private double peasantQualityFactor;
    private double liveStockAffinity;
    private boolean land;
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
    private int waterAvailable;
    private int pesticidesAvailable;
    private int tools;
    private int supplies;
    private int riceSeedsByHectare;
    private int seeds;
    private int harvestedWeight;
    private double totalHarvestedWeight;
    private String startRiceSeason;
    private String endRiceSeason;
    private String currentCropName;

    /**
     *
     */
    public PeasantFamilyProfile() {
    }
    public String getPeasantKind() {
        return peasantKind;
    }
    public void setPeasantKind(String peasantKind) {
        this.peasantKind = peasantKind;
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
    public String getCurrentCropName() {
        return this.currentCropName;
    }

    /**
     *
     * @param currentCropName
     */
    public void setCurrentCropName(String currentCropName) {
        this.currentCropName = currentCropName;
    }

    /**
     *
     * @return
     */
    public int getRiceSeedsByHectare() {
        return riceSeedsByHectare;
    }

    /**
     *
     * @param riceSeedsByHectare
     */
    public void setRiceSeedsByHectare(int riceSeedsByHectare) {
        this.riceSeedsByHectare = riceSeedsByHectare;
    }

    /**
     *
     * @return
     */
    public int getPesticidesAvailable() {
        return pesticidesAvailable;
    }

    /**
     *
     * @param pesticidesAvailable
     */
    public void setPesticidesAvailable(int pesticidesAvailable) {
        this.pesticidesAvailable = pesticidesAvailable;
    }

    /**
     *
     * @return
     */
    public double getPeasantFamilyMinimalVital() {
        return peasantFamilyMinimalVital;
    }

    /**
     *
     * @param peasantFamilyMinimalVital
     */
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

    /**
     *
     */
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
    public String getPeasantFamilyAlias() {
        return peasantFamilyAlias;
    }

    /**
     *
     * @return
     */
    public String getPeasantFamilyLandAlias() {
        return peasantFamilyAlias + "_land";
    }

    /**
     *
     * @param peasantFamilyAlias
     */
    public void setPeasantFamilyAlias(String peasantFamilyAlias) {
        this.peasantFamilyAlias = peasantFamilyAlias;
    }

    /**
     *
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     *
     * @param health
     */
    public void setHealth(int health) {
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
            this.health = this.health + 2;
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
    public synchronized void setProductivity(double productivity) {
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
    public synchronized void setWellBeging(double wellBeging) {
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
    public synchronized void setWorker(boolean worker) {
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
    public synchronized void setPeasantQualityFactor(double peasantQualityFactor) {
        this.peasantQualityFactor = peasantQualityFactor;
    }

    /**
     *
     * @return
     */
    public int getTools() {
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
    public int getSupplies() {
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
    public double getLiveStockAffinity() {
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
    public boolean getLand() {
        return land;
    }

    /**
     *
     * @param farm
     */
    public synchronized void setLand(boolean land) {
        this.land = land;
    }

    /**
     *
     * @return
     */
    public int getCropSize() {
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
    public synchronized void setServicesPresence(double servicesPresence) {
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
    public synchronized void setHousingSize(double housingSize) {
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
    public synchronized void setHousingCondition(double housingCondition) {
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
    public synchronized void setHousingLocation(double housingLocation) {
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
    public synchronized void setFarmDistance(double farmDistance) {
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
    public double getTotalIncome() {
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
    public double getLoanAmountToPay() {
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
    public int getHarvestedWeight() {
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
    public double getHousingQuailty() {
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
    public double getTimeSpentOnMaintenance() {
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
    public int getSeeds() {
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
    public double getCropHealth() {
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
    public double getFarmReady() {
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
    public double getHarvestedWeightExpected() {
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
    public double getCropEficiency() {
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
    public double getProcessedWeight() {
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
    public double getProcessingTime() {
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
     * @param pesticidesAvailable
     */
    public synchronized void setPesticidesAvailable(Integer pesticidesAvailable) {
        this.pesticidesAvailable += pesticidesAvailable;
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
     * @param water
     */
    public void useWater(int water) {
        this.waterAvailable -= water;
    }

    /**
     *
     */
    public void decreaseHealth() {
        this.health -= 10;
    }

    /**
     *
     * @return
     */
    public String toJson() {
        return ", peasantKind=" + peasantKind + ", purpose=" + purpose + ", peasantFamilyMinimalVital=" + peasantFamilyMinimalVital + ", health=" + health + ", productivity=" + productivity + ", wellBeging=" + wellBeging + ", worker=" + worker + ", peasantQualityFactor=" + peasantQualityFactor + ", liveStockAffinity=" + liveStockAffinity + ", farm=" + land + ", cropSize=" + cropSize + ", housing=" + housing + ", servicesPresence=" + servicesPresence + ", housingSize=" + housingSize + ", housingCondition=" + housingCondition + ", housingLocation=" + housingLocation + ", farmDistance=" + farmDistance + ", money=" + money + ", totalIncome=" + totalIncome + ", loanAmountToPay=" + loanAmountToPay + ", housingQuailty=" + housingQuailty + ", timeSpentOnMaintenance=" + timeSpentOnMaintenance + ", cropHealth=" + cropHealth + ", farmReady=" + farmReady + ", harvestedWeightExpected=" + harvestedWeightExpected + ", processedCrop=" + processedCrop + ", cropEficiency=" + cropEficiency + ", processedWeight=" + processedWeight + ", processingTime=" + processingTime + ", trainingLevel=" + trainingLevel + ", trainingAvailability=" + trainingAvailability + ", trainingRelevance=" + trainingRelevance + ", trainingCost=" + trainingCost + ", irrigation=" + irrigation + ", irrigationTime=" + irrigationTime + ", pestControl=" + pestControl + ", diseasedCrop=" + diseasedCrop + ", weedControl=" + weedControl + ", infestedCrop=" + infestedCrop + ", suppliesAvailability=" + suppliesAvailability + ", toolsAvailability=" + toolsAvailability + ", associated=" + associated + ", neighbors=" + neighbors + ", collaborationValue=" + collaborationValue + ", totalHarvestedWeight=" + totalHarvestedWeight  +", healthProgramsAvailability=" + healthProgramsAvailability + ", livestockFarming=" + livestockFarming + ", livestockHealth=" + livestockHealth + ", livestockNumber=" + livestockNumber + ", familyTime=" + familyTime + ", peasantFamilyAffinity=" + peasantFamilyAffinity + ", familyTimeAvailability=" + familyTimeAvailability + ", communications=" + communications + ", socialCompatibility=" + socialCompatibility + ", restingTimeAvailibility=" + restingTimeAvailibility + ", peasantRestAffinity=" + peasantRestAffinity + ", leisureOptions=" + leisureOptions + ", sellDone=" + sellDone + ", waterAvailable=" + waterAvailable + ", pesticidesAvailable=" + pesticidesAvailable + ", tools=" + tools + ", supplies=" + supplies + ", riceSeedsByHectare=" + riceSeedsByHectare + ", seeds=" + seeds + ", harvestedWeight=" + harvestedWeight + ", startRiceSeason=" + startRiceSeason + ", endRiceSeason=" + endRiceSeason + ", currentCropName=" + currentCropName + '}';
    }

    /**
     *
     * @return
     */
    @Override
    public synchronized String toString() {
        return ""
                + " * Peasant Kind: " + peasantKind + "\n"
                + " * Health: " + health + "\n"
                + " * Land: " + land + "\n"
                + " * Money: " + money + "\n"
                + " * Total Harvested Weight: " + totalHarvestedWeight + "\n"
                + " * Loan Amount To Pay: " + loanAmountToPay + "\n"
                + " * Harvested Weight: " + harvestedWeight + "\n"
                + " * Crop Health: " + cropHealth + "\n"
                + " * Processed Weight: " + processedWeight + "\n"
                + " * Diseased Crop: " + diseasedCrop + "\n"
                + " * Weed Control: " + weedControl + "\n"
                + " * Infested Crop: " + infestedCrop + "\n"
                + " * Sell Done: " + sellDone + "\n"
                + " * Water Available: " + waterAvailable + "\n"
                + " * Pesticides Available: " + pesticidesAvailable + "\n"
                + " * Tools: " + tools + "\n"
                + " * Supplies: " + supplies + "\n"
                + " * Seeds: " + seeds + "\n"
                + " * Start Ñame Season: " + startRiceSeason + "\n"
                + " * Current Crop Name: " + currentCropName + "\n"
                + " * ==========================================================================\n"
                + " \n";
    }

    @Override
    public PeasantFamilyProfile clone() {
        try {
            return (PeasantFamilyProfile) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void increaseTotalHarvestedWeight(double harvested) {
        this.totalHarvestedWeight += harvested;
    }

}
