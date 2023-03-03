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
