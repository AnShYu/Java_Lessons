package ru.andrey.weatherstation;

public class WrongRadarTypeException extends Exception {

    public WrongRadarTypeException(String message) {
        super(message);
    }
}
