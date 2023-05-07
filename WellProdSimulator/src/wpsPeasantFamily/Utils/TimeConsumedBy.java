/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wpsPeasantFamily.Utils;

/**
 *
 * @author jairo
 */
public enum TimeConsumedBy {

    LookForLoan(4),
    DoHealthCare(4),
    DoVitalsTask(12),
    AttendReligiousEvents(2),
    AttendToLivestock(2),
    CheckCrops(1),
    HarvestCrops(2),
    IrrigateCrops(2),
    MaintainHouse(2),
    ManagePests(2),
    PlantCrops(2),
    PrepareLand(2),
    ProcessProducts(2),
    SellCrops(2),
    SellProducts(2),
    SpendFamilyTime(2),
    SpendFriendsTime(2),
    GetTraining(2),
    LookForALand(8),
    ObtainLivestock(4),
    ObtainPesticides(4),
    ObtainSeeds(4),
    ObtainSupplies(4),
    ObtainTools(4),
    ObtainWater(4),
    Communicate(1),
    LookForCollaboration(1),
    ProvideCollaboration(2),
    EngageInLeisureActivitiesGoal(1),
    FindNews(2),
    WasteTimeAndResourcesGoal(1);
    
    private int time;

    private TimeConsumedBy(int time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
