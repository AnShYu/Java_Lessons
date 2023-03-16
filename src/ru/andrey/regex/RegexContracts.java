package ru.andrey.regex;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexContracts {
//    Презюмирую, что в файле тема каждого нового письма записана с новой строки
//    Презюмирую, что каждая новая тема письма - это новый договор
    public static void main(String[] args) {
        File file = new File("Files_for_REGEX/File_with_Contracts.txt");

        List<String> list = readAllLinesFromFile(file);
        String regex = "[А-Я][а-яё]*\\s[А-Я][а-яё]*\\s[А-Я][а-яё]*\\s[0-3][0-9]\\.([0-1][0-9])\\.[\\d]{4}\\sдоговор\\s([А-Я][а-яА-ЯёЁ]*)";

        int[] months = new int[13];

        // Map for keeping number of contracts with firms. Key is firm name, value is number of contracts
        Map<String, Integer> map = new HashMap<>();

        for (String line: list) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                Integer month = Integer.parseInt(matcher.group(1));
                months[month] = months[month] + 1;

                String firmName = matcher.group(2);
                if(!map.containsKey(firmName)) {
                    map.put(firmName, 1);
                } else {
                    map.put(firmName, map.get(firmName) + 1);
                }
            }
        }

        for (String firm: map.keySet()) {
            System.out.println(firm + ": " + map.get(firm));
        }

        int maxNumberOfContracts = 0;
        for (int value: months) {
            if (value > maxNumberOfContracts) {
                maxNumberOfContracts = value;
            }
        }

        List<Integer> listOfMonthsWithMaxContracts = new ArrayList<>();
        for (int i = 0; i<months.length; i++) {
            if (months[i] == maxNumberOfContracts) {
                listOfMonthsWithMaxContracts.add(i);
            }
        }

        System.out.println("Месяцы с наибольшим количеством заключенных договоров: ");
        for (Integer value: listOfMonthsWithMaxContracts) {
            switch (value) {
                case 1:
                    System.out.println("Январь");
                    break;
                case 2:
                    System.out.println("Февраль");
                    break;
                case 3:
                    System.out.println("Март");
                    break;
                case 4:
                    System.out.println("Апрель");
                    break;
                case 5:
                    System.out.println("Май");
                    break;
                case 6:
                    System.out.println("Июнь");
                    break;
                case 7:
                    System.out.println("Июль");
                    break;
                case 8:
                    System.out.println("Август");
                    break;
                case 9:
                    System.out.println("Сентябрь");
                    break;
                case 10:
                    System.out.println("Октябрь");
                    break;
                case 11:
                    System.out.println("Ноябрь");
                    break;
                case 12:
                    System.out.println("Декабрь");
                    break;
            }
        }
    }

    private static List<String> readAllLinesFromFile (File file) {

        List<String> list = new ArrayList<>();

        if (file.exists()) {
            String filePath = file.getPath(); // Нужно ли тут закрывать файл?
            Path path = Paths.get(filePath); // его не вносим в трай?
            try (Scanner scanner = new Scanner(path)) {
                String line = scanner.nextLine();
                while (scanner.hasNext()) {
                    list.add(line);
                    line = scanner.nextLine();
                }
                list.add(line);
                return list;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();

    }
}
