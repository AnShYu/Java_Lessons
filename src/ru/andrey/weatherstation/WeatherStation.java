package ru.andrey.weatherstation;

import java.time.LocalDate;
import java.util.*;

public class WeatherStation {
//  Мапа мап здесь не нужна? если добавлять все радары одновременно и в спец мапу и в общую, то будет ли создаваться 2 радара?
    private Map<String, Radar> mapOfTemperatureRadars = new HashMap<>();
    private Map<String, Radar> mapOfWindSPeedRadars = new HashMap<>();
    private Map<String, Radar> mapOfHumidityRadars = new HashMap<>();
    private UidGenerator uidGenerator;
    public WeatherStation() {
        this.uidGenerator = new UidGenerator();
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

    public void addRadarReading (String uid, LocalDate date, float readingValue) throws  RadarMalfunctionException, WrongRadarUIDException {
        Radar radar = getRadar(uid);
        if (!radar.isNormalFunctioning()) throw new RadarMalfunctionException("Radar is malfunctioning");
        RadarReading radarReading = new RadarReading(uid, date, readingValue);
        radar.addRadarReading(date, radarReading);
    }

    public List<RadarReading> getAllReadingsOfTheRadar (String uid) throws RadarMalfunctionException, WrongRadarUIDException {
        Radar radar = getRadar(uid);
        if (!radar.isNormalFunctioning()) throw new RadarMalfunctionException("Radar is malfunctioning");
        List<RadarReading> listOfTheRadarReadings = new ArrayList<>(radar.getMapOfTheRadarReadings().values());
        Collections.sort(listOfTheRadarReadings);
        return listOfTheRadarReadings;
    }

    public Forecast getForecastOnCertainDate (LocalDate date) {
        float temperature = 0.0f;
        float humidity = 0.0f;
        float windSpeed = 0.0f;
        int numberOfTemperatureRadars = 0;
        int numberOfHumidityRadars = 0;
        int numberOfWindSpeedRadars = 0;
        int numberOfTemperatureRadarsWithEnoughReadings = 0;
        int numberOfHumidityRadarsWithEnoughReadings = 0;
        int numberOfWindSpeedRadarsWithEnoughReadings = 0;

        for (String uid: mapOfTemperatureRadars.keySet()) { // как здесь обратиться сразу к значению?
            Radar radar = mapOfTemperatureRadars.get(uid);
            if (radar.isNormalFunctioning()) {
                temperature = temperature + radar.getAverageReadingForPeriod(date);
                numberOfTemperatureRadars++;
                if (radar.hasReadingsForAllDaysInCalculation(date)) {
                    numberOfTemperatureRadarsWithEnoughReadings++;
                }
            }
        }
        for (String uid: mapOfHumidityRadars.keySet()) {
            Radar radar = mapOfHumidityRadars.get(uid);
            if (radar.isNormalFunctioning()) {
                humidity = humidity + radar.getAverageReadingForPeriod(date);
                numberOfHumidityRadars++;
                if (radar.hasReadingsForAllDaysInCalculation(date)) {
                    numberOfHumidityRadarsWithEnoughReadings++;
                }
            }
        }
        for (String uid: mapOfWindSPeedRadars.keySet()) {
            Radar radar = mapOfWindSPeedRadars.get(uid);
            if (radar.isNormalFunctioning()) {
                windSpeed = windSpeed + radar.getAverageReadingForPeriod(date);
                numberOfWindSpeedRadars++;
                if (radar.hasReadingsForAllDaysInCalculation(date)) {
                    numberOfWindSpeedRadarsWithEnoughReadings++;
                }
            }
        }

        float averageTemperature = temperature / numberOfTemperatureRadars;
        float averageHumidity = humidity / numberOfHumidityRadars;
        float averageWindSpeed = windSpeed / numberOfWindSpeedRadars;

        boolean isPrecise = true;
        if (numberOfTemperatureRadars <= 1 || numberOfHumidityRadars <= 1 || numberOfWindSpeedRadars <= 1) isPrecise = false;
        if (numberOfTemperatureRadarsWithEnoughReadings == 0) isPrecise = false;
        if (numberOfHumidityRadarsWithEnoughReadings == 0) isPrecise = false;
        if (numberOfWindSpeedRadarsWithEnoughReadings == 0) isPrecise = false;

        Forecast forecast = new Forecast (averageTemperature, averageHumidity, averageWindSpeed, isPrecise);
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
        else if (mapOfHumidityRadars.containsKey(uid)) radar = mapOfHumidityRadars.get(uid);
        else throw new WrongRadarUIDException("There is no Radar with such UID");
        return radar;
    }

}
