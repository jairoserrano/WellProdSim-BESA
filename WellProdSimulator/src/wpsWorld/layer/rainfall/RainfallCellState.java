package wpsWorld.layer.rainfall;

import BESA.Log.ReportBESA;
import wpsWorld.automata.core.cell.LayerCellState;

/**
 * Concrete implementation of the rainfall cell state
 */
public class RainfallCellState implements LayerCellState {

    private double rainfall;

    /**
     *
     * @param rainfall
     */
    public RainfallCellState(double rainfall) {
        //ReportBESA.info("New rainfall state: " + rainfall);
        this.rainfall = rainfall;
    }

    /**
     *
     * @return
     */
    public double getRainfall() {
        return rainfall;
    }

    /**
     *
     * @param rainfall
     */
    public void setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }
}
