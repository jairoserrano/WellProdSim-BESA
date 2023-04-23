package wpsWorld.layer.shortWaveRadiation;

import BESA.Log.ReportBESA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wpsWorld.automata.core.cell.LayerCellState;

/**
 * Concrete implementation of the short wave radiation cell state
 */
public class ShortWaveRadiationCellState implements LayerCellState {

    private double shortWaveRadiation;

    /**
     *
     * @param shortWaveRadiation
     */
    public ShortWaveRadiationCellState(double shortWaveRadiation) {
        //ReportBESA.info("New short wave radiation state: " + shortWaveRadiation);
        this.shortWaveRadiation = shortWaveRadiation;
    }

    /**
     *
     */
    public ShortWaveRadiationCellState() {
    }

    /**
     *
     * @return
     */
    public double getShortWaveRadiation() {
        return shortWaveRadiation;
    }

    /**
     *
     * @param shortWaveRadiation
     */
    public void setShortWaveRadiation(double shortWaveRadiation) {
        this.shortWaveRadiation = shortWaveRadiation;
    }
}
