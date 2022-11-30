package wpsMain.world.helper;

import org.json.JSONArray;
import org.json.JSONObject;
import wpsMain.world.layer.data.MonthData;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import wpsMain.util.ReportBESA;

public class MonthlyDataLoader {

    public static List<MonthData> loadMonthlyDataFile(String dataFileLocation) throws IOException {
        ReportBESA.info(dataFileLocation);
        InputStream in = new FileInputStream(dataFileLocation);
        //ClassLoader classLoader = MonthlyDataLoader.class.getClassLoader();
        //File jsonFile = new File(classLoader.getResource(dataFileLocation).getFile());
        String jsonContent = new String(in.readAllBytes());//Files.readAllBytes(Paths.get(jsonFile.toURI())), StandardCharsets.UTF_8);
        return jsonToMonthlyData(jsonContent);
    }

    private static List<MonthData> jsonToMonthlyData(String jsonContent) {
        JSONArray radiationJson = new JSONArray(jsonContent);
        ArrayList<MonthData> monthlyData = new ArrayList<>();
        ReportBESA.info("Cargando datos");
        radiationJson.forEach(item -> {
            JSONObject currentObject = (JSONObject) item;
            MonthData monthData = new MonthData();
            monthData.setAverage(currentObject.getDouble("average"));
            monthData.setMaxValue(currentObject.getDouble("maxValue"));
            monthData.setMinValue(currentObject.getDouble("minValue"));
            monthData.setStandardDeviation(currentObject.getDouble("standardDeviation"));
            monthlyData.add(monthData);
        });
        return monthlyData;
    }
}
