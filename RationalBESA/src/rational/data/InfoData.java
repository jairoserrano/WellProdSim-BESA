package rational.data;

import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;

public class InfoData extends DataBESA{

    String message;
    
    public InfoData(String message) {
        this.message = message;
    }

    @Override
    public void getDataBesaFromString(String str) throws KernellAgentEventExceptionBESA {
        this.message = str;
    }

    @Override
    public String getStringFromDataBesa() throws KernellAgentEventExceptionBESA {
        return message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
