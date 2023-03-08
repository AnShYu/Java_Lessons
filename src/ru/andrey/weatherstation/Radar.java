package ru.andrey.weatherstation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public abstract class Radar implements Serializable { // почему его делать как enum? ----------------------------------------

    private static final long serialVersionUID = 1L;
    private static final int DAYS_IN_ACCOUNT_FOR_FORECAST = 5;

    private String uid;
    private String name;
    private float latitude;
    private float longitude;
    private String type;


    private boolean normalFunctioning;
    private Map<LocalDate, RadarReading> mapOfTheRadarReadings = new HashMap<>();

    

    public Radar(String uid, String name, float latitude, float longitude, String type) {
        this.uid = uid;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.normalFunctioning = true;
    }

    public void setAsMalfunctioning() {
        normalFunctioning = false;
    }

    public void setAsNotMalfunctioning() {
        normalFunctioning = true;
    }

    public void addRadarReading (LocalDate date, RadarReading radarReading) {
        mapOfTheRadarReadings.put(date, radarReading);
    }

    public void addRadarReading (RadarReading reading) {
        LocalDate date = reading.getDate();
        mapOfTheRadarReadings.put(date, reading);
    }

    public boolean isNormalFunctioning () {
        return normalFunctioning;
    }


    public float getAverageReadingForPeriod (LocalDate date) {
            float totalOfRadarReadings = 0.0f;
            int daysWithReadings = 0;
            for (int i = 1; i <= DAYS_IN_ACCOUNT_FOR_FORECAST; i++) {
                if (mapOfTheRadarReadings.containsKey(date.minusDays(i))) {
                    totalOfRadarReadings = totalOfRadarReadings + mapOfTheRadarReadings.get(date.minusDays(i)).getReadingValue();
                    daysWithReadings++;
                }
            }
            float averageReading = totalOfRadarReadings / daysWithReadings;
            return averageReading;
    }

    public Boolean hasReadingsForAllDaysInCalculation  (LocalDate date) {
            for (int i = 1; i <= DAYS_IN_ACCOUNT_FOR_FORECAST; i++) {
                if (!mapOfTheRadarReadings.containsKey(date.minusDays(i))) {
                    return false;
                }
            }
            return true;
    }

    public String getType() {
        return type;
    }

    public String getUid() {
        return uid;
    }

    public List<RadarReading> getAllReadingsOfTheRadar() {
        List<RadarReading> listOfTheRadarReadings = new ArrayList<>(this.mapOfTheRadarReadings.values());
        return listOfTheRadarReadings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Radar radar = (Radar) o;
        return Objects.equals(uid, radar.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }

    @Override
    public String toString() {
        return "Radar: uid: " + uid + ", name: " + name + ", latitude: " + latitude + ", longitude: " + longitude + ", type: " + type;
    }

}
