/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BESA.Log;

/**
 *
 * @author User
 */
public class LogConfig {

    /**
     *
     */
    protected Boolean trace;
    /**
     *
     */
    protected Boolean debug;
    /**
     *
     */
    protected Boolean info;
    /**
     *
     */
    protected Boolean warn;
    /**
     *
     */
    protected Boolean error;
    /**
     *
     */
    protected Boolean fatal;
    /**
     *
     */
    protected Boolean logmanager;
    /**
     * 
     */
    protected String dateformat;

    public Boolean getDebug() {
        return debug;
    }

    public Boolean getError() {
        return error;
    }

    public Boolean getFatal() {
        return fatal;
    }

    public Boolean getInfo() {
        return info;
    }

    public Boolean getLogmanager() {
        return logmanager;
    }

    public Boolean getTrace() {
        return trace;
    }

    public Boolean getWarn() {
        return warn;
    }

    public String getDateformat() {
        return dateformat;
    }
}
