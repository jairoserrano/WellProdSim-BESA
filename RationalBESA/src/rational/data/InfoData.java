package rational.data;

import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;

/**
 * The InfoData class extends DataBESA and represents a simple message container
 * for communication between rational agents.
 */
public class InfoData extends DataBESA {

    // The message contained in the InfoData object
    private String message;

    /**
     * Constructor that initializes the message field.
     *
     * @param message The message to store in the object.
     */
    public InfoData(String message) {
        this.message = message;
    }

    /**
     * Updates the message field with the given string.
     *
     * @param message The string to set as the new message.
     * @throws KernellAgentEventExceptionBESA if any error occurs during the
     * update.
     */
    @Override
    public void getDataBesaFromString(String message) throws KernellAgentEventExceptionBESA {
        this.message = message;
    }

    /**
     * Returns the message as a string.
     *
     * @return The message as a string.
     * @throws KernellAgentEventExceptionBESA if any error occurs during the
     * conversion.
     */
    @Override
    public String getStringFromDataBesa() throws KernellAgentEventExceptionBESA {
        return message;
    }

    /**
     * Gets the message.
     *
     * @return The message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message The new message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
