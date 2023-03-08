package ru.andrey.weatherstation;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RadarReadingsFileUtil {

    //дата, uid радара, тип параметра, значение параметра - презюмирую, что каждая запись начинается с новой строчки
    public static List<RadarReading> makeListOfRadarReadings(File file) {
        List<String> listOfCSVformatedRadarReadings = readRadarReadingsFromFile(file);
        List<RadarReading> listOfRadarReadings = new ArrayList<>();
            for (String string : listOfCSVformatedRadarReadings) {
                String[] parts = string.split(",");

                String dateInString = parts[0].trim();
                LocalDate date = LocalDate.parse(dateInString);

                String uid = parts[1].trim();

                float readingValue = Float.parseFloat(parts[3].trim());

                RadarReading reading = new RadarReading(uid, date, readingValue);
                listOfRadarReadings.add(reading);
            }
            return listOfRadarReadings;
    }


    private static List<String> readRadarReadingsFromFile(File file) {
        try (Reader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader)) {
            List<String> listOfReadValues = new ArrayList<>();
            String readValue = br.readLine();
            while (readValue != null) {
                listOfReadValues.add(readValue);
                readValue = br.readLine();
            }
            return listOfReadValues;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
