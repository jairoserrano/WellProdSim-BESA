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
package wpsSimulator;

import BESA.Log.ReportBESA;
import org.snakeyaml.engine.v2.api.Load;
import org.snakeyaml.engine.v2.api.LoadSettings;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import wpsPeasant.Utils.PeasantProfile;

/**
 *
 * @author jairo
 */
public class wpsConfig {

    private static wpsConfig instance = null;

    private String peasantType = "";
    private String rainfallConditions = "";
    private String perturbation = "";
    private String startSimulationDate = "15/03/2022";
    private String mainRiceCropID = "rice_1";
    private long dayLength = 50;
    private long checkCropStatusPeriodic = 7;
    PeasantProfile regularFarmerProfile;
    PeasantProfile lazyFarmerProfile;
    PeasantProfile proactiveFarmerProfile;

    /**
     *
     * @param args
     */
    @SuppressWarnings("unchecked")
    private wpsConfig() {

        LoadSettings settings = LoadSettings.builder().build();
        Load load = new Load(settings);
        Map<String, Object> data;

        try {

            String jsonData;
            String yamlContent;
            Gson gson = new Gson();

            yamlContent = new String(Files.readAllBytes(Paths.get("resources/wpsRegularPeasant.yml")));
            data = (Map<String, Object>) load.loadFromString(yamlContent);
            ReportBESA.info("Configuración RegularPeasant cargada con exito");
            Map<String, Object> regularPeasant = (Map<String, Object>) data.get("RegularPeasant");
            jsonData = gson.toJson(regularPeasant);
            regularFarmerProfile = gson.fromJson(jsonData, PeasantProfile.class);

            //ReportBESA.info(regularFarmerProfile);
            yamlContent = new String(Files.readAllBytes(Paths.get("resources/wpsLazyPeasant.yml")));
            data = (Map<String, Object>) load.loadFromString(yamlContent);
            ReportBESA.info("Configuración LazyPeasant cargada con exito");
            Map<String, Object> lazyPeasant = (Map<String, Object>) data.get("LazyPeasant");
            jsonData = gson.toJson(lazyPeasant);
            lazyFarmerProfile = gson.fromJson(jsonData, PeasantProfile.class);

            //ReportBESA.info(lazyFarmerProfile);
            yamlContent = new String(Files.readAllBytes(Paths.get("resources/wpsProactivePeasant.yml")));
            data = (Map<String, Object>) load.loadFromString(yamlContent);
            ReportBESA.info("Configuración ProactivePeasant cargada con exito");
            Map<String, Object> proactivePeasant = (Map<String, Object>) data.get("ProactivePeasant");
            jsonData = gson.toJson(proactivePeasant);
            proactiveFarmerProfile = gson.fromJson(jsonData, PeasantProfile.class);

            //ReportBESA.info(proactiveFarmerProfile);
        } catch (IOException ex) {
            Logger.getLogger(wpsConfig.class.getName()).log(Level.SEVERE, null, ex);
            ReportBESA.error("No hay configuración válida");
            System.exit(0);
        }

        /* Simulation parameters
        if (args.length == 7) {
            peasantType = args[0];
            rainfallConditions = args[1];
            perturbation = args[2];
            startSimulationDate = args[3];
            mainRiceCropID = args[4];
            //dayLength = Long(args[5]);
            //checkCropStatusPeriodic = args[6];
        } else {*/
        peasantType = "normal";
        rainfallConditions = "normal";
        perturbation = "none";
        startSimulationDate = "15/03/2022";
        mainRiceCropID = "rice_1";
        dayLength = 50;
        checkCropStatusPeriodic = 7;
        //}
    }

    public static wpsConfig getInstance() {
        if (instance == null) {
            instance = new wpsConfig();
        }
        return instance;
    }

    /**
     *
     * @return
     */
    public PeasantProfile getRegularFarmerProfile() {
        return regularFarmerProfile;
    }

    /**
     *
     * @return
     */
    public PeasantProfile getLazyFarmerProfile() {
        return lazyFarmerProfile;
    }

    /**
     *
     * @return
     */
    public PeasantProfile getProactiveFarmerProfile() {
        return proactiveFarmerProfile;
    }

    /**
     *
     * @return
     */
    public String getMainRiceCropID() {
        return mainRiceCropID;
    }

    /**
     *
     * @param mainRiceCropID
     */
    public void setMainRiceCropID(String mainRiceCropID) {
        this.mainRiceCropID = mainRiceCropID;
    }

    /**
     *
     * @return
     */
    public long getDayLength() {
        return dayLength;
    }

    /**
     *
     * @param dayLength
     */
    public void setDayLength(long dayLength) {
        this.dayLength = dayLength;
    }

    /**
     *
     * @return
     */
    public long getCheckCropStatusPeriodic() {
        return checkCropStatusPeriodic;
    }

    /**
     *
     * @param checkCropStatusPeriodic
     */
    public void setCheckCropStatusPeriodic(long checkCropStatusPeriodic) {
        this.checkCropStatusPeriodic = checkCropStatusPeriodic;
    }

    /**
     *
     * @return
     */
    public String getStartSimulationDate() {
        return startSimulationDate;
    }

    /**
     *
     * @param startSimulationDate
     */
    public void setStartSimulationDate(String startSimulationDate) {
        this.startSimulationDate = startSimulationDate;
    }

    /**
     *
     * @return
     */
    public String getPeasantType() {
        return peasantType;
    }

    /**
     *
     * @param peasantType
     */
    public void setPeasantType(String peasantType) {
        this.peasantType = peasantType;
    }

    /**
     *
     * @return
     */
    public String getRainfallConditions() {
        return rainfallConditions;
    }

    /**
     *
     * @param rainfallConditions
     */
    public void setRainfallConditions(String rainfallConditions) {
        this.rainfallConditions = rainfallConditions;
    }

    /**
     *
     * @return
     */
    public String getPerturbation() {
        return perturbation;
    }

    /**
     *
     * @param perturbation
     */
    public void setPerturbation(String perturbation) {
        this.perturbation = perturbation;
    }

}
