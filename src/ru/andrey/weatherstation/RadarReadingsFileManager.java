package ru.andrey.weatherstation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RadarReadingsFileManager {

    //дата, uid радара, тип параметра, значение параметра - презюмирую, что каждая запись начинается с новой строчки
    public static List<RadarReading> makeListOfRadarReadings(String fileName) {
        List<String> listOfCSVs = readRadarReadingsFromFile(fileName);
        List<RadarReading> listOfRadarReadings = new ArrayList<>();
        for (String string: listOfCSVs) {
            String[] parts = string.split("\\,");

            String dateInString = parts[0].trim();
            LocalDate date = LocalDate.parse(dateInString);

            String uid = parts[1].trim();

            float readingValue = Float.parseFloat(parts[3].trim());

            RadarReading reading = new RadarReading(uid, date, readingValue);
            listOfRadarReadings.add(reading);
        }
        return listOfRadarReadings;

//            int commaIndex1 = string.indexOf(",");
//            String dateInString = string.substring(0, commaIndex1).trim();
//            LocalDate date = LocalDate.parse(dateInString);
//
//            String substring1 = string.substring(commaIndex1 + 1).trim();
//            int commaIndex2 = substring1.indexOf(",")
        }


    }

    private static List<String> readRadarReadingsFromFile (String fileName) {
        try (Reader reader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(reader)) {
            List<String> listOfReadValues = new ArrayList<>();
            String readValue = br.readLine();
            while (readValue != null) {
                listOfReadValues.add(readValue);
                readValue = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
