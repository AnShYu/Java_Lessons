package ru.andrey.weatherstation;

import java.util.ArrayList;
import java.util.List;

public class UidGenerator {

    int counterOfRadars = 0; // почему при создании нового uid это поле опять становится равным нулю? В чем отличие от хранения данных в коллекции? Или это зависит от того, в каком классе я создаю переменную?
    private String uid;

    public UidGenerator(String prefix) {
        counterOfRadars++;
        this.uid = prefix + "_" + counterOfRadars;
    }

    public String getUid() {
        return uid;
    }
}
