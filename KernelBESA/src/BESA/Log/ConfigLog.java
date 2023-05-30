package BESA.Log;

import BESA.Config.ConfigExceptionBESA;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConfigLog {

    private String CONFIG_FILE = "confbesa.xml";
    private boolean trace = false;
    private boolean debug = true;
    private boolean info = true;
    private boolean warn = true;
    private boolean error = true;
    private boolean fatal = true;
    private boolean logmanager = false;
    private String dateformat = "hh:mm:ss:SS";

    public ConfigLog() throws ConfigExceptionBESA {
        loadConfiFile();
    }

    public ConfigLog(String path) throws ConfigExceptionBESA {
        CONFIG_FILE = path;
        loadConfiFile();
    }

    public void loadConfiFile() throws ConfigExceptionBESA {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(CONFIG_FILE);
            if (inputStream == null) {
                System.out.print("error config XML fil");
            }
            Document doc = dBuilder.parse(inputStream);

            doc.getDocumentElement().normalize();

            NodeList logList = doc.getElementsByTagName("Log");
            if (logList.getLength() > 0) {
                Node logNode = logList.item(0);

                if (logNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element logElement = (Element) logNode;

                    updateLogValuesFromXML(logElement);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ConfigExceptionBESA("Error reading the configuration file: " + e.getMessage());
        }
    }

    private void updateLogValuesFromXML(Element logElement) {
        Boolean aux = null;

        aux = getBooleanAttribute(logElement, "trace");
        if (aux != null) {
            trace = aux;
        }
        aux = getBooleanAttribute(logElement, "debug");
        if (aux != null) {
            debug = aux;
        }
        aux = getBooleanAttribute(logElement, "info");
        if (aux != null) {
            info = aux;
        }
        aux = getBooleanAttribute(logElement, "warn");
        if (aux != null) {
            warn = aux;
        }
        aux = getBooleanAttribute(logElement, "error");
        if (aux != null) {
            error = aux;
        }
        aux = getBooleanAttribute(logElement, "fatal");
        if (aux != null) {
            fatal = aux;
        }
        String dateFAux = logElement.getAttribute("dateformat");
        if (dateFAux != null && !dateFAux.isEmpty()) {
            dateformat = dateFAux;
        }
    }

    private Boolean getBooleanAttribute(Element element, String attributeName) {
        String attributeValue = element.getAttribute(attributeName);
        if (attributeValue != null && !attributeValue.isEmpty()) {
            return Boolean.parseBoolean(attributeValue);
        }
        return null;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isFatal() {
        return fatal;
    }

    public void setFatal(boolean fatal) {
        this.fatal = fatal;
    }

    public boolean isInfo() {
        return info;
    }

    public void setInfo(boolean info) {
        this.info = info;
    }

    public boolean isLogmanager() {
        return logmanager;
    }

    public void setLogmanager(boolean logmanager) {
        this.logmanager = logmanager;
    }

    public boolean isTrace() {
        return trace;
    }

    public void setTrace(boolean trace) {
        this.trace = trace;
    }

    public boolean isWarn() {
        return warn;
    }

    public void setWarn(boolean warn) {
        this.warn = warn;
    }

    public String getDateFormat() {
        return dateformat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateformat = dateFormat;
    }

}
