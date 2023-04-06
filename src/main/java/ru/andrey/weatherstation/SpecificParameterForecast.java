package ru.andrey.weatherstation;

public class SpecificParameterForecast {
    private boolean isPrecise;
    private float parameterForecast;

    public SpecificParameterForecast(boolean isPrecise, float parameterForecast) {
        this.isPrecise = isPrecise;
        this.parameterForecast = parameterForecast;
    }

    public boolean isPrecise() {
        return isPrecise;
    }

    public float getParameterForecast() {
        return parameterForecast;
    }
}
