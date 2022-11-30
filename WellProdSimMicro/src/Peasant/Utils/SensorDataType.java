package Peasant.Utils;

/**
 *
 * @author jairo
 */
public enum SensorDataType {
    ACTIVITY("activity"),
    EMOTIONS("emotions"),
    INTERACTION("interaction"),
    PEASANT("peasant"),
    PURPOSE("purpose");
    private final String identif;

    private SensorDataType(String i) {
        identif = i;
    }

    public static SensorDataType getFromId(String ident) {
        SensorDataType ret = null;
        for (SensorDataType sdt : values()) {
            if (sdt.identif.equals(ident)) {
                ret = sdt;
                break;
            }
        }
        return ret;
    }
}
