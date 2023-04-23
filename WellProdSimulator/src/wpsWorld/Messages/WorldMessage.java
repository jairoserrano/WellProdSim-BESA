package wpsWorld.Messages;

import BESA.Kernel.Agent.Event.DataBESA;

/**
 *
 * @author jairo
 */
public class WorldMessage extends DataBESA {
    private WorldMessageType worldMessageType;
    private String cropId;
    private String peasantAgentId;
    private String payload;
    private String date;

    /**
     *
     * @param worldMessageType
     * @param cropId
     * @param date
     * @param peasantAgentId
     */
    public WorldMessage(WorldMessageType worldMessageType, String cropId, String date, String peasantAgentId) {
        this.worldMessageType = worldMessageType;
        this.cropId = cropId;
        this.date = date;
        this.peasantAgentId = peasantAgentId;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "---> WorldMessage{" + "worldMessageType=" + worldMessageType + ", cropId=" + cropId + ", peasantAgentId=" + peasantAgentId + ", payload=" + payload + ", date=" + date + "}";
    }

    /**
     *
     * @return
     */
    public String getPeasantAgentId() {
        return peasantAgentId;
    }

    /**
     *
     * @param peasantAgentId
     */
    public void setPeasantAgentId(String peasantAgentId) {
        this.peasantAgentId = peasantAgentId;
    }

    /**
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public WorldMessageType getWorldMessageType() {
        return worldMessageType;
    }

    /**
     *
     * @param worldMessageType
     */
    public void setWorldMessageType(WorldMessageType worldMessageType) {
        this.worldMessageType = worldMessageType;
    }

    /**
     *
     * @return
     */
    public String getCropId() {
        return cropId;
    }

    /**
     *
     * @param cropId
     */
    public void setCropId(String cropId) {
        this.cropId = cropId;
    }

    /**
     *
     * @return
     */
    public String getPayload() {
        return payload;
    }

    /**
     *
     * @param payload
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }
}
