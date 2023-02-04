package ru.andrey.weatherstation;

import java.util.ArrayList;
import java.util.List;

public class UidGenerator {

    int counterOfRadars = 0;
    String uid;

    public UidGenerator(String prefix) {
        counterOfRadars++;
        this.uid = prefix + "_" + counterOfRadars;
    }

}
