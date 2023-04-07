package ru.andrey.caraccidentreport.util;

import org.junit.Assert;
import org.junit.Test;
import ru.andrey.caraccidentreport.exceptions.DataAccessException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class FileWriterUtilTest {

    @Test
    public void writeListOfStringsToTheFile() {
        File file = new File("Files_for_Car_Accident_Report/Test_Files/File_for_writing_test.txt");

        List<String> list = new ArrayList<>();
        list.add("1ая строчка...");
        list.add("2ая строчка...");
        list.add("3ья строчка...");

        FileWriterUtil.writeListOfStringsToTheFile(list, file);

        try (Scanner scanner = new Scanner(file)) {
            String allTextFromFile = scanner.nextLine();
            while (scanner.hasNext()) {
                allTextFromFile = allTextFromFile + scanner.nextLine();
            }
            assertEquals("1ая строчка...2ая строчка...3ья строчка...", allTextFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}