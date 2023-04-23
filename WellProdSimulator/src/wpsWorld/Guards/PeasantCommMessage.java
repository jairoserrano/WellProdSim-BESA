package wpsWorld.Guards;

import BESA.Kernel.Agent.Event.DataBESA;

/**
 *
 * @author jairo
 */
public class PeasantCommMessage extends DataBESA {
    private String peasantId;
    private String payload;
    private PeasantCommMessageType peasantMessageType;
    private String date;
    private String simpleMessage;

    /**
     *
     * @param peasantMessageType
     * @param peasantId
     * @param payload
     */
    public PeasantCommMessage(PeasantCommMessageType peasantMessageType, String peasantId, String payload) {
        this.peasantId = peasantId;
        this.payload = payload;
        this.peasantMessageType = peasantMessageType;
    }

    /**
     *
     * @param simpleMessage
     */
    public PeasantCommMessage(String simpleMessage) {
        this.simpleMessage = simpleMessage;
    }

    /**
     *
     * @return
     */
    public String getSimpleMessage() {
        return simpleMessage;
    }

    /**
     *
     * @return
     */
    public String getPeasantId() {
        return peasantId;
    }

    /**
     *
     * @param peasantId
     */
    public void setPeasantId(String peasantId) {
        this.peasantId = peasantId;
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

    /**
     *
     * @return
     */
    public PeasantCommMessageType getPeasantMessageType() {
        return peasantMessageType;
    }

    /**
     *
     * @param peasantMessageType
     */
    public void setPeasantMessageType(PeasantCommMessageType peasantMessageType) {
        this.peasantMessageType = peasantMessageType;
    }
}
