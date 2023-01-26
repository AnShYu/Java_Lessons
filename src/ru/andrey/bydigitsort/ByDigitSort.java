package ru.andrey.bydigitsort;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ByDigitSort {
    public static void main(String[] args) {

        int[] initialArray = {123, 78, 556, 50, 14, 100};
//        int[] initialArray = {1, 4, 7, 6, 5};

        System.out.print("Initial Array: ");
        for (int element: initialArray) {
            System.out.print(element + " ");
        }
        System.out.println();

        List<Integer> arrayWithZeros = new ArrayList<>();
        List<Integer> arrayWithOnes = new ArrayList<>();

        for (int j = 1; j <=15; j++) {  // не знаю, сколько разрядов здесь можно максимально поставить (чтобы не выйти за пределы памяти). Может, сначала проверять числа на количество значимых разрядов?
            arrayWithZeros.clear();
            arrayWithOnes.clear();
            for (int element : initialArray) {
                if (getDigitByNumber(element, j) == 0) {
                    arrayWithZeros.add(element);
                } else if (getDigitByNumber(element, j) == 1) {
                    arrayWithOnes.add(element);
                }
            }
            if (arrayWithZeros.size() != 0) {
                for (int i = 0; i < arrayWithZeros.size(); i++) {
                    initialArray[i] = arrayWithZeros.get(i);
                }
            }

            if (arrayWithOnes.size() != 0) {
                for (int i = 0; i < arrayWithOnes.size(); i++) {
                    initialArray[arrayWithZeros.size() + i] = arrayWithOnes.get(i);
                }
            }
        }

        System.out.print("Sorted Array: ");
        for (int element: initialArray) {
            System.out.print(element + " ");
        }
        System.out.println();

    }

    static int getDigit (int number, int mask) {
        int bitAnd = number & mask;
        if (bitAnd == 0) return 0;
        else return 1;
    }

    static int getDigitByNumber (int number, int digitNumber) {
        int mask = 1;
        for (int i = 1; i < digitNumber; i++) {
            mask = mask * 2;
        }
        return getDigit(number, mask);
    }
}



//        int x = 60;
//        System.out.println("dceimal: " + x + " binary: " + Integer.toBinaryString(x));
//
//        for (int i = 1; i <= 10; i++) {
//            int num = getDigitByNumber(x, i);
//            System.out.println("index:" + i + " binary: " + num);
//        }

//        List<Integer> initialArray = new ArrayList<>(1);
//        initialArray.add(123);
//        initialArray.add(78);
//        initialArray.add(556);
//        initialArray.add(50);
//        initialArray.add(14);
//        initialArray.add(100);
//
//        System.out.print("Initial Array: ");
//        for (int element: initialArray) {
//            System.out.print(element + " ");
//        }
//        System.out.println();
//
//        List<Integer> arrayWIthZeros = new ArrayList<>(1);
//        List<Integer> arrayWithOnes = new ArrayList<>(1);
//
//        for (int j = 1; j <=15; j++) {
//            for (int element : initialArray) {
//                if (getDigitByNumber(element, 1) == 0) {
//                    arrayWIthZeros.add(element);
//                } else if (getDigitByNumber(element, 1) == 1) {
//                    arrayWithOnes.add(element);
//                }
//                for (int i = 0; i < arrayWIthZeros.size(); i++) {
//                    initialArray.add(i, arrayWIthZeros.get(i));
//                }
//                for (int i = 0; i < arrayWithOnes.size(); i++) {
//                    initialArray.add(arrayWIthZeros.size() + i, arrayWithOnes.get(i));
//                }
//            }
//        }
//
//        System.out.print("Sorted Array: ");
//        for (int element: initialArray) {
//            System.out.print(element + " ");
//        }
//        System.out.println();

