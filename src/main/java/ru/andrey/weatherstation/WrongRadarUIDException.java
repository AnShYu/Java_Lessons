package ru.andrey.weatherstation;

public class WrongRadarUIDException extends Exception {
    public WrongRadarUIDException(String message) {
        super(message);
    }
}
