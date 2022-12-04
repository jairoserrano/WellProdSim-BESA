package BESA.World.layer.temperature;

import BESA.World.automata.core.cell.GenericWorldLayerCell;
import BESA.World.automata.core.cell.LayerCellState;

/**
 * Temperature cell concrete implementation
 */
public class TemperatureCell extends GenericWorldLayerCell<LayerCellState> {


    private String id;


    public TemperatureCell(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
