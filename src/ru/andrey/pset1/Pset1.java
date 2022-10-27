package ru.andrey.pset1;

import java.util.Scanner;

public class Pset1 {
    public static void main (String[] args) {

        // introduce class "scanner"
        Scanner scanner = new Scanner(System.in);

        // find out the user's input for "n"
        int total = scanner.nextInt();

        // make an array of numbers given by the user
        int[] arrayNumbers = new int[total];
        for (int i = 0; i < total; i++) {
            arrayNumbers[i] = scanner.nextInt();
        }

        // make an array of numbers from 1 to 100
        int[] array_hundred = new int[100];
        for (int i = 0; i < 100; i++) {
            array_hundred[i] = i + 1;
        }

        // make an array of counters and set all counters to 0
        int[] array_counters = new int[100];
        for (int i = 0; i < 100; i++) {
            array_counters[i] = 0;
        }

        // count how many times a number from 1 to 100 is used in the user's numbers
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < total; j++) {
                if (array_hundred[i] == arrayNumbers[j]) {
                    array_counters[i]++;
                }
            }
        }

        // print out how many times each number is used (if used at all)
        for (int i = 0; i < 100; i++) {
            if (array_counters[i] != 0) {
                System.out.println(array_hundred[i] + " " + array_counters[i]);
            }
        }
    }
}
