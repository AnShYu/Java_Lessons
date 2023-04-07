package ru.andrey.caraccidentreport.util;

import org.junit.Test;
import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.util.FileReaderUtil;

import java.io.File;

import static org.junit.Assert.*;

public class FileReaderUtilTest {

    @Test
    public void testReadFile() {
        File file = new File ("Files_for_Car_Accident_Report/Test_Files/Text_for_FileReaderUtilTest.txt");
        FileReaderUtil fru = new FileReaderUtil();
        try {
            String result = fru.readFile(file);
            assertEquals("Я ненавижу свет Однообразных звезд Здравствуй, мой давний бред, Башни стрельчатой рост.", result);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}