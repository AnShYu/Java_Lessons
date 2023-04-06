package ru.andrey.caraccidentreport.util;

import ru.andrey.caraccidentreport.exceptions.DataAccessException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReaderUtil {

    public static String readFile (File file) throws DataAccessException {
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                String allTextFromFile = scanner.nextLine();
                while (scanner.hasNext()) {
                    allTextFromFile = allTextFromFile + " " + scanner.nextLine();
                }
                return allTextFromFile;
            } catch (IOException e) {
                throw new DataAccessException("No File Access", e);
            }
        }
        throw new DataAccessException("No File Access");
    }
}
