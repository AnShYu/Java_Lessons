package ru.andrey.weatherstation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Radar {

    String uid;
    String name;
    Float latitude;
    Float longitude;
    private String type;
    private Boolean normalFunctioning; // могу ли сделать поля не прайват? или же для всех полей, к которым хочу обращаться из других файлов, нужно делать геттер?
    Map<LocalDate, RadarReading> mapOfTheRadarReadings = new HashMap<>();

    public Radar(String prefix, String name, Float latitude, Float longitude, String type) {
        UidGenerator newUID = new UidGenerator(prefix);
        this.uid = newUID.uid;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.normalFunctioning = true;
    }

    public void isMalfunctioning () {
        normalFunctioning = false;
    }

    public void isNotMalfunctioning () {
        normalFunctioning = true;
    }

    public void addRadarReading (LocalDate date, RadarReading radarReading) {
        mapOfTheRadarReadings.put(date, radarReading);
    }

    public boolean isNormalFunctioning () {
        return normalFunctioning;
    }

    public String getType() {
        return type;
    }

    public Float getAverageReadingForPeriod (LocalDate date) {
        if (normalFunctioning) {
            int numberOfDaysInCalculation = 5;
            Float totalOfRadarReadings = 0.0f;
            for (int i = 1; i <= numberOfDaysInCalculation; i++) {
                totalOfRadarReadings = totalOfRadarReadings + mapOfTheRadarReadings.get(date.minusDays(i)).readingValue;
            }
            Float averageReading = totalOfRadarReadings / numberOfDaysInCalculation;
            return averageReading;
        }
        return 0.0f;
    }

    public Boolean thereAreReadingsForAllDaysInCalculation  (LocalDate date) {
            int numberOfDaysInCalculation = 5;
            Boolean thereAreReadingsForAllDaysInCalculation = true;
            for (int i = 1; i <= numberOfDaysInCalculation; i++) {
                if (mapOfTheRadarReadings.containsKey(date.minusDays(i)) == false) {
                    thereAreReadingsForAllDaysInCalculation = false;
                    break;
                }
            }
            return thereAreReadingsForAllDaysInCalculation;
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
}
