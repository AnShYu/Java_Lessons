package ru.andrey.weatherstation;

import java.time.LocalDate;
import java.util.*;

public class WeatherStation {
    private Map<String, Radar> mapOfRadars = new HashMap<>();

    public void addRadar (String uidPrefix, String name, Float latitude, Float longitude, String type) {
        switch (type) {
            case "Temperature":
                Radar temperatureRadar = new TemperatureRadar(uidPrefix, name, latitude, longitude);
                mapOfRadars.put(temperatureRadar.uid, temperatureRadar);
                break;
            case "WindSpeed":
                Radar windRadar = new WindRadar(uidPrefix, name, latitude, longitude);
                mapOfRadars.put(windRadar.uid, windRadar);
                break;
            case "Humidity":
                Radar humidityRadar = new HumidityRadar(uidPrefix, name, latitude, longitude);
                mapOfRadars.put(humidityRadar.uid, humidityRadar);
                break;
            default:
                // throw WrongRadarTypeException
                break;
        }
    }

    public void addRadarReading (String uid, LocalDate date, Float readingValue) {
        RadarReading radarReading = new RadarReading(uid, date, readingValue);
        mapOfRadars.get(uid).addRadarReading(date, radarReading);
        // throw RadarMalFunctionException
    }

    public List<RadarReading> getAllReadingsOfTheRadar (String uid) {
        Radar requestedRadar = mapOfRadars.get(uid);
        List<RadarReading> arrayOfTheRadarReadings = new ArrayList<>(requestedRadar.mapOfTheRadarReadings.values());

        Collections.sort(arrayOfTheRadarReadings);
        return arrayOfTheRadarReadings;
        // throw RadarMalFunctionException
    }

    public Forecast getForecastOnCertainDate (LocalDate date) {
        Float temperarture = 0.0f;
        Float humidity = 0.0f;
        Float windSpeed = 0.0f;
        int numberOfTemperatureRadars = 0;
        int numberOfHumidityRadars = 0;
        int numberOfWindSpeedRadars = 0;
        int numberOfTemperatureRadarsWithReadingsForAllDaysInCalculation = 0;
        int numberOfHumidityRadarsWithReadingsForAllDaysInCalculation = 0;
        int numberOfWindSpeedRadarsWithReadingsForAllDaysInCalculation = 0;
        for (String uid: mapOfRadars.keySet()) {
            if (mapOfRadars.get(uid).getType().equals("Temperature")) {
                temperarture = temperarture + mapOfRadars.get(uid).getAverageReadingForPeriod(date);
                numberOfTemperatureRadars++;
                if (mapOfRadars.get(uid).thereAreReadingsForAllDaysInCalculation(date)) numberOfTemperatureRadarsWithReadingsForAllDaysInCalculation++;
            }
            else if (mapOfRadars.get(uid).getType().equals("Humidity")) {
                humidity = humidity + mapOfRadars.get(uid).getAverageReadingForPeriod(date);
                numberOfHumidityRadars++;
                if (mapOfRadars.get(uid).thereAreReadingsForAllDaysInCalculation(date)) numberOfHumidityRadarsWithReadingsForAllDaysInCalculation++;
            }
            else if (mapOfRadars.get(uid).getType().equals("WindSpeed")) {
                windSpeed = windSpeed + mapOfRadars.get(uid).getAverageReadingForPeriod(date);
                numberOfWindSpeedRadars++;
                if (mapOfRadars.get(uid).thereAreReadingsForAllDaysInCalculation(date)) numberOfWindSpeedRadarsWithReadingsForAllDaysInCalculation++;
            }
        }

        Float averageTemperature = temperarture / numberOfTemperatureRadars;
        Float averageHumidity = humidity / numberOfHumidityRadars;
        Float averageWindSpeed = windSpeed / numberOfWindSpeedRadars;

        Boolean isPrecise = true;
        if (numberOfTemperatureRadars == 1 || numberOfHumidityRadars == 1 || numberOfWindSpeedRadars == 1) isPrecise = false;
        if (numberOfTemperatureRadarsWithReadingsForAllDaysInCalculation == 0) isPrecise = false;
        if (numberOfHumidityRadarsWithReadingsForAllDaysInCalculation == 0) isPrecise = false;
        if (numberOfWindSpeedRadarsWithReadingsForAllDaysInCalculation == 0) isPrecise = false;

        Forecast forecast = new Forecast (averageTemperature, averageHumidity, averageWindSpeed, isPrecise);
        return forecast;
    }

    public void markRadarMalfunction (String uid) {
        mapOfRadars.get(uid).isMalfunctioning();
    }

    public void fixRadar (String uid) {
        mapOfRadars.get(uid).isNotMalfunctioning();
    }

    public Map<String, Radar> getMapOfAllMalfunctioningRadars () {
        Map<String, Radar> mapOfMalfunctioningRadars = new HashMap<>();
        for (String uid: mapOfRadars.keySet()) {
            if (mapOfRadars.get(uid).isNormalFunctioning()) {
                mapOfMalfunctioningRadars.put(uid, mapOfRadars.get(uid));
            }
        }
        return  mapOfMalfunctioningRadars;
    }

}
