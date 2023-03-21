package ru.andrey.caraccidentreport;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParserUtil {

    public static List<String> parseText (String text, Pattern pattern) {
        Matcher matcher = pattern.matcher(text);
        List<String> parsedPartOfText = new ArrayList<>();
        while (matcher.find()) {
            parsedPartOfText.add(matcher.group());
        }
        return  parsedPartOfText;
    }

    // This method allows to define which group of the pattern (if there are groups with names) shall be used for parsing
    public static List<String> parseText (String text, Pattern pattern, String groupName) {
        Matcher matcher = pattern.matcher(text);
        List<String> parsedPartOfText = new ArrayList<>();
        while (matcher.find()) {
            parsedPartOfText.add(matcher.group(groupName));
        }
        return  parsedPartOfText;
    }
}
