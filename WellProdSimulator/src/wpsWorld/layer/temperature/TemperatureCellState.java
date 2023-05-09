package wpsWorld.layer.temperature;

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
        //wpsReport.info("New temperature state: " + temperature);
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
