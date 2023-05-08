/*
 * @(#)Remote.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved. Takina
 * and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Config;

/**
 * This class keeps the general configuration information of remote container.
 *
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since JDK1.4
 */
public class Remote {

    /**
     * RMI Port.
     */
    protected int rmiport;

    public void setRmiport(int rmiport) {
        this.rmiport = rmiport;
    }

    public void setMcaddress(String mcaddress) {
        this.mcaddress = mcaddress;
    }

    public void setMcport(int mcport) {
        this.mcport = mcport;
    }
    /**
     * Multicast address.
     */
    protected String mcaddress;
    /**
     * Multicast port.
     */
    protected int mcport;

    /**
     * Gets the multicast address.
     * 
     * @return Multicast address.
     */
    public String getMcaddress() {
        return mcaddress;
    }

    /**
     * Gets the multicast port.
     * 
     * @return Multicast port.
     */
    public int getMcport() {
        return mcport;
    }

    /**
     * Gets the RMI port.
     * 
     * @return RMI port.
     */
    public int getRmiport() {
        return rmiport;
    }
}
