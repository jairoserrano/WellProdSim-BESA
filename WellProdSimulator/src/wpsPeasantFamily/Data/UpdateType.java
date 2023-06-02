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
package wpsPeasantFamily.Data;

/**
 *
 */
public enum UpdateType {

    /**
     *
     */
    NEW_DAY,
    
    /**
     *
     */
    USED_NEW_DAY,

    /**
     *
     */
    USE_TIME,
    
    /**
     * 
     */
    SHOW_STATUS,
    
    /**
     * 
     */
    CROP_CHECKED_TODAY,
    
    /**
     * 
     */
    MONEY_ORIGIN,
    
    /**
     * 
     */
    DAILY_DISCOUNT,
    
    /**
     * 
     */
    CHANGE_SEASON,
    
    /**
     * 
     */
    CHANGE_CROP_CARE
}
