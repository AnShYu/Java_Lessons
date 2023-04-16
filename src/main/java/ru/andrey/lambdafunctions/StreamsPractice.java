package ru.andrey.lambdafunctions;

import java.io.File;
import java.util.*;

public class StreamsPractice {

    public static void main(String[] args) {
        // Задание 1.
        int[] numbers1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int result1 = sumSquares(numbers1);
        System.out.println(result1);

        // Задание 1 через Optional.
        int[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int result2 = sumSquaresOptional(numbers2);
        System.out.println(result2);


        // Задани 3.
        Candy c1 = new Candy("Twix", 95, "Snickers");
        Candy c2 = new Candy("Трюфель", 105, "Рот-Фронт");
        Candy c3 = new Candy("Коровка", 90, "Красный Октябрь");
        Candy c4 = new Candy("Ириска", 85, "Красный Октябрь");

        List<Candy> candies = new ArrayList<>();
        candies.add(c1);
        candies.add(c2);
        candies.add(c3);
        candies.add(c4);

        List<String> manufactorers = getManufacturersOfCandiesForLessThan100Rub(candies);
        for(String string: manufactorers) {
            System.out.println(string);
        }

    }


    // Задание 1.
    public static int sumSquares (int[] numbers) {
        int sumOfSquares = Arrays.stream(numbers)
                .filter(number -> number%2 == 0)
                .map(number -> {
                    int squareNumber = number*number;
                    return squareNumber;
                })
                .sum();
        return sumOfSquares;
    }


    // Задание 1 через Optional.
    public static int sumSquaresOptional (int[] numbers) {
        OptionalInt maybeSumOfSquares = Arrays.stream(numbers)
                .filter(number -> number%2 == 0)
                .map(number -> {
                    int squareNumber = number*number;
                    return squareNumber;
                })
                .reduce((number1, number2) -> number1 + number2);
        if (maybeSumOfSquares.isPresent()) {
            int sumOfSquares = maybeSumOfSquares.getAsInt();
            return sumOfSquares;
        } else {
            return 0;
        }
    }


    // Задание 2.
    public long calculateSizeOfAudioFiles (List<String> pathes) {
        long totalFilesSize = pathes.stream()
                .filter(path -> path.contains(".mp3"))
                .map(path -> {
                    File file = new File(path);
                    long length = file.length();
                    return  length;
                })
                .reduce(0l, (fileSize1, fileSize2) -> fileSize1 + fileSize2);
        return totalFilesSize;
    }


    // Задание 3.
    public static List<String> getManufacturersOfCandiesForLessThan100Rub (List<Candy> candies) {

        List<String> manufacturers = candies.stream()
                .peek(candy -> System.out.println(candy.getName()))
                .filter(candy -> candy.getPrice() < 100f)
                .map(candy -> {
                    String manufacturer = candy.getManufactorer();
                    return manufacturer;
                })
                .distinct()
                .sorted()
                .toList();
        return manufacturers;
    }
}
