package ru.andrey.lambdafunctions;

import java.util.Optional;
import java.util.Scanner;

public class OptionalPractice {

    public static void main(String[] args) {
        // Задание 4.
        Optional<String> maybeName = getName();
        if (maybeName.isPresent()) {
            String name = maybeName.get();
            System.out.println("Привет, " + name + "!");
        } else {
            System.out.println("Привет, незнакомец!");
        }
    }


    // Задание 4.
    public static Optional<String> getName () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя");
        String userInput = scanner.nextLine();
        String name;
        if (!userInput.equals("")) {
            name = userInput;
        } else {
            name = null;
        }
        return Optional.ofNullable(name);
    }
}
