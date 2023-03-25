/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wpsPeasant.EmotionalModel;

/**
 *
 * @author jairo
 */
public enum EmotionalObjectType {

    /**
     *
     */
    PEASANT("PEASANT", EmotionalConfig.Objects.Valioso),

    /**
     *
     */
    LAND("LAND", EmotionalConfig.Objects.Valioso),

    /**
     *
     */
    FARMING("FARMING", EmotionalConfig.Objects.Valioso),

    /**
     *
     */
    HARVEST("HARVEST", EmotionalConfig.Objects.Importante),

    /**
     *
     */
    CROP("CROP", EmotionalConfig.Objects.Importante),

    /**
     *
     */
    BUGS("BUGS", EmotionalConfig.Objects.Repulsivo),

    /**
     *
     */
    NULL("",EmotionalConfig.Objects.Indiferente);

    private final String emoType;
    private final EmotionalConfig.Objects config;

    private EmotionalObjectType(String emoType, EmotionalConfig.Objects config) {
        this.emoType = emoType;
        this.config = config;
    }

    /**
     *
     * @param ident
     * @return
     */
    public static EmotionalObjectType getFromId(String ident) {
        EmotionalObjectType ret = null;
        for (EmotionalObjectType sdt : values()) {
            if (sdt.emoType.equalsIgnoreCase(ident)) {
                ret = sdt;
                break;
            }
        }
        return ret;
    }

    /**
     *
     * @return
     */
    public String getEmoType() {
        return emoType;
    }

    /**
     *
     * @return
     */
    public EmotionalConfig.Objects getConfigEnum() {
        return config;
    }

    /**
     *
     * @return
     */
    public String getConfig() {
        return config.toString();
    }
}
