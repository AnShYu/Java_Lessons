package ru.andrey.mergeSort;

import ru.andrey.gym.Coach;

import java.util.concurrent.ThreadLocalRandom;

public class MergeSortDemo {
    private int arraySize;

    int[] array = new int[arraySize];

    public MergeSortDemo(int arraySize) {
        this.arraySize = arraySize;
        for (int i: array) {
            i = ThreadLocalRandom.current().nextInt(-100, 100);
        }
    }

    // Метод для заполнения массива случайными числами
//    public void fillInTheArray(int[] array) {
//        for (int i: array) {
//            i = ThreadLocalRandom.current().nextInt(-200, 200);
//        }
//    }

    // Метод для получения массива из класса
    public int[] convertToArray () {
        return array;
    }

    // Основной метод - сортировка слиянием
    public int[] mergeSort(int[] array) {
        if (array.length == 1) return array; // можно ли поменять условие на проверку отсортированности? И как в if писать сложное условие? Можно ли метод в методе прописывать?
        int[] leftHalf = new int[array.length / 2];
        for (int i = 0; i < leftHalf.length; i++) {
            leftHalf[i] = array[i];
        }
        int[] rightHalf = new int[array.length - leftHalf.length];
        for (int i = 0; i < rightHalf.length; i++) {
            rightHalf[i] = array[leftHalf.length + i];
        }
        mergeSort(leftHalf); // что здесь происходит? Не нужно писать leftHalf[] = mergerSort(leftHalf) (так вообще сработает? или нужно через for loop все значения передавать)?
        mergeSort(rightHalf);
        int[] mergedArray = new int[leftHalf.length + rightHalf.length];
        // ниже каунтеры для того, чтобы считать, какой элемент мерджЭррэй заполняем, и какие элементы правой и левой половин сравниваем
        int counterForMergedArray = 0;
        int counterForLeftHalf = 0;
        int counterForRightHalf = 0;
        for (int i = 0; i < mergedArray.length; i++) {
            if (counterForLeftHalf <= (leftHalf.length - 1) && counterForRightHalf <= (rightHalf.length - 1)) {
                // если наши каунтеры еще не превысили размеры левого и правого массива, то смотрим, в каком массиве меньшее число - его добавляем мерджЭррэй
                if (leftHalf[counterForLeftHalf] >= rightHalf[counterForRightHalf]) {
                    mergedArray[counterForMergedArray] = leftHalf[counterForLeftHalf];
                    counterForMergedArray++;
                    counterForLeftHalf++;
                }
                if (leftHalf[counterForLeftHalf] < rightHalf[counterForRightHalf]) {
                    mergedArray[counterForMergedArray] = rightHalf[counterForRightHalf];
                    counterForMergedArray++;
                    counterForRightHalf++;
                }
//                else {
//                    // ошибка
//                }
            }
            if (counterForLeftHalf > (leftHalf.length - 1) && counterForRightHalf <= (rightHalf.length - 1)) {
                // если левый массив уже весь выбран, то вся оставшаяся часть правого массива уходит в мерджЭррэй
                for (int j = counterForMergedArray; j < (mergedArray.length - 1); j++) {
                    mergedArray[j] = rightHalf[j];
                    counterForRightHalf++; // возможно, это уже лишнее
                }
            }
            if (counterForRightHalf > (rightHalf.length - 1) && counterForLeftHalf <= (leftHalf.length - 1)) {
                // всю оставшуюся левую часть в мердж
            }
//            if (counterForRightHalf >= (rightHalf.length - 1) && counterForLeftHalf >= (leftHalf.length - 1)) {
//                // ошибка
//            }
//            if (counterForMergedArray > (mergedArray.length - 1)) {
//                // ошибка
//            }
        }
        return mergedArray;
    }


    // Метод для проверки массива на то, отсортирован он или нет
//    public boolean checkIfSorted (int[] array) {
//        boolean isSorted = false;
//        for (int i: array) {
//            if (array.length == 1) isSorted = true;
//            if (array[i] <= array[i+1]) isSorted = true;
//            else isSorted = false;
//        }
//        return isSorted;
//    }
    public static void main(String[] args) {
        MergeSortDemo demo = new MergeSortDemo(10);
        int[] array = demo.convertToArray();
        for (int i: array) {
            System.out.print(i + " ");
        }
        System.out.println(" ");
        demo.mergeSort(array);
        for (int i: array) {
            System.out.print(array[i] + " ");
        }
    }

}