/*
 * @(#)Environment.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved. Takina
 * and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Config;

/**
 * This class keeps the general configuration information of container 
 * environment.
 *
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since JDK1.4
 */
public class Environment {

    /**
     * Send event attemps
     * TODO Arreglar Ortografia.
     */
    protected int seneventattemps;
    /**
     * Send event timeout.
     */
    protected long sendeventtimeout;
    /**
     * RMI timeout.
     */
    protected long rmitimeout;
    /**
     * Remote tag.
     */
    protected Remote remote;
    /**
     * External tag.
     * TODO Cambiar nombre de clase.
     */
    protected Interop interop;
    /**
     * Mobile tag.
     */
    protected Mobile mobile;
    /**
     *
     */
    protected CE ce;

    /**
     * Gets the external tag information.
     * 
     * @return External tag information.
     */
    public Interop getInterop() {
        return interop;
    }

    /**
     * Gets the mobile tag information.
     * 
     * @return Mobile tag information.
     */
    public Mobile getMobile() {
        return mobile;
    }

    /**
     * 
     * @return 
     */
    public CE getCE() {
        return ce;
    }
    
    /**
     * Gets the remote tag information.
     * 
     * @return Remote tag information.
     */
    public Remote getRemote() {
        return remote;
    }

    /**
     * Gets the RMI timeout.
     * 
     * @return RMI timeout.
     */
    public long getRmitimeout() {
        return rmitimeout;
    }

    public void setSeneventattemps(int seneventattemps) {
        this.seneventattemps = seneventattemps;
    }

    public void setSendeventtimeout(long sendeventtimeout) {
        this.sendeventtimeout = sendeventtimeout;
    }

    public void setRmitimeout(long rmitimeout) {
        this.rmitimeout = rmitimeout;
    }

    public void setRemote(Remote remote) {
        this.remote = remote;
    }

    public void setInterop(Interop interop) {
        this.interop = interop;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public void setCe(CE ce) {
        this.ce = ce;
    }

    /**
     * Gets the send event timeout.
     * 
     * @return Send event timeout.
     */
    public long getSendeventtimeout() {
        return sendeventtimeout;
    }

    /**
     * Gets the send event attemps.
     * TODO Corregir ortografia.
     * @return Send event attemps.
     */
    public int getSeneventattemps() {
        return seneventattemps;
    }    
}
