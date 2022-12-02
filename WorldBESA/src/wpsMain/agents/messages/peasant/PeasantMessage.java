package wpsMain.agents.messages.peasant;

import BESA.Kernel.Agent.Event.DataBESA;

public class PeasantMessage extends DataBESA {
    private String peasantId;
    private String payload;
    private PeasantMessageType peasantMessageType;
    private String date;
    private String simpleMessage;

    public PeasantMessage(PeasantMessageType peasantMessageType, String peasantId, String payload) {
        this.peasantId = peasantId;
        this.payload = payload;
        this.peasantMessageType = peasantMessageType;
    }

    public PeasantMessage(String simpleMessage) {
        this.simpleMessage = simpleMessage;
    }

    public String getSimpleMessage() {
        return simpleMessage;
    }

    public String getPeasantId() {
        return peasantId;
    }

    public void setPeasantId(String peasantId) {
        this.peasantId = peasantId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public PeasantMessageType getPeasantMessageType() {
        return peasantMessageType;
    }

    public void setPeasantMessageType(PeasantMessageType peasantMessageType) {
        this.peasantMessageType = peasantMessageType;
    }
}
