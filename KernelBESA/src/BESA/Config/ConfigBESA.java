/*
 * @(#)ConfigBESA.java 3.0	11/09/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved. Takina
 * and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import BESA.Config.Container;
import BESA.Config.Environment;
import BESA.Config.Remote;
import BESA.Config.EnvironmentCase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConfigBESA {

    private String CONFIG_FILE = "confbesa.xml";
    private final String DEFAULT_IP_ADDRESS = "127.0.0.1";
    private final int DEFAULT_SEND_EVENT_ATTEMPS = 10;
    private final long DEFAULT_SEND_EVENT_TIMEOUT = 1;
    private final long DEFAULT_RMI_TIMEOUT = 1000;
    private EnvironmentCase environmentCase = EnvironmentCase.LOCAL;
    private String aliasContainer = "MAS";
    private double passwordContainer = 0.91;
    private String ipaddress = "127.0.0.1";
    private int rmiPort = 1099;
    private String mcaddress = "230.0.0.1";
    private int mcport = 2222;
    private String baplocatoradd = "127.0.0.1";
    private int bapport = 7000;
    private long sendEventTimeout = 1;
    private long rMITimeout = 1000;
    private int sendEventAttemps = 10;
    private int bloport = 8080;
    private int bpoPort = 8000;

    public ConfigBESA() throws ConfigExceptionBESA {
        loadConfig();
    }

    public ConfigBESA(String configBESAPATH) throws ConfigExceptionBESA {
        CONFIG_FILE = configBESAPATH;                                           //Sets a new path.
        loadConfig();
    }

    private boolean loadConfig() throws ConfigExceptionBESA {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new FileInputStream(CONFIG_FILE));

            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            NodeList containerList = root.getElementsByTagName("Container");

            if (containerList.getLength() > 0) {
                Element containerElement = (Element) containerList.item(0);
                Container container = new Container();
                container.setAlias(containerElement.getAttribute("alias"));
                container.setPassword(Double.parseDouble(containerElement.getAttribute("password")));
                container.setIpaddress(containerElement.getAttribute("ipaddress"));

                NodeList environmentList = containerElement.getElementsByTagName("Environment");
                if (environmentList.getLength() > 0) {
                    Element environmentElement = (Element) environmentList.item(0);
                    Environment environment = new Environment();
                    environment.setRmitimeout(Long.parseLong(environmentElement.getAttribute("rmitimeout")));
                    environment.setSeneventattemps(Integer.parseInt(environmentElement.getAttribute("seneventattemps")));
                    environment.setSendeventtimeout(Long.parseLong(environmentElement.getAttribute("sendeventtimeout")));

                    NodeList remoteList = environmentElement.getElementsByTagName("Remote");
                    if (remoteList.getLength() > 0) {
                        Element remoteElement = (Element) remoteList.item(0);
                        Remote remote = new Remote();
                        remote.setRmiport(Integer.parseInt(remoteElement.getAttribute("rmiport")));
                        remote.setMcaddress(remoteElement.getAttribute("mcaddress"));
                        remote.setMcport(Integer.parseInt(remoteElement.getAttribute("mcport")));

                        environment.setRemote(remote);
                        container.setEnvironment(environment);
                    }

                    updateConfigFromXML(container);
                } else {
                    throw new ConfigExceptionBESA("Missing the Container tag.");
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ConfigExceptionBESA("Error reading the configuration file: " + e.getMessage());
        }

        return false;
    }

    private void updateConfigFromXML(Container xMLcontainer) throws ConfigExceptionBESA {
        aliasContainer = xMLcontainer.getAlias();
        passwordContainer = xMLcontainer.getPassword();
        ipaddress = xMLcontainer.getIpaddress();

        if (aliasContainer == null || aliasContainer.isEmpty()) {
            throw new ConfigExceptionBESA("Missing the property \"alias\" in the Container tag.");
        }
        if (passwordContainer == 0.0) {
            throw new ConfigExceptionBESA("Missing the property \"password\" in the Container tag.");
        }
        if (ipaddress == null || ipaddress.isEmpty()) {
            try {
                ipaddress = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException ex) {
                ipaddress = DEFAULT_IP_ADDRESS;
            }
        }

        Environment xMLEnvironment = xMLcontainer.getEnvironment();
        if (xMLEnvironment != null) {
            rMITimeout = xMLEnvironment.getRmitimeout();
            if (rMITimeout == 0) {
                rMITimeout = DEFAULT_RMI_TIMEOUT;
            }
            sendEventAttemps = xMLEnvironment.getSeneventattemps();
            if (sendEventAttemps == 0) {
                sendEventAttemps = DEFAULT_SEND_EVENT_ATTEMPS;
            }
            sendEventTimeout = xMLEnvironment.getSendeventtimeout();
            if (sendEventTimeout == 0) {
                sendEventTimeout = DEFAULT_SEND_EVENT_TIMEOUT;
            }

            Remote xMLRemote = xMLEnvironment.getRemote();
            if (xMLRemote != null) {
                rmiPort = xMLRemote.getRmiport();
                if (rmiPort == 0) {
                    throw new ConfigExceptionBESA("Missing the property \"rmiport\" in the remote tag.");
                }
                mcaddress = xMLRemote.getMcaddress();
                if (mcaddress == null || mcaddress.isEmpty()) {
                    throw new ConfigExceptionBESA("Missing the property \"mcaddress\" in the remote tag.");
                }
                mcport = xMLRemote.getMcport();
                if (mcport == 0) {
                    throw new ConfigExceptionBESA("Missing the property \"mcport\" in the remote tag.");
                }

                environmentCase = EnvironmentCase.REMOTE;
            } else {
                environmentCase = EnvironmentCase.LOCAL;
            }
        } else {
            sendEventAttemps = DEFAULT_SEND_EVENT_ATTEMPS;
            sendEventTimeout = DEFAULT_SEND_EVENT_TIMEOUT;
            environmentCase = EnvironmentCase.LOCAL;
        }
    }

    // Add other getter and setter methods for the ConfigBESA properties here
    /**
     * Sets the send event time-out.
     *
     * @param t Send event time-out.
     */
    public void setSendEventTimeout(long t) {
        this.sendEventTimeout = t;
    }

    /**
     * Sets the RMI time-out.
     *
     * @param t RMI time-out.
     */
    public void setRMITimeout(long t) {
        this.rMITimeout = t;
    }

    /**
     * Sets the send event attemps.
     *
     * @param t Send event attemps.
     */
    public void setSendEventAttemps(int t) {
        this.sendEventAttemps = t;
    }

    /**
     * Gets send event time-out.
     *
     * @return Send event time-out.
     */
    public long getSendEventTimeout() {
        return this.sendEventTimeout;
    }

    /**
     * Gets the RMI time out.
     *
     * @return RMI time out.
     */
    public long getRMITimeout() {
        return this.rMITimeout;
    }

    /**
     * Gets the send event attemps.
     *
     * @return Send event attemps.
     */
    public int getSendEventAttemps() {
        return this.sendEventAttemps;
    }

    /**
     * Gets the alias container.
     *
     * @return Alias container.
     */
    public String getAliasContainer() {
        return aliasContainer;
    }

    /**
     * Sets alias container.
     *
     * @param aliasContainer Alias container.
     */
    public void setAliasContainer(String aliasContainer) {
        this.aliasContainer = aliasContainer;
    }

    /**
     * Gets the IP Address.
     *
     * @return IP Address.
     */
    public String getIpaddress() {
        return ipaddress;
    }

    /**
     * Sets the IP Addess.
     *
     * @param ipaddress IP Address.
     */
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    /**
     * Gets the password container.
     *
     * @return Password container.
     */
    public double getPasswordContainer() {
        return passwordContainer;
    }

    /**
     * Sets the passsword container.
     *
     * @param passwordContainer
     */
    public void setPasswordContainer(double passwordContainer) {
        this.passwordContainer = passwordContainer;
    }

    /**
     * Gets the BAP locator address.
     *
     * @return BAP locator address.
     */
    public String getBaplocatoradd() {
        return baplocatoradd;
    }

    /**
     * Sets the BAP locator address.
     *
     * @param baplocatoradd BAP locator address.
     */
    public void setBaplocatoradd(String baplocatoradd) {
        this.baplocatoradd = baplocatoradd;
    }

    /**
     * Gets the BAP port.
     *
     * @return BAP port.
     */
    public int getBapport() {
        return bapport;
    }

    /**
     * Sets the BAP port.
     *
     * @param bapport BAP port.
     */
    public void setBapport(int bapport) {
        this.bapport = bapport;
    }

    /**
     * Gets the MC Address.
     *
     * @return MC Address.
     */
    public String getMcaddress() {
        return mcaddress;
    }

    /**
     * Sets the MC Address.
     *
     * @param mcaddress MC Address.
     */
    public void setMcaddress(String mcaddress) {
        this.mcaddress = mcaddress;
    }

    /**
     * Gets the MC port.
     *
     * @return MC port.
     */
    public int getMcport() {
        return mcport;
    }

    /**
     * Sets the MC port:
     *
     * @param mcport MC port.
     */
    public void setMcport(int mcport) {
        this.mcport = mcport;
    }

    /**
     * Gets the RMI port.
     *
     * @return RMI port.
     */
    public int getRmiPot() {
        return rmiPort;
    }

    /**
     * Sets the RMI port.
     *
     * @param rmiPot RMI port.
     */
    public void setRmiPot(int rmiPot) {
        this.rmiPort = rmiPot;
    }

    /**
     * Gets the environment type.
     *
     * @return environment type.
     */
    public EnvironmentCase getEnvironmentCase() {
        return environmentCase;
    }

    /**
     * Gets the RMI time-out.
     *
     * @return RMI time-out.
     */
    public long getrMITimeout() {
        return rMITimeout;
    }

    /**
     * Gets RMI port.
     *
     * @return RMI port.
     */
    public int getRmiPort() {
        return rmiPort;
    }

    /**
     * Gets the BPO port.
     *
     * @return BPO port.
     */
    public int getBpoPort() {
        return bpoPort;
    }

    /**
     * Sets the BPO Port.
     *
     * @param bpoPort BPO Port.
     */
    public void setBpoPort(int bpoPort) {
        this.bpoPort = bpoPort;
    }

    /**
     *
     * @return
     */
    public int getBloport() {
        return bloport;
    }

    /**
     *
     * @param bloport
     */
    public void setBloport(int bloport) {
        this.bloport = bloport;
    }
}
