package ru.andrey.weatherstation;

import java.time.LocalDate;

public class Forecast {
    private LocalDate date;
    private float temperature;
    private float humidity;
    private float windSpeed;
    private boolean isPrecise;

    public Forecast(LocalDate date, float temperature, float humidity, float windSpeed, boolean isPrecise) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.isPrecise = isPrecise;
    }

    public Forecast(LocalDate date, SpecificParameterForecast temperatureForecast, SpecificParameterForecast humidityForecast, SpecificParameterForecast windSpeedForecast) {
        boolean isPrecise = false;
        if(temperatureForecast.isPrecise() && humidityForecast.isPrecise() && windSpeedForecast.isPrecise()) {
            isPrecise = true;
        }
        float temperature = temperatureForecast.getParameterForecast();
        float humidity = humidityForecast.getParameterForecast();
        float windSpeed = windSpeedForecast.getParameterForecast();

        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.isPrecise = isPrecise;
    }

    public LocalDate getDate() {
        return date;
    }
    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public boolean isPrecise() {
        return isPrecise;
    }

    @Override
    public String toString() {
        String preciseness = "Forecast is precise";
        if (!isPrecise) preciseness = "Forecast is not precise";
        return "Temperature: " + temperature + ", Humidity: " + humidity + ", WindSpeed: " + windSpeed + ". " + preciseness;
    }
}
