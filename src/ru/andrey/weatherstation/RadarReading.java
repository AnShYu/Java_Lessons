package ru.andrey.weatherstation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class RadarReading implements Comparable<RadarReading>, Serializable {

    private static final long serialVersionUID = 1L;
    private LocalDate date;
    private float readingValue;
    private String uid;

    public RadarReading(String uid, LocalDate date, float readingValue) {
        this.uid = uid;
        this.date = date;
        this.readingValue = readingValue;
    }

    public float getReadingValue() {
        return readingValue;
    }

    public String getUid() {
        return uid;
    }

    public LocalDate getDate() {
        return date;
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

    @Override
    public String toString() {
        return "Radar Reading: " + "date: " + date + ", " + "reading: " + readingValue;
    }
}
