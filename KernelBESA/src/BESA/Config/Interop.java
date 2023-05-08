/*
 * @(#)Interop.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved. Takina
 * and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Config;


/**
 * This class keeps the general configuration information of extern container.
 * TODO Cambiar por extern.
 *
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since JDK1.4
 */
public class Interop {

    /**
     * RMI Port.
     */
    protected int rmiport;
    /**
     * Multicast address.
     */
    protected String mcaddress;
    /**
     * Multicast port.
     */
    protected int mcport;
    /**
     * BAP port.
     */
    protected int bapport;
    /**
     * BAP locator address.
     */
    protected String baplocatoradd;
    /**
     * blo port.
     */
    protected int bloport;
    /**
     * BPO port.
     */
    protected int bpoport;

    /**
     * Gets the BAP locator address.
     * 
     * @return BAP locator address.
     */
    public String getBaplocatoradd() {
        return baplocatoradd;
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

    /**
     * Gets the BPO port.
     * 
     * @return BPO port.
     */
    public int getBpoport() {
        return bpoport;
    }

    /**
     * Get bap locator port.
     * @return Bap locator port.
     */
    public int getBloport() {
        return bloport;
    }
}
