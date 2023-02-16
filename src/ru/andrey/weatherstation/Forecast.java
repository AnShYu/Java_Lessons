package ru.andrey.weatherstation;

public class Forecast {
    private float temperature;
    private float humidity;
    private float windSpeed;
    private boolean isPrecise;

    public Forecast(float temperature, float humidity, float windSpeed, boolean isPrecise) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.isPrecise = isPrecise;
    }

    @Override
    public String toString() {
        String preciseness = "Forecast is precise";
        if (!isPrecise) preciseness = "Forecast is not precise";
        return "Temperature: " + temperature + ", Humidity: " + humidity + ", WindSpeed: " + windSpeed + ". " + preciseness;
    }
}
