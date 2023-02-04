package ru.andrey.weatherstation;

public class WindRadar extends Radar {
    public WindRadar (String prefix, String name, Float latitude, Float longitude) {
        super(prefix, name, latitude, longitude, "WindSpeed");
    }
}
