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
package wpsActivator;

import org.snakeyaml.engine.v2.api.Load;
import org.snakeyaml.engine.v2.api.LoadSettings;
import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import wpsPeasantFamily.Data.FarmingResource;
import wpsPeasantFamily.Data.PeasantFamilyProfile;
import wpsViewer.Agent.wpsReport;

/**
 *
 * @author jairo
 */
public final class wpsConfig {

    private static wpsConfig instance = null;
    private String SocietyAgentName;
    private String BankAgentName;
    private String MarketAgentName;
    private String ControlAgentName;
    private String PerturbationAgentName;
    private String ViewerAgentName;
    private String peasantType = "";
    private String rainfallConditions = "";
    private String perturbation = "";
    private String startSimulationDate;
    private int peasantSerialID;

    PeasantFamilyProfile regularFarmerProfile;
    PeasantFamilyProfile lazyFarmerProfile;
    PeasantFamilyProfile proactiveFarmerProfile;

    /**
     *
     * @return
     */
    public static wpsConfig getInstance() {
        if (instance == null) {
            instance = new wpsConfig();
        }
        return instance;
    }

    /**
     *
     * @param args
     */
    private wpsConfig() {

        loadPeasantConfig();
        loadWPSConfig();
        this.peasantSerialID = 1;

    }

    public String getSocietyAgentName() {
        return SocietyAgentName;
    }

    public String getBankAgentName() {
        return BankAgentName;
    }

    public String getMarketAgentName() {
        return MarketAgentName;
    }

    public String getPerturbationAgentName() {
        return PerturbationAgentName;
    }

    public String getControlAgentName() {
        return ControlAgentName;
    }

    public String getViewerAgentName() {
        return this.ViewerAgentName;
    }

    /**
     *
     * @return
     */
    public PeasantFamilyProfile getRegularFarmerProfile() {
        return regularFarmerProfile;
    }

    /**
     *
     * @return
     */
    public PeasantFamilyProfile getLazyFarmerProfile() {
        return lazyFarmerProfile;
    }

    /**
     *
     * @return
     */
    public PeasantFamilyProfile getProactiveFarmerProfile() {
        return proactiveFarmerProfile;
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

    public Map<String, FarmingResource> loadMarketConfig() {

        Map<String, FarmingResource> priceList = new HashMap<>();
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;

        try {
            // Especifica la ubicación del archivo .properties
            fileInputStream = new FileInputStream("resources/wpsConfig.properties");
            // Carga las propiedades desde el archivo
            properties.load(fileInputStream);

            priceList.put("water",
                    new FarmingResource(
                            "water",
                            properties.getProperty("market.water.price"),
                            properties.getProperty("market.water.quantity")
                    )
            );
            priceList.put("seeds",
                    new FarmingResource(
                            "seeds",
                            properties.getProperty("market.seeds.price"),
                            properties.getProperty("market.seeds.quantity")
                    )
            );
            priceList.put("pesticides",
                    new FarmingResource(
                            "pesticides",
                            properties.getProperty("market.pesticides.price"),
                            properties.getProperty("market.pesticides.quantity")
                    )
            );
            priceList.put("tools",
                    new FarmingResource(
                            "tools",
                            properties.getProperty("market.tools.price"),
                            properties.getProperty("market.tools.quantity")
                    )
            );
            priceList.put("livestock",
                    new FarmingResource(
                            "livestock",
                            properties.getProperty("market.livestock.price"),
                            properties.getProperty("market.livestock.quantity")
                    )
            );
            priceList.put("ñame",
                    new FarmingResource(
                            "ñame",
                            properties.getProperty("market.ñame.price"),
                            properties.getProperty("market.ñame.quantity")
                    )
            );
            fileInputStream.close();
            return priceList;
        } catch (IOException e) {
            wpsReport.error(e.getMessage());
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    wpsReport.error(e.getMessage());
                }
            }
        }
        return null;
    }

    private void loadWPSConfig() {

        Properties properties = new Properties();

        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream fileInputStream = classLoader.getResourceAsStream("wpsConfig.properties")) {
            // Especifica la ubicación del archivo .properties

            // Carga las propiedades desde el archivo
            properties.load(fileInputStream);

            // Valores iniciales de la simulación
            this.startSimulationDate = properties.getProperty("control.startdate");
            this.BankAgentName = properties.getProperty("bank.name");
            this.ControlAgentName = properties.getProperty("control.name");
            this.MarketAgentName = properties.getProperty("market.name");
            this.SocietyAgentName = properties.getProperty("society.name");
            this.PerturbationAgentName = properties.getProperty("perturbation.name");
            this.ViewerAgentName = properties.getProperty("viewer.name");

            System.out.println("---" + this.startSimulationDate + "----");
            fileInputStream.close();
        } catch (IOException e) {
            wpsReport.error(e.getMessage());
        }
    }

    private void loadPeasantConfig() {
        LoadSettings settings = LoadSettings.builder().build();
        Load load = new Load(settings);
        Map<String, Object> data;

        try {

            String jsonData;
            String yamlContent;
            Gson gson = new Gson();

            yamlContent = new String(Files.readAllBytes(Paths.get("resources/wpsRegularPeasant.yml")));
            data = (Map<String, Object>) load.loadFromString(yamlContent);
            wpsReport.info("Configuración RegularPeasant cargada con exito");
            Map<String, Object> regularPeasant = (Map<String, Object>) data.get("RegularPeasant");
            jsonData = gson.toJson(regularPeasant);
            regularFarmerProfile = gson.fromJson(jsonData, PeasantFamilyProfile.class);
            //wpsReport.info(regularFarmerProfile);

            yamlContent = new String(Files.readAllBytes(Paths.get("resources/wpsLazyPeasant.yml")));
            data = (Map<String, Object>) load.loadFromString(yamlContent);
            wpsReport.info("Configuración LazyPeasant cargada con exito");
            Map<String, Object> lazyPeasant = (Map<String, Object>) data.get("LazyPeasant");
            jsonData = gson.toJson(lazyPeasant);
            lazyFarmerProfile = gson.fromJson(jsonData, PeasantFamilyProfile.class);
            //wpsReport.info(lazyFarmerProfile);

            yamlContent = new String(Files.readAllBytes(Paths.get("resources/wpsProactivePeasant.yml")));
            data = (Map<String, Object>) load.loadFromString(yamlContent);
            wpsReport.info("Configuración ProactivePeasant cargada con exito");
            Map<String, Object> proactivePeasant = (Map<String, Object>) data.get("ProactivePeasant");
            jsonData = gson.toJson(proactivePeasant);
            proactiveFarmerProfile = gson.fromJson(jsonData, PeasantFamilyProfile.class);
            //wpsReport.info(proactiveFarmerProfile);

        } catch (IOException ex) {
            wpsReport.error("No hay configuración válida");
            System.exit(0);
        }
    }

    public synchronized String getUniqueFarmerName() {
        return "PeasantFamily_" + peasantSerialID++;
    }

    public synchronized PeasantFamilyProfile getFarmerProfile() {
        Random rand = new Random();

        switch (rand.nextInt(3)) {
            case 0:
                return this.getRegularFarmerProfile();
            case 1:
                return this.getProactiveFarmerProfile();
            case 2:
                return this.getRegularFarmerProfile();
            default:
                return this.getRegularFarmerProfile();
        }
    }

}
