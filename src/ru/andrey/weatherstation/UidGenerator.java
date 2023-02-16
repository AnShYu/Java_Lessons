package ru.andrey.weatherstation;

import java.util.ArrayList;
import java.util.List;

public class UidGenerator {

    private int counter = 1;
    public String generateUid(String prefix) {
        String uid  = prefix + "_" + counter;
        counter++;
        return uid;
    }
}
