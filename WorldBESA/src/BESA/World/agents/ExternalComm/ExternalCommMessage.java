package BESA.World.agents.ExternalComm;

import BESA.Kernel.Agent.Event.DataBESA;

public class ExternalCommMessage extends DataBESA {
    private String peasantId;
    private String payload;
    private ExternalCommMessageType peasantMessageType;
    private String date;
    private String simpleMessage;

    public ExternalCommMessage(ExternalCommMessageType peasantMessageType, String peasantId, String payload) {
        this.peasantId = peasantId;
        this.payload = payload;
        this.peasantMessageType = peasantMessageType;
    }

    public ExternalCommMessage(String simpleMessage) {
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

    public ExternalCommMessageType getPeasantMessageType() {
        return peasantMessageType;
    }

    public void setPeasantMessageType(ExternalCommMessageType peasantMessageType) {
        this.peasantMessageType = peasantMessageType;
    }
}
