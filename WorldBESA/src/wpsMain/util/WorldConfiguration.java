package wpsMain.util;

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
    private static final String CONF_NAME = "src/main/resources/app.properties";
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
                System.out.println("Sorry, unable to find app.properties");
                return;
            }

            //load a properties file from class path, inside static method
            this.appProperties = new Properties();
            this.appProperties.load(in);

        } catch (IOException ex) {
            logger.error("No app config file found!!");
            ex.printStackTrace();
        }

    }

    public static WorldConfiguration getPropsInstance() {
        if (instance == null) {
            instance = new WorldConfiguration();
        }
        return instance;
    }

    public void setPerturbations(boolean diseasePerturbation, boolean coursePerturbation) {
        getPropsInstance().setDiseasePerturbation(diseasePerturbation);
        getPropsInstance().setCoursePerturbation(coursePerturbation);
    }

    public String getProperty(String key) {
        return this.appProperties.getProperty(key);
    }

    public boolean isDiseasePerturbation() {
        return this.diseasePerturbation;
    }

    public void setDiseasePerturbation(boolean diseasePerturbation) {
        this.diseasePerturbation = diseasePerturbation;
    }

    public boolean isCoursePerturbation() {
        return this.coursePerturbation;
    }

    public void setCoursePerturbation(boolean coursePerturbation) {
        this.coursePerturbation = coursePerturbation;
    }
}