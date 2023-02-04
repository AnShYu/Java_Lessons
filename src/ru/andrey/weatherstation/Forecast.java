package ru.andrey.weatherstation;

import java.time.LocalDate;

public class Forecast {
    private Float temperature;
    private Float humidity;
    private Float windSpeed;
    private Boolean isPrecise;

    public Forecast(Float temperature, Float humidity, Float windSpeed, Boolean isPrecise) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.isPrecise = isPrecise;
    }

}
