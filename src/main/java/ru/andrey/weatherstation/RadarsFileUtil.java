package ru.andrey.weatherstation;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class RadarsFileUtil {

    public static void writeRadarsToTheFile (List<Radar> list, File file) {
        try (FileOutputStream fos = new FileOutputStream(file, true);
             ObjectOutputStream ous = new ObjectOutputStream(fos)) {
                ous.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Radar> readListOfRadarsFromFile(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            List<Radar> listOfAllRadars = (List<Radar>) ois.readObject();
            return listOfAllRadars;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
