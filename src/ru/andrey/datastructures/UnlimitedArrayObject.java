package ru.andrey.datastructures;

import java.util.Scanner;

public class UnlimitedArrayObject<T> {

    private int arraySize = 10;
    private int counter = 0;
    private Object[] myArray = new Object[arraySize];

    public void add(T o) {
        if (counter >= arraySize) {
            Object[] longerArray = new Object[arraySize + 10];
            for (int i = 0; i < arraySize; i++) {
                longerArray[i] = myArray[i];
            }
            myArray = longerArray; // можно ли так делать?
            arraySize = arraySize + 10; // сначала пробовал указать это выше в коде, но получалась ошибка (выход за пределы массива)
        }
        myArray[counter] = o;
        counter++;
    }


    // Method to return value of member of the array of the given index
    public T returnValue (int index) {
        return (T) myArray[index];
    }

    //Демо для проверки работы
    public static void main(String[] args) {
        UnlimitedArrayObject<String> myArray = new UnlimitedArrayObject<>();
        Scanner scanner = new Scanner(System.in);
        int numbersInArray = scanner.nextInt();
        for (int i = 0; i < numbersInArray; i++) {
            myArray.add("myString" + i);
            String str = myArray.returnValue(i);
            System.out.println(myArray.returnValue(i));
        }
    }

}
