package rational.mapping;

import rational.data.InfoData;

/**
 *
 * The Believes interface represents a contract for managing
 *
 * and updating the belief system of a rational agent.
 */
public interface Believes {

    /**
     * Updates the belief system with new information from the given InfoData
     * object.
     *
     * @param si The InfoData object containing new information.
     * @return true if the belief system was successfully updated, false
     * otherwise.
     */
    public boolean update(InfoData si);

    /**
     * Clones the current belief system, creating a new instance with the same
     * state.
     *
     * @return A cloned instance of the belief system.
     * @throws Exception if an error occurs during the cloning process.
     */
    public Believes clone() throws Exception;
}
