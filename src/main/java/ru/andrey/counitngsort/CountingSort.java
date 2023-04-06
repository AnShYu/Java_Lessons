package ru.andrey.counitngsort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CountingSort {

    public static void main(String[] args) {

        int[] mainArray = {2, 2, 9, 3, 7, 5, 4, 6, 2, 9, 6, 8, 3, 1, 1, 9, 2, 5}; // Unsorted Array. Each element can be >=0 and <10
        System.out.print("Unsorted Array: ");
        for (int element: mainArray) {
            System.out.print(element + " ");
        }
        System.out.println();

        int[] temporaryArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // 10 elements

        for (int element: mainArray) {
            temporaryArray[element]++;
        }

        int startingPoint = 0; // index at which we start to fill in the mainArray
        for (int i = 0; i < temporaryArray.length; i++) {
            if (temporaryArray[i] !=0) {
                for (int j = 0; j<temporaryArray[i]; j++) {
                    mainArray[startingPoint] = i;
                    startingPoint++;
                }
            }
        }

        System.out.print("Sorted Array:   ");
        for (int element: mainArray) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
