package ru.andrey.caraccidentreport.parsing;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class TextParserUtilTest {

    @Test
    public void testCorrectString() {
        TextParserUtil tpu = new TextParserUtil();
        String text = "18.06.2021";
        String regex = "(?<date>\\d{2}\\.\\d{2}\\.\\d{4})";
        Pattern pattern = Pattern.compile(regex);
        List<String> results = tpu.parseText(text, pattern, "date");
        String elementOfResultsList = results.get(0);
        assertEquals("18.06.2021", elementOfResultsList);
    }

    @Test
    public void testFindStringInText() {
        TextParserUtil tpu = new TextParserUtil();
        String text = "Мы поедем, мы помчимся на оленях утром ранним 18.06.2021г.! И отчаянно ворвемся прямо в снежную зарю!";
        String regex = "(?<date>\\d{2}\\.\\d{2}\\.\\d{4})";
        Pattern pattern = Pattern.compile(regex);
        List<String> results = tpu.parseText(text, pattern, "date");
        String elementOfResultsList = results.get(0);
        assertEquals("18.06.2021", elementOfResultsList); // можно ли как-то по-другому проверить list?-------------
    }

    @Test
    public void testReturnEmptyList() {
        TextParserUtil tpu = new TextParserUtil();
        String text = "Мы поедем, мы помчимся на оленях утром ранним! И отчаянно ворвемся прямо в снежную зарю!";
        String regex = "(?<date>\\d{2}\\.\\d{2}\\.\\d{4})";
        Pattern pattern = Pattern.compile(regex);
        List<String> results = tpu.parseText(text, pattern, "date");
        assertEquals(Collections.EMPTY_LIST, results);
    }

    @Test
    public void testTwoMatchingStrings() {
        TextParserUtil tpu = new TextParserUtil();
        String text = "Мы поедем, мы помчимся на оленях утром ранним 18.06.2021г.! И отчаянно ворвемся 23.09.2023 г. прямо в снежную зарю!";
        String regex = "(?<date>\\d{2}\\.\\d{2}\\.\\d{4})";
        Pattern pattern = Pattern.compile(regex);
        List<String> results = tpu.parseText(text, pattern, "date");
        String elementOfResultsList = results.get(0);
        assertEquals("18.06.2021", elementOfResultsList);
    }
}