package ru.andrey.caraccidentreport.parsing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParserUtil {

    public static List<String> parseText (String text, Pattern pattern) {
        List<String> parsedPartOfText = parseText(text, pattern, "all_text");
        return parsedPartOfText;
    }

    // This method allows to define which group of the pattern (if there are groups with names) shall be used for parsing
    public static List<String> parseText (String text, Pattern pattern, String groupName) {
        Matcher matcher = pattern.matcher(text);
        List<String> parsedPartOfText = new ArrayList<>();
        if (matcher.find()) {
            matcher.reset();
            while (matcher.find()) {
                if (groupName.equals("all_text")) {
                    parsedPartOfText.add(matcher.group());
                } else {
                    parsedPartOfText.add(matcher.group(groupName));
                }
            }
            return parsedPartOfText;
        } else {
            return Collections.emptyList();
        }
    }
}
