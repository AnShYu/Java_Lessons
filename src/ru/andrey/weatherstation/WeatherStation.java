package ru.andrey.weatherstation;

import java.time.LocalDate;
import java.util.*;

public class WeatherStation {
    private Map<String, Radar> mapOfRadars = new HashMap<>();

    public void addRadar (String uidPrefix, String name, Float latitude, Float longitude, String type) {
        switch (type) {
            case "Temperature":
                Radar temperatureRadar = new TemperatureRadar(uidPrefix, name, latitude, longitude);
                mapOfRadars.put(temperatureRadar.getUid(), temperatureRadar);
                break;
            case "WindSpeed":
                Radar windRadar = new WindRadar(uidPrefix, name, latitude, longitude);
                mapOfRadars.put(windRadar.getUid(), windRadar);
                break;
            case "Humidity":
                Radar humidityRadar = new HumidityRadar(uidPrefix, name, latitude, longitude);
                mapOfRadars.put(humidityRadar.getUid(), humidityRadar);
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
        List<RadarReading> listOfTheRadarReadings = new ArrayList<>(requestedRadar.getMapOfTheRadarReadings().values());

        Collections.sort(listOfTheRadarReadings);
        return listOfTheRadarReadings;
        // throw RadarMalFunctionException
    }

    public Forecast getForecastOnCertainDate (LocalDate date) {
        Float temperarture = 0.0f;
        Float humidity = 0.0f;
        Float windSpeed = 0.0f;
        int numberOfTemperatureRadars = 0;
        int numberOfHumidityRadars = 0;
        int numberOfWindSpeedRadars = 0;
        int numberOfTemperatureRadarsWithEnoughReadings = 0;
        int numberOfHumidityRadarsWithEnoughReadings = 0;
        int numberOfWindSpeedRadarsWithEnoughReadings = 0;
        for (String uid: mapOfRadars.keySet()) {
            if (mapOfRadars.get(uid).getType().equals("Temperature")) {
                temperarture = temperarture + mapOfRadars.get(uid).getAverageReadingForPeriod(date);
                numberOfTemperatureRadars++;
                if (mapOfRadars.get(uid).thereAreReadingsForAllDaysInCalculation(date)) numberOfTemperatureRadarsWithEnoughReadings++;
            }
            else if (mapOfRadars.get(uid).getType().equals("Humidity")) {
                humidity = humidity + mapOfRadars.get(uid).getAverageReadingForPeriod(date);
                numberOfHumidityRadars++;
                if (mapOfRadars.get(uid).thereAreReadingsForAllDaysInCalculation(date)) numberOfHumidityRadarsWithEnoughReadings++;
            }
            else if (mapOfRadars.get(uid).getType().equals("WindSpeed")) {
                windSpeed = windSpeed + mapOfRadars.get(uid).getAverageReadingForPeriod(date);
                numberOfWindSpeedRadars++;
                if (mapOfRadars.get(uid).thereAreReadingsForAllDaysInCalculation(date)) numberOfWindSpeedRadarsWithEnoughReadings++;
            }
        }

        Float averageTemperature = temperarture / numberOfTemperatureRadars;
        Float averageHumidity = humidity / numberOfHumidityRadars;
        Float averageWindSpeed = windSpeed / numberOfWindSpeedRadars;

        Boolean isPrecise = true;
        if (numberOfTemperatureRadars <= 1 || numberOfHumidityRadars <= 1 || numberOfWindSpeedRadars <= 1) isPrecise = false;
        if (numberOfTemperatureRadarsWithEnoughReadings == 0) isPrecise = false;
        if (numberOfHumidityRadarsWithEnoughReadings == 0) isPrecise = false;
        if (numberOfWindSpeedRadarsWithEnoughReadings == 0) isPrecise = false;

        Forecast forecast = new Forecast (averageTemperature, averageHumidity, averageWindSpeed, isPrecise);
        return forecast;
    }

    public void markRadarMalfunction (String uid) {
        mapOfRadars.get(uid).setAsMalfunctioning();
    }

    public void fixRadar (String uid) {
        mapOfRadars.get(uid).setAsNotMalfunctioning();
    }

    public List<Radar> getListOfAllMalfunctioningRadars () {
        List<Radar> listOfMalfunctioningRadars = new ArrayList<>();
        for (String uid: mapOfRadars.keySet()) {
            if (mapOfRadars.get(uid).isNormalFunctioning() == false) {
                listOfMalfunctioningRadars.add(mapOfRadars.get(uid));
            }
        }
        return  listOfMalfunctioningRadars;
    }

}
