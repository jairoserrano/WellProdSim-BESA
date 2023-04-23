package wpsWorld.layer.disease;

import wpsWorld.automata.core.cell.LayerCellState;

/**
 * Concrete disease cell state implementation
 */
public class DiseaseCellState implements LayerCellState {

    private double probabilityDisease;

    private boolean infected;

    /**
     *
     */
    public DiseaseCellState() {
    }

    /**
     *
     * @return
     */
    public double getCurrentProbabilityDisease() {
        return probabilityDisease;
    }

    /**
     *
     * @param currentProbabilityDisease
     */
    public void setCurrentProbabilityDisease(double currentProbabilityDisease) {
        this.probabilityDisease = currentProbabilityDisease;
    }

    /**
     *
     * @return
     */
    public double getProbabilityDisease() {
        return probabilityDisease;
    }

    /**
     *
     * @param probabilityDisease
     */
    public void setProbabilityDisease(double probabilityDisease) {
        this.probabilityDisease = probabilityDisease;
    }

    /**
     *
     * @return
     */
    public boolean isInfected() {
        return infected;
    }

    /**
     *
     * @param infected
     */
    public void setInfected(boolean infected) {
        this.infected = infected;
    }
}
