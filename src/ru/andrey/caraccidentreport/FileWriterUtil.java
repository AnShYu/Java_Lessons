package ru.andrey.caraccidentreport;

import ru.andrey.weatherstation.Radar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileWriterUtil {

    public static void writeListOfStringsToTheFile (List<String> list, File file) {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream ous = new ObjectOutputStream(fos)) {
            for (String string: list) {
                ous.writeObject(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
