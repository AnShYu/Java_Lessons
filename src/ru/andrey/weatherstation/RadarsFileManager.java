package ru.andrey.weatherstation;

import java.io.*;
import java.util.List;

public class RadarsFileManager {

    public static void writeRadarsToTheFile (List<Radar> list, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName, true);
             ObjectOutputStream ous = new ObjectOutputStream(fos)) {
                ous.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Radar> readListOfRadarsFromFile(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            List<Radar> listOfAllRadars = (List<Radar>) ois.readObject();
            return listOfAllRadars;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
