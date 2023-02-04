package ru.andrey.weatherstation;

import java.time.LocalDate;

public class RadarReading implements Comparable<RadarReading> {
    LocalDate date;
    Float readingValue;
    String uid;

    public RadarReading(String uid, LocalDate date, Float readingValue) {
        this.uid = uid;
        this.date = date.now();
        this.readingValue = readingValue;
    }

    @Override
    public int compareTo(RadarReading o) {
        if (this.date.compareTo(o.date) < 0) return -1;
        if (this.date.compareTo(o.date) > 0) return 1;
        return 0;
    }
}
