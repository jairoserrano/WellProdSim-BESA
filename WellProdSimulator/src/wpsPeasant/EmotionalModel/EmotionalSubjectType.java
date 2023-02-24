/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wpsPeasant.EmotionalModel;

/**
 *
 * @author jairo
 */
public enum EmotionalSubjectType {
    PEASANT("PEASANT", EmotionalConfig.People.Amigo), FAMILIA("FAMILIA", EmotionalConfig.People.Amigo);

    private final String emoType;
    private EmotionalConfig.People config;

    private EmotionalSubjectType(String emoType, EmotionalConfig.People config) {
        this.emoType = emoType;
        this.config = config;
    }

    public EmotionalConfig.People getConfigEnum() {
        return config;
    }

    public String getConfig() {
        return config.toString();
    }

    public static EmotionalSubjectType getFromId(String ident) {
        EmotionalSubjectType ret = null;
        for (EmotionalSubjectType sdt : values()) {
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
}
