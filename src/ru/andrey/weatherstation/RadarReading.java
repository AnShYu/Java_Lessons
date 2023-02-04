package ru.andrey.weatherstation;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RadarReading that = (RadarReading) o;
        return Objects.equals(readingValue, that.readingValue) && Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readingValue, uid);
    }
}
