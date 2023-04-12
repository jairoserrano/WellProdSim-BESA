package BESA.World.layer.evapotranspiration;

import BESA.Log.ReportBESA;
import BESA.World.automata.core.cell.LayerCellState;

/**
 * Concrete implementation of the evapotranspiration cell state
 */
public class EvapotranspirationCellState implements LayerCellState {

    private double evapotranspirationReference;

    public EvapotranspirationCellState(double evapotranspirationReference) {
        //ReportBESA.info("Next evapotranspiration state: " + evapotranspirationReference);
        this.evapotranspirationReference = evapotranspirationReference;
    }

    public EvapotranspirationCellState() {
    }

    public double getEvapotranspirationReference() {
        return evapotranspirationReference;
    }

    public void setEvapotranspirationReference(double evapotranspirationReference) {
        this.evapotranspirationReference = evapotranspirationReference;
    }

}
