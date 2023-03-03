package ru.andrey.weatherstation;

public class UIDGeneratorSingleton {
    private static UIDGeneratorSingleton INSTANCE;
    private UIDGeneratorSingleton() {
    }
    public static UIDGeneratorSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UIDGeneratorSingleton();
        }
        return INSTANCE;
    }

    private int counter = 1;
    public String generateUid(String prefix) {
        String uid  = prefix + "_" + counter;
        counter++;
        return uid;
    }

    public void increaseUIDCounter () {
        counter++;
    }
}
