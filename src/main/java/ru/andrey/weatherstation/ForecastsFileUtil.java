package ru.andrey.weatherstation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

public class ForecastsFileUtil {

    public static void writeForecastToFile (Forecast forecast, File file) {
        try (Writer writer  = new FileWriter(file, true)) {
            writer.write("Date: " + forecast.getDate() + "\n");
            writer.write("Temperature: " + forecast.getTemperature() + "\n");
            writer.write("Humidity: " + forecast.getHumidity() + "\n");
            writer.write("Wind Speed: " + forecast.getWindSpeed() + "\n");
            if (forecast.isPrecise()) {
                writer.write("The forecast is precise");
            } else {
                writer.write("The forecast is not precise");
            }
            writer.write("-----");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
