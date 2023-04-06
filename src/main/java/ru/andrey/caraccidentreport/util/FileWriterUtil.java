package ru.andrey.caraccidentreport.util;

import ru.andrey.weatherstation.Radar;

import java.io.*;
import java.util.List;

public class FileWriterUtil {

    public static void writeListOfStringsToTheFile (List<String> list, File file) {
        try (Writer writer  = new FileWriter(file)) {
            for (String string: list) {
                writer.write(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
