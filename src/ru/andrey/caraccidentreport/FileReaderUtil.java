package ru.andrey.caraccidentreport;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileReaderUtil {

    public static String readFile (File file) {
        if (file.exists()) {
            String filePath = file.getPath();
            Path path = Paths.get(filePath);
            try (Scanner scanner = new Scanner(path)) {
                String allTextFromFile = scanner.nextLine();
                while (scanner.hasNext()) {
                    allTextFromFile = allTextFromFile + scanner.nextLine();
                }
                return allTextFromFile;
            } catch (IOException e) { // можно ли здесь сделать throw?
                e.printStackTrace();
            }
        }
        return null;
    }
}
