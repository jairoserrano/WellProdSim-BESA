package BESA.World.layer.rainfall;

import BESA.Log.ReportBESA;
import BESA.World.automata.core.cell.LayerCellState;

/**
 * Concrete implementation of the rainfall cell state
 */
public class RainfallCellState implements LayerCellState {

    private double rainfall;

    public RainfallCellState(double rainfall) {
        //ReportBESA.info("New rainfall state: " + rainfall);
        this.rainfall = rainfall;
    }

    public double getRainfall() {
        return rainfall;
    }

    public void setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }
}
