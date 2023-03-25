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
package wpsPeasant.Utils;

/**
 *
 * @author jairo
 */
public enum PeasantActivityType {

    /**
     *
     */
    IDENTIFY_PURPOSE("identify_purpose"),

    /**
     *
     */
    WORK("work"),

    /**
     *
     */
    MANAGE_LAND("manage_land"),

    /**
     *
     */
    MANAGE_FINANCES("manage_finances"),

    /**
     *
     */
    PAY_DEBTS("pay_debts"),

    /**
     *
     */
    SELL_PRODUCTS("sell_products"),

    /**
     *
     */
    MAINTAIN_EQUIPMENT("maintain_equipment"),

    /**
     *
     */
    CULTIVATE("cultivate"),

    /**
     *
     */
    PREPARE_LAND("prepare_land"),

    /**
     *
     */
    HARVEST("harvest"),

    /**
     *
     */
    PROCESS("process"),

    /**
     *
     */
    IMPROVE_SKILLS("improve_skills"),

    /**
     *
     */
    IRRIGATE("irrigate"),

    /**
     *
     */
    APPLY_PESTICIDES("apply_pesticides"),

    /**
     *
     */
    APPLY_FERTILIZERS("apply_fertilizers"),

    /**
     *
     */
    SEARCH_SUPPLIES("search_supplies"),

    /**
     *
     */
    SEEK_FUNDING("seek_funding"),

    /**
     *
     */
    FIND_TOOLS("find_tools"),

    /**
     *
     */
    COLLABORATE("collaborate"),

    /**
     *
     */
    WORK_FOR_EXTERNALS("work_for_externals"),

    /**
     *
     */
    CARE_FOR_ANIMALS("care_for_animals"),

    /**
     *
     */
    SPEND_TIME_WITH_FAMILY("spend_time_with_family"),

    /**
     *
     */
    COMMUNICATE("communicate"),

    /**
     *
     */
    REST("rest");

    private String type;

    private PeasantActivityType(String s) {
        type = s;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param tipo
     */
    public void setType(String tipo) {
        this.type = tipo;
    }
}
