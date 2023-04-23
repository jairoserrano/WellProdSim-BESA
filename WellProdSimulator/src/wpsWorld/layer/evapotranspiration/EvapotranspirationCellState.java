package wpsWorld.layer.evapotranspiration;

import BESA.Log.ReportBESA;
import wpsWorld.automata.core.cell.LayerCellState;

/**
 * Concrete implementation of the evapotranspiration cell state
 */
public class EvapotranspirationCellState implements LayerCellState {

    private double evapotranspirationReference;

    /**
     *
     * @param evapotranspirationReference
     */
    public EvapotranspirationCellState(double evapotranspirationReference) {
        //ReportBESA.info("Next evapotranspiration state: " + evapotranspirationReference);
        this.evapotranspirationReference = evapotranspirationReference;
    }

    /**
     *
     */
    public EvapotranspirationCellState() {
    }

    /**
     *
     * @return
     */
    public double getEvapotranspirationReference() {
        return evapotranspirationReference;
    }

    /**
     *
     * @param evapotranspirationReference
     */
    public void setEvapotranspirationReference(double evapotranspirationReference) {
        this.evapotranspirationReference = evapotranspirationReference;
    }

}
