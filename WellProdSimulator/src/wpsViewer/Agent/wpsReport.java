/**
 * ==========================================================================
 * __      __ _ __   ___  *    WellProdSim                                  *
 * \ \ /\ / /| '_ \ / __| *    @version 1.0                                 *
 *  \ V  V / | |_) |\__ \ *    @since 2023                                  *
 *   \_/\_/  | .__/ |___/ *                                                 *
 *           | |          *    @author Jairo Serrano                        *
 *           |_|          *    @author Enrique Gonzalez                     *
 * ==========================================================================
 * Social Simulator used to estimate productivity and well-being of peasant *
 * families. It is event oriented, high concurrency, heterogeneous time     *
 * management and emotional reasoning BDI.                                  *
 * ==========================================================================
 */
package wpsViewer.Agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class wpsReport {

    private static final Logger logger = LoggerFactory.getLogger(wpsReport.class);

    private wpsReport() {
        
    }

    public static void trace(Object message) {
        logger.trace(formatMessage(message));
    }

    public static void debug(Object message) {
        logger.debug(formatMessage(message));
    }

    public static void info(Object message) {
        logger.info(formatMessage(message));
    }

    public static void warn(Object message) {
        logger.warn(formatMessage(message));
    }

    public static void error(Object message) {
        logger.error(formatMessage(message));
    }

    public static void fatal(Object message) {
        logger.error(formatMessage(message));
    }

    private static String formatMessage(Object message) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String callingClassName = stackTrace[3].getClassName();
        String callingMethodName = stackTrace[3].getMethodName();
        String simpleClassName = callingClassName.substring(callingClassName.lastIndexOf('.') + 1);
        //if (simpleClassName.contains("DoVitalsTask")) {
        //    return Thread.currentThread().getName()
       //             +"\n-------------------------------------------------------------------------------------------------\n" 
       //             + String.format("%23s:%-20s %-65s", simpleClassName, callingMethodName, message);
       // } else {
            return Thread.currentThread().getName() + " " + String.format("%23s:%-20s %-65s", simpleClassName, callingMethodName, message);
       // }
    }

}
