package wpsWorld.Helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import wpsViewer.Agent.wpsReport;
import wpsWorld.layer.data.MonthData;

/**
 *
 * @author jairo
 */
public class MonthlyDataLoader {

    /**
     *
     * @param dataFileLocation
     * @return
     * @throws IOException
     */
    public static List<MonthData> loadMonthlyDataFile(String dataFileLocation) throws IOException {
        //wpsReport.info(dataFileLocation);
        InputStream in = new FileInputStream(dataFileLocation);
        //ClassLoader classLoader = MonthlyDataLoader.class.getClassLoader();
        //File jsonFile = new File(classLoader.getResource(dataFileLocation).getFile());
        String jsonContent = new String(in.readAllBytes());//Files.readAllBytes(Paths.get(jsonFile.toURI())), StandardCharsets.UTF_8);
        return jsonToMonthlyData(jsonContent, dataFileLocation);
    }

    private static List<MonthData> jsonToMonthlyData(String jsonContent, String dataFileLocation) {
        JSONArray radiationJson = new JSONArray(jsonContent);
        ArrayList<MonthData> monthlyData = new ArrayList<>();
        radiationJson.forEach(item -> {
            JSONObject currentObject = (JSONObject) item;
            MonthData monthData = new MonthData();
            monthData.setAverage(currentObject.getDouble("average"));
            monthData.setMaxValue(currentObject.getDouble("maxValue"));
            monthData.setMinValue(currentObject.getDouble("minValue"));
            monthData.setStandardDeviation(currentObject.getDouble("standardDeviation"));
            monthlyData.add(monthData);
        });
        wpsReport.info("Cargando " + dataFileLocation + " ... OK");
        return monthlyData;
    }
}
