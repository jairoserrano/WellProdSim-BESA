/*
 * @(#)Container.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved. Takina
 * and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Config;


/**
 * This class keeps the general configuration information of BESA container.
 *
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since JDK1.0
 */
public class Container {

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setPassword(double password) {
        this.password = password;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * Alias container.
     */
    protected String alias;
    /**
     * Password container.
     */
    protected double password;
    /**
     * IP Address container.
     */
    protected String ipaddress;
    /**
     * Tag where will keep the environment information.
     */
    protected Environment environment;

    /**
     * Gets the alias container.
     *
     * @return Alias container.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Gets the tag environment.
     *
     * @return Tag environment
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * Gets the IP address.
     * 
     * @return IP address.
     */
    public String getIpaddress() {
        return ipaddress;
    }

    /**
     * Gets the password.
     * 
     * @return Password.
     */
    public double getPassword() {
        return password;
    }
}
