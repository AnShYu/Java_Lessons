package ru.andrey.bubbleSort;

import java.util.Arrays;

public class SumArray {

    public static void main(String[] args) {
        int[] num = {1, 5, 7, 2, 3};
        System.out.println(sumArrayRec(num));
    }

    static int sumArray(int[] numbers) {
        int sum = 0;
        for (int num: numbers) {
            sum += num;
        }
        return sum;
    }

    static int sumArrayRec (int[] numbers) {
        for (int n: numbers) {
            System.out.print(n + " ");
        }
        System.out.println(" ");
        if (numbers.length == 1) return numbers[0];
        numbers[numbers.length - 2] = numbers[numbers.length - 2] + numbers[numbers.length - 1];
        int newArray[] = Arrays.copyOfRange(numbers, 0, numbers.length - 1);
        return sumArrayRec(newArray);
    }
}
