package ru.andrey.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        String str3 = scanner.nextLine();
        String str4 = scanner.nextLine();
        List<String> list = new ArrayList<>();
        list.add(str1);
        list.add(str2);
        list.add(str3);
        list.add(str4);


        int matchCounter = 0;
        int longestStringLength = 0;
        String longestString = "No lines with \"рассвет\"";

        String regex = "[Рр]ассвет[а-яё]{0,3}";
        Pattern pattern = Pattern.compile(regex);
        for (String str: list) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                matchCounter++;
                if (str.length() > longestStringLength) {
                    longestStringLength = str.length();
                    longestString = str;

                }
            }
        }

        System.out.println("Lines with \"рассвет\": " + matchCounter);
        if (longestStringLength > 0) {
            System.out.println("The longest string with \"рассвет\" is: " + longestString);
        } else {
            System.out.println("There are no lines with \"рассвет\"");
        }
    }

}
