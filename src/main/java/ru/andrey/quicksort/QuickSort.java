package ru.andrey.quicksort;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    public static void main(String[] args) {

        // Create an array which shall be sorted
        int[] mainArray = new int[20];
        for (int i = 0; i < mainArray.length; i++) {
            mainArray[i] = ThreadLocalRandom.current().nextInt(-100, 100);
        }

        // Print the unsorted array on the screen
        System.out.print("Unsorted Array: ");
        for (int element: mainArray) {
            System.out.print(element + " ");
        }
        System.out.println();

        //Sort the array
        QuickSort demo = new QuickSort();
        demo.quickSort(mainArray);

        // Print the sorted array on the screen
        System.out.print("Sorted Array: ");
        for (int element: mainArray) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // Method which sorts the given array
    public void quickSort (int [] array) {
        int startingPoint = 0;
        int endPoint = array.length - 1;
        quickSortTheRange(array, startingPoint, endPoint);
    }

    // Method which sorts a specific range of the given array
    public void quickSortTheRange (int[] array, int startingPoint, int endPoint) {

        // Base case when the recursion stops
        if (startingPoint == endPoint) return;
        // Save the value of the initial starting point (since int startingPoint will be used to count and will change it's value. But we still need to now the initial starting point)
        int initialStartingPoint = startingPoint;
        // Decide which element of the range will be used as frontier. I use the first element
        int frontier = array[startingPoint];

        // Count how many elements are lesser, how many elements are greater and how many elements equal to the frontier
        int counterOfLesserElements = 0;
        int counterOfFrontierElements = 0;
        int counterOfGreaterElements = 0;
        while (startingPoint != endPoint + 1) {
            if (array[startingPoint] < frontier) counterOfLesserElements++;
            else if (array[startingPoint] == frontier) counterOfFrontierElements++;
            else counterOfGreaterElements++;
            startingPoint++;
        }
        startingPoint = initialStartingPoint;
        int totalOfElements = counterOfLesserElements + counterOfFrontierElements + counterOfGreaterElements;

        // Create an array in which we will add numbers in the correct order - all lesser than frontier will be to the left
        int[] supplementaryArray = new int[totalOfElements];

        // Fill in the supplementaryArray
        int leftPosition = 0;
        int frontierPosition = counterOfLesserElements;
        int rightPosition = counterOfLesserElements + counterOfFrontierElements;

        while (startingPoint != endPoint + 1) {
            if (array[startingPoint] < frontier) {
                supplementaryArray[leftPosition] = array[startingPoint];
                leftPosition++;
            }
            else if (array[startingPoint] == frontier) {
                supplementaryArray[frontierPosition] = array[startingPoint];
                frontierPosition++;
            }
            else {
                supplementaryArray[rightPosition] = array[startingPoint];
                rightPosition++;
            }
            startingPoint++;
        }
        startingPoint = initialStartingPoint;

        // Make initial range equal to the supplementaryArray
       int supplementaryArrayPosition = 0;
        while (startingPoint != endPoint + 1) {
            array[startingPoint] = supplementaryArray[supplementaryArrayPosition];
            startingPoint++;
            supplementaryArrayPosition++;
        }

        // Recursive calls
        if (counterOfLesserElements != 0) {
            quickSortTheRange(array, initialStartingPoint, initialStartingPoint + counterOfLesserElements - 1);
        }
        if (counterOfGreaterElements != 0) {
            quickSortTheRange(array, initialStartingPoint + counterOfLesserElements + counterOfFrontierElements, initialStartingPoint + totalOfElements - 1);
        }
    }
}