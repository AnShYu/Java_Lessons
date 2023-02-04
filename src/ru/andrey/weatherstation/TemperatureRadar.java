package ru.andrey.weatherstation;

public class TemperatureRadar extends Radar {

    public TemperatureRadar (String prefix, String name, Float latitude, Float longitude) {
        super(prefix, name, latitude, longitude, "Temperature");
    }

}
