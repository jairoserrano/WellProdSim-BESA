/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wpsMain.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jairo
 */
public class ReportBESA extends BESA.Log.ReportBESA{
    
    private static final Logger logger = LogManager.getLogger(ReportBESA.class);

    @Override
    protected void traceImp(Object message, long time) {
        logger.trace(message);
    }

    @Override
    protected void debugImp(Object message, long time) {
        logger.debug(message);
    }

    @Override
    protected void infoImp(Object message, long time) {
        logger.info(message);
    }

    @Override
    protected void warnImp(Object message, long time) {
        logger.warn(message);
    }

    @Override
    protected void errorImp(Object message, long time) {
        logger.error(message);
    }

    @Override
    protected void fatalImp(Object message, long time) {
        logger.fatal(message);
    }
    
}
