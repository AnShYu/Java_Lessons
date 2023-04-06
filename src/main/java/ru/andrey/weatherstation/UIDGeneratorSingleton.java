package ru.andrey.weatherstation;

import java.util.List;

public class UIDGeneratorSingleton {
    private static UIDGeneratorSingleton INSTANCE;
    private int counter = 1;
    private UIDGeneratorSingleton() {
    }
    public static UIDGeneratorSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UIDGeneratorSingleton();
        }
        return INSTANCE;
    }

    public String generateUid(String prefix) {
        String uid  = prefix + "_" + counter;
        counter++;
        return uid;
    }

    public void updateUIDCounter (int providedCounter) {
        if (providedCounter > counter) {
            counter = providedCounter;
        }
    }

}
