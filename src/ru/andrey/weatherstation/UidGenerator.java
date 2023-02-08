package ru.andrey.weatherstation;

import java.util.ArrayList;
import java.util.List;

public class UidGenerator {

    int counterOfRadars = 0;
    private String uid;

    public UidGenerator(String prefix) {
        counterOfRadars++;
        this.uid = prefix + "_" + counterOfRadars;
    }

    public String getUid() {
        return uid;
    }
}
