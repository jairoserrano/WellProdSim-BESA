package BESA.World.layer.crop.cell.root;

import BESA.World.helper.Soil;
import BESA.World.layer.crop.cell.CropCell;
import BESA.World.layer.disease.DiseaseCell;

/**
 * Root cell implementation
 */
public class RootCell extends CropCell<RootCellState> {

    private String id;

    public RootCell(
            double cropFactor_ini,
            double cropFactor_mid,
            double cropFactor_end,
            double degreeDays_mid,
            double degreeDays_end,
            int cropArea,
            double maximumRootDepth,
            double depletionFraction,
            Soil soilType,
            boolean isActive,
            DiseaseCell diseaseCell,
            String id,
            String agentPeasantId) {
        super(cropFactor_ini, cropFactor_mid, cropFactor_end, degreeDays_mid, degreeDays_end, cropArea, maximumRootDepth, depletionFraction, soilType, isActive, diseaseCell, agentPeasantId);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }


}
