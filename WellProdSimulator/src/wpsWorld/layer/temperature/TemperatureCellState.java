package wpsWorld.layer.temperature;

import BESA.Log.ReportBESA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wpsWorld.automata.core.cell.LayerCellState;

/**
 * Temperature cell state concrete implementation
 */
public class TemperatureCellState implements LayerCellState {

    private double temperature;

    /**
     *
     * @param temperature
     */
    public TemperatureCellState(double temperature) {
        //ReportBESA.info("New temperature state: " + temperature);
        this.temperature = temperature;
    }

    /**
     *
     * @return
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     *
     * @param temperature
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
