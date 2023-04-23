package wpsWorld.Helper;

import BESA.Log.ReportBESA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton pojo of the world configuration keys
 */
public class WorldConfiguration {

    private static final Logger logger = LogManager.getLogger(WorldConfiguration.class);
    private static final String CONF_NAME = "resources/app.properties";
    private static WorldConfiguration instance = null;
    private Properties appProperties;
    private boolean diseasePerturbation = false;
    private boolean coursePerturbation = false;

    /**
     * In the constructor loads the configuration file in memory
     */
    private WorldConfiguration() {

        try (InputStream in = new FileInputStream(CONF_NAME)) {

            if (in == null) {
                ReportBESA.error("Sorry, unable to find app.properties");
                return;
            }

            //load a properties file from class path, inside static method
            this.appProperties = new Properties();
            this.appProperties.load(in);

        } catch (IOException ex) {
            ReportBESA.error("No app config file found!!");
            ex.printStackTrace();
        }

    }

    /**
     *
     * @return
     */
    public static WorldConfiguration getPropsInstance() {
        if (instance == null) {
            instance = new WorldConfiguration();
        }
        return instance;
    }

    /**
     *
     * @param diseasePerturbation
     * @param coursePerturbation
     */
    public void setPerturbations(boolean diseasePerturbation, boolean coursePerturbation) {
        getPropsInstance().setDiseasePerturbation(diseasePerturbation);
        getPropsInstance().setCoursePerturbation(coursePerturbation);
    }

    /**
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return this.appProperties.getProperty(key);
    }

    /**
     *
     * @return
     */
    public boolean isDiseasePerturbation() {
        return this.diseasePerturbation;
    }

    /**
     *
     * @param diseasePerturbation
     */
    public void setDiseasePerturbation(boolean diseasePerturbation) {
        this.diseasePerturbation = diseasePerturbation;
    }

    /**
     *
     * @return
     */
    public boolean isCoursePerturbation() {
        return this.coursePerturbation;
    }

    /**
     *
     * @param coursePerturbation
     */
    public void setCoursePerturbation(boolean coursePerturbation) {
        this.coursePerturbation = coursePerturbation;
    }
}