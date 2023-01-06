package ru.andrey.running;

import java.util.Scanner;

// This class allows to create an array on integers of unlimited size
public class UnlimitedArray {
    private int arraySize = 10;
    private int counter = 0;
    private int[]myArray = new int[arraySize];

    // Method to add integers to the array
    public void add(int integerToAdd) {
        if (counter >= arraySize) {
            int[] longerArray = new int[arraySize + 10];
            for (int i = 0; i < arraySize; i++) {
                longerArray[i] = myArray[i];
            }
            myArray = longerArray;
            arraySize = arraySize + 10;
        }
        myArray[counter] = integerToAdd;
        counter++;
    }

    // НЕ СРАБОТАЛО. ВИДИМО, НЕЛЬЗЯ ПРОСТО УВЕЛИЧИТЬ РАЗМЕР МАССИВА В []
//    public void add(int integerToAdd) {
//        if (counter >= arraySize) {
//            arraySize = arraySize + 10;
//        }
//        myArray[counter] = integerToAdd;
//        counter++;
//    }

    // Method to return value of member of the array of the given index
    public int returnValue (int index) {
        return myArray[index];
    }

    //Демо для проверки работы
    public static void main(String[] args) {
        UnlimitedArray myArray = new UnlimitedArray();
        Scanner scanner = new Scanner(System.in);
        int numbersInArray = scanner.nextInt();
        for (int i = 0; i < numbersInArray; i++) {
            myArray.add(i);
            System.out.println(myArray.returnValue(i));
        }
    }
}

