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

import java.util.Map;
import rational.data.InfoData;

/**
 *
 * @author jairo
 */
public class SensorData extends InfoData{
    
    private SensorDataType dataType;
    private String infoReceived=null;
    private Map<String, Object> dataP=null;
    private Map<String, Object> dataPE=null;
    private int ack;
    private boolean hasEmo;

    public SensorData() {
        super(null);
        hasEmo=false;
    }

    public SensorDataType getDataType() {
        return dataType;
    }

    public void setDataType(SensorDataType dataType) {
        this.dataType = dataType;
    }

    public Map<String, Object> getDataP() {
        return dataP;
    }

    public void setDataP(Map<String, Object> dataP) {
        this.dataP = dataP;
    }

    public Map<String, Object> getDataPE() {
        return dataPE;
    }

    public void setDataPE(Map<String, Object> dataPE) {
        this.dataPE = dataPE;
    }

    public String getInfoReceived() {
        return infoReceived;
    }

    public void setInfoReceived(String infoReceived) {
        this.infoReceived = infoReceived;
    }

    public int getAck() {
        return ack;
    }

    public void setAck(int ack) {
        this.ack = ack;
    }

    public boolean isHasEmo() {
        return hasEmo;
    }

    public void setHasEmo(boolean hasEmo) {
        this.hasEmo = hasEmo;
    }

    @Override
    public String toString() {
        return "SensorData{" + "dataType=" + dataType + ", infoReceived=" + infoReceived + ", dataP=" + dataP + ", dataPE=" + dataPE + ", ack=" + ack + ", hasEmo=" + hasEmo + '}';
    }
    
}
