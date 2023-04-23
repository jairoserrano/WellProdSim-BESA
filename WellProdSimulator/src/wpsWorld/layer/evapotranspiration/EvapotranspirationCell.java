package wpsWorld.layer.evapotranspiration;

import wpsWorld.automata.core.cell.GenericWorldLayerCell;

/**
 * Concrete implementation for the evapotranspiration cell
 */
public class EvapotranspirationCell extends GenericWorldLayerCell<EvapotranspirationCellState> {

    private String id;

    /**
     *
     * @param id
     */
    public EvapotranspirationCell(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

}
