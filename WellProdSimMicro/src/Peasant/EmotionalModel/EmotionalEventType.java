/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant.EmotionalModel;

/**
 *
 * @author jairo
 */
public enum EmotionalEventType {
    HARVEST("HARVEST", EmotionalConfig.Events.Deseable),
    FARMING("FARMING", EmotionalConfig.Events.Deseable),
    RESTING("RESTING", EmotionalConfig.Events.Deseable),
    BURN("BURN", EmotionalConfig.Events.Indeseable);

    private final String emoType;
    private final EmotionalConfig.Events config;

    private EmotionalEventType(String emoType, EmotionalConfig.Events config) {
        this.emoType = emoType;
        this.config = config;
    }

    public static EmotionalEventType getFromId(String ident) {
        EmotionalEventType ret = null;
        for (EmotionalEventType sdt : values()) {
            if (sdt.emoType.equalsIgnoreCase(ident)) {
                ret = sdt;
                break;
            }
        }
        return ret;
    }

    public String getEmoType() {
        return emoType;
    }

    public EmotionalConfig.Events getConfigEnum() {
        return config;
    }

    public String getConfig() {
        return config.toString();
    }
}
