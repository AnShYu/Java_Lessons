package ru.andrey.weatherstation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Radar {

    private String uid;
    private String name;
    private Float latitude;
    private Float longitude;
    private String type;

    private static int daysInAccountForForecast = 5;
    private Boolean normalFunctioning;
    private Map<LocalDate, RadarReading> mapOfTheRadarReadings = new HashMap<>();

    public Radar(String prefix, String name, Float latitude, Float longitude, String type) {
        UidGenerator newUID = new UidGenerator(prefix);
        this.uid = newUID.getUid();
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

    public boolean isNormalFunctioning () {
        return normalFunctioning;
    }


    public Float getAverageReadingForPeriod (LocalDate date) {
        if (normalFunctioning) {
            Float totalOfRadarReadings = 0.0f;
            for (int i = 1; i <= daysInAccountForForecast; i++) {
                if (mapOfTheRadarReadings.containsKey(date.minusDays(i))) {
                    totalOfRadarReadings = totalOfRadarReadings + mapOfTheRadarReadings.get(date.minusDays(i)).getReadingValue();
                }
            }
            Float averageReading = totalOfRadarReadings / daysInAccountForForecast;
            return averageReading;
        }
        return 0.0f;
    }

    public Boolean thereAreReadingsForAllDaysInCalculation  (LocalDate date) {
            Boolean thereAreReadingsForAllDaysInCalculation = true;
            for (int i = 1; i <= daysInAccountForForecast; i++) {
                if (mapOfTheRadarReadings.containsKey(date.minusDays(i)) == false) {
                    thereAreReadingsForAllDaysInCalculation = false;
                    break;
                }
            }
            return thereAreReadingsForAllDaysInCalculation;
    }

    public String getType() {
        return type;
    }

    public String getUid() {
        return uid;
    }

    public Map<LocalDate, RadarReading> getMapOfTheRadarReadings() {
        return mapOfTheRadarReadings;
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
