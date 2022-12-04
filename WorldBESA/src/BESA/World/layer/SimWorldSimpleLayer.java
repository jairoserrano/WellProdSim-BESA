package BESA.World.layer;

import BESA.World.automata.core.cell.LayerCell;
import BESA.World.automata.core.layer.GenericWorldLayerUniqueCell;
import BESA.World.helper.WorldConfiguration;
import BESA.World.helper.MonthlyDataLoader;
import BESA.World.layer.data.MonthData;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Abstract implementation for the layers, used for this specific world simulation
 *
 * @param <C> type of cell
 */
public abstract class SimWorldSimpleLayer<C extends LayerCell> extends GenericWorldLayerUniqueCell<C> {

    protected List<MonthData> monthlyData;

    protected Random random;

    protected WorldConfiguration worldConfig = WorldConfiguration.getPropsInstance();

    public SimWorldSimpleLayer(String dataFile) {
        this.loadYearDataFromFile(dataFile);
        this.random = new Random();
    }

    protected double calculateGaussianFromMonthData(int month) {
        MonthData monthData = this.monthlyData.get(month);
        return this.random.nextGaussian() * monthData.getStandardDeviation() + monthData.getAverage();
    }

    protected void loadYearDataFromFile(String dataFile) {
        try {
            this.monthlyData = MonthlyDataLoader.loadMonthlyDataFile(dataFile);
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception.getMessage());
        }
    }

}
