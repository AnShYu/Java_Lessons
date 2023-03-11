package ru.andrey.weatherstation;

public class WindRadar extends Radar {
    public WindRadar (String uid, String name, float latitude, float longitude) {
        super(uid, name, latitude, longitude, RadarType.WINDSPEED);
    }
}
