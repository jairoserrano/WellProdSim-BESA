package wpsWorld.layer.crop.cell.rice;

import wpsWorld.Helper.Soil;
import wpsWorld.layer.crop.cell.CropCell;
import wpsWorld.layer.disease.DiseaseCell;

/**
 * Rice crop cell implementation
 */
public class RiceCell extends CropCell<RiceCellState> {

    private String id;

    /**
     *
     * @param cropFactor_ini
     * @param cropFactor_mid
     * @param cropFactor_end
     * @param degreeDays_mid
     * @param degreeDays_end
     * @param cropArea
     * @param maximumRootDepth
     * @param depletionFraction
     * @param soilType
     * @param isActive
     * @param diseaseCell
     * @param id
     * @param agentPeasantId
     */
    public RiceCell(
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
