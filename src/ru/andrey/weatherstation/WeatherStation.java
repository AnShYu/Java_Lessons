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
    private UIDGeneratorSingleton uidGenerator;
    public WeatherStation() {
        this.uidGenerator = UIDGeneratorSingleton.getInstance();
    }

    public Map<String, Radar> getMapOfTemperatureRadars() {
        return mapOfTemperatureRadars;
    }

    public Map<String, Radar> getMapOfWindSpeedRadars() {
        return mapOfWindSPeedRadars;
    }

    public Map<String, Radar> getMapOfHumidityRadars() {
        return mapOfHumidityRadars;
    }

    public void addRadar (String uidPrefix, String name, float latitude, float longitude, String type) throws WrongRadarTypeException {
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

    public void addRadar (Radar radar) {
        uidGenerator.increaseUIDCounter(); // это норм подход? Или лучше получать counter через getter
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

    public void addRadarReading (String uid, LocalDate date, float readingValue) throws  RadarMalfunctionException, WrongRadarUIDException {
        Radar radar = getRadar(uid);
        if (!radar.isNormalFunctioning()) throw new RadarMalfunctionException("Radar is malfunctioning");
        RadarReading radarReading = new RadarReading(uid, date, readingValue);
        radar.addRadarReading(date, radarReading);
    }

    public void addRadarReading(List<RadarReading> list) {
        for (RadarReading reading : list) {
            if (this.mapOfTemperatureRadars.containsKey(reading.getUid())) {
                this.mapOfTemperatureRadars.get(reading.getUid()).addRadarReading(reading);
            } else if (this.mapOfHumidityRadars.containsKey(reading.getUid())) {
                this.mapOfTemperatureRadars.get(reading.getUid()).addRadarReading(reading);
            } else if (this.mapOfWindSPeedRadars.containsKey(reading.getUid())) {
                this.mapOfTemperatureRadars.get(reading.getUid()).addRadarReading(reading);
            }
        }

    }

    public List<RadarReading> getAllReadingsOfTheRadar (String uid) throws RadarMalfunctionException, WrongRadarUIDException {
        Radar radar = getRadar(uid);
        if (!radar.isNormalFunctioning()) throw new RadarMalfunctionException("Radar is malfunctioning");
        List<RadarReading> listOfTheRadarReadings = new ArrayList<>(radar.getMapOfTheRadarReadings().values());
        Collections.sort(listOfTheRadarReadings);
        return listOfTheRadarReadings;
    }

    public Forecast getForecastOnCertainDate (LocalDate date, File file) {
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

        Forecast forecast = new Forecast (averageTemperature, averageHumidity, averageWindSpeed, isPrecise);

        try (Writer writer  = new FileWriter(file, true)) {
            writer.write("Date: " + date + "\n");
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

        return forecast;
    }

    public void markRadarMalfunction (String uid) throws WrongRadarUIDException {
        Radar radar = getRadar(uid);
        radar.setAsMalfunctioning();
    }

    public void fixRadar (String uid) throws WrongRadarUIDException {
        Radar radar = getRadar(uid);
        radar.setAsNotMalfunctioning();
    }

    public List<Radar> getListOfAllMalfunctioningRadars () {
        List<Radar> listOfMalfunctioningRadars = new ArrayList<>();
        for (String uid: mapOfTemperatureRadars.keySet()) {
            Radar radar = mapOfTemperatureRadars.get(uid);
            if (!radar.isNormalFunctioning()) {
                listOfMalfunctioningRadars.add(radar);
            }
        }
        for (String uid: mapOfWindSPeedRadars.keySet()) {
            Radar radar = mapOfWindSPeedRadars.get(uid);
            if (!radar.isNormalFunctioning()) {
                listOfMalfunctioningRadars.add(radar);
            }
        }
        for (String uid: mapOfHumidityRadars.keySet()) {
            Radar radar = mapOfHumidityRadars.get(uid);
            if (!radar.isNormalFunctioning()) {
                listOfMalfunctioningRadars.add(radar);
            }
        }
        return  listOfMalfunctioningRadars;
    }

    private Radar getRadar(String uid) throws WrongRadarUIDException {
        Radar radar;
        if (mapOfTemperatureRadars.containsKey(uid)) {
            radar = mapOfTemperatureRadars.get(uid);
        }
        else if (mapOfWindSPeedRadars.containsKey(uid)) {
            radar = mapOfWindSPeedRadars.get(uid);
        }
        else if (mapOfHumidityRadars.containsKey(uid)) {
            radar = mapOfHumidityRadars.get(uid);
        } else {
            throw new WrongRadarUIDException("There is no Radar with such UID");
        }
        return radar;
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
        for (Radar radar: this.getMapOfTemperatureRadars().values()) {
            listOfRadars.add(radar);
        }
        for (Radar radar: this.getMapOfHumidityRadars().values()) {
            listOfRadars.add(radar);
        }
        for (Radar radar: this.getMapOfWindSpeedRadars().values()) {
            listOfRadars.add(radar);
        }
        return listOfRadars;
    }
}
