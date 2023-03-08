package ru.andrey.weatherstation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.*;

public class WeatherStation {

    private Map<String, Radar> mapOfTemperatureRadars = new HashMap<>();
    private Map<String, Radar> mapOfWindSPeedRadars = new HashMap<>();
    private Map<String, Radar> mapOfHumidityRadars = new HashMap<>();

    private List<Map<String, Radar>> listOfRadarMaps = new ArrayList<>(); // Хороший ли это подход? Создавать дополнительные сущности, чтобы немного сократить код дальше
    private UIDGeneratorSingleton uidGenerator;
    public WeatherStation() {
        this.uidGenerator = UIDGeneratorSingleton.getInstance();
        this.listOfRadarMaps.add(mapOfTemperatureRadars);
        this.listOfRadarMaps.add(mapOfHumidityRadars);
        this.listOfRadarMaps.add(mapOfWindSPeedRadars);
    }

    public void addRadar(String uidPrefix, String name, float latitude, float longitude, String type) throws WrongRadarTypeException {
        String uid = uidGenerator.generateUid(uidPrefix);
        switch (type) {
            case "Temperature":
                Radar temperatureRadar = new TemperatureRadar(uid, name, latitude, longitude);
                mapOfTemperatureRadars.put(temperatureRadar.getUid(), temperatureRadar);
                break;
            case "WindSpeed":
                Radar windRadar = new WindRadar(uid, name, latitude, longitude);
                mapOfWindSPeedRadars.put(windRadar.getUid(), windRadar);
                break;
            case "Humidity":
                Radar humidityRadar = new HumidityRadar(uid, name, latitude, longitude);
                mapOfHumidityRadars.put(humidityRadar.getUid(), humidityRadar);
                break;
            default:
                throw new WrongRadarTypeException("There is no such type of Radars");
        }
    }

    public void addRadar(Radar radar) {
        uidGenerator.increaseUIDCounter();
        switch (radar.getType()) {
            case "Temperature":
                mapOfTemperatureRadars.put(radar.getUid(), radar);
                break;
            case "WindSpeed":
                mapOfWindSPeedRadars.put(radar.getUid(), radar);
                break;
            case "Humidity":
                mapOfHumidityRadars.put(radar.getUid(), radar);
                break;
        }
    }

    public void addRadarReading(String uid, LocalDate date, float readingValue) throws  RadarMalfunctionException, WrongRadarUIDException {
        Radar radar = getRadar(uid);
        if (!radar.isNormalFunctioning()) throw new RadarMalfunctionException("Radar is malfunctioning");
        RadarReading radarReading = new RadarReading(uid, date, readingValue);
        radar.addRadarReading(date, radarReading);
    }

    public void addRadarReading(List<RadarReading> list) {
        for (RadarReading reading : list) {
            for (Map<String, Radar> map : listOfRadarMaps) {
                if (map.containsKey(reading.getUid())) {
                    map.get(reading.getUid()).addRadarReading(reading);
                }
            }
        }
    }

    public List<RadarReading> getAllReadingsOfTheRadar(String uid) throws RadarMalfunctionException, WrongRadarUIDException {
        Radar radar = getRadar(uid);
        if (!radar.isNormalFunctioning()) throw new RadarMalfunctionException("Radar is malfunctioning");
        List<RadarReading> listOfTheRadarReadings = radar.getAllReadingsOfTheRadar();
        Collections.sort(listOfTheRadarReadings);
        return listOfTheRadarReadings;
    }

    public Forecast getForecastOnCertainDate(LocalDate date) {
        SpecificParameterForecast temperatureForecast = getSpecificParameterForecastOnCertainDate(mapOfTemperatureRadars, date);
        SpecificParameterForecast humidityForecast = getSpecificParameterForecastOnCertainDate(mapOfHumidityRadars, date);
        SpecificParameterForecast windSpeedForecast = getSpecificParameterForecastOnCertainDate(mapOfWindSPeedRadars, date);

        boolean isPrecise = false;
        if(temperatureForecast.isPrecise() && humidityForecast.isPrecise() && windSpeedForecast.isPrecise()) {
            isPrecise = true;
        }

        float averageTemperature = temperatureForecast.getParameterForecast();
        float averageHumidity = humidityForecast.getParameterForecast();
        float averageWindSpeed = windSpeedForecast.getParameterForecast();

        Forecast forecast = new Forecast (date, averageTemperature, averageHumidity, averageWindSpeed, isPrecise);
        return forecast;
    }

    public void markRadarMalfunction(String uid) throws WrongRadarUIDException {
        Radar radar = getRadar(uid);
        radar.setAsMalfunctioning();
    }

    public void fixRadar(String uid) throws WrongRadarUIDException {
        Radar radar = getRadar(uid);
        radar.setAsNotMalfunctioning();
    }

    public List<Radar> getListOfAllMalfunctioningRadars() {
        List<Radar> listOfMalfunctioningRadars = new ArrayList<>();
        for (Map<String, Radar> map : listOfRadarMaps) {
            addRadarsToMalfunctioning(map, listOfMalfunctioningRadars);
            }
        return  listOfMalfunctioningRadars;
    }

    private Radar getRadar(String uid) throws WrongRadarUIDException {
        Radar radar;
        for (Map<String, Radar> map : listOfRadarMaps) {
            if (map.containsKey(uid)) {
                radar = mapOfTemperatureRadars.get(uid);
                return radar;
            } else {
                throw new WrongRadarUIDException("There is no Radar with such UID");
            }
        }
        return null; // нормально ли здесь возвращать null? -----------------------------------------------------------------------
    }

    private SpecificParameterForecast getSpecificParameterForecastOnCertainDate (Map<String, Radar> map, LocalDate date) {
        float parameterValue = 0.0f;
        int numberOfParameterRadars = 0;
        int numberOfParameterRadarsWithEnoughReadings = 0;
        for (Radar radar : map.values()) {
            if (radar.isNormalFunctioning()) {
                parameterValue = parameterValue + radar.getAverageReadingForPeriod(date);
                numberOfParameterRadars++;
                if (radar.hasReadingsForAllDaysInCalculation(date)) {
                    numberOfParameterRadarsWithEnoughReadings++;
                }
            }
        }
        float averageParameterValue = parameterValue / numberOfParameterRadars;

        boolean isPrecise = true;
        if (numberOfParameterRadars <= 1) {
            isPrecise = false;
        }
        if (numberOfParameterRadarsWithEnoughReadings == 0) {
            isPrecise = false;
        }

        SpecificParameterForecast specificForeCast = new SpecificParameterForecast(isPrecise, averageParameterValue);
        return specificForeCast;
    }

    public List<Radar> getListOfAllRadars() {
        List <Radar> listOfRadars = new ArrayList<>();
        for (Map<String, Radar> map : listOfRadarMaps) {
            for (Radar radar: map.values()) {
                listOfRadars.add(radar);
            }
        }
        return listOfRadars;
    }

    public void addRadarsToMalfunctioning (Map<String, Radar> map, List<Radar> list) {
        for (String uid: map.keySet()) {
            Radar radar = map.get(uid);
            if (!radar.isNormalFunctioning()) {
                list.add(radar);
            }
        }
    }

}
