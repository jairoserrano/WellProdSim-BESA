package BESA.World.layer.shortWaveRadiation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import BESA.World.automata.core.cell.LayerCellState;

/**
 * Concrete implementation of the short wave radiation cell state
 */
public class ShortWaveRadiationCellState implements LayerCellState {

    private static final Logger logger = LogManager.getLogger(ShortWaveRadiationCellState.class);
    private double shortWaveRadiation;


    public ShortWaveRadiationCellState(double shortWaveRadiation) {
        logger.info("New short wave radiation state: " + shortWaveRadiation);
        this.shortWaveRadiation = shortWaveRadiation;
    }

    public ShortWaveRadiationCellState() {
    }

    public double getShortWaveRadiation() {
        return shortWaveRadiation;
    }

    public void setShortWaveRadiation(double shortWaveRadiation) {
        this.shortWaveRadiation = shortWaveRadiation;
    }
}
