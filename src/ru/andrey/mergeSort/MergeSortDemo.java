package ru.andrey.mergeSort;

import ru.andrey.gym.Coach;

import java.util.concurrent.ThreadLocalRandom;

public class MergeSortDemo {
//    private int arraySize;
//
//    int[] array = new int[arraySize];
//
//    public MergeSortDemo(int arraySize) {
//        this.arraySize = arraySize;
//        for (int i = 0; i < arraySize; i++) {
//            array[i] = ThreadLocalRandom.current().nextInt(-100, 100);
//        }
//    }

    // Метод для заполнения массива случайными числами
//    public void fillInTheArray(int[] array) {
//        for (int i: array) {
//            i = ThreadLocalRandom.current().nextInt(-200, 200);
//        }
//    }

//    // Метод для получения массива из класса
//    public int[] convertToArray () {
//        return array;
//    }

    // Основной метод - сортировка слиянием
    public void mergeSort(int[] array) {
        if (array.length == 1) return; // можно ли поменять условие на проверку отсортированности? И как в if писать сложное условие? Можно ли метод в методе прописывать?
        int[] leftHalf = new int[array.length / 2];
        for (int i = 0; i < leftHalf.length; i++) {
            leftHalf[i] = array[i];
        }
        int[] rightHalf = new int[array.length - leftHalf.length];
        for (int i = 0; i < rightHalf.length; i++) {
            rightHalf[i] = array[leftHalf.length + i];
        }
        mergeSort(leftHalf); // что здесь происходит? Не нужно писать leftHalf[] = mergerSort(leftHalf). Правильно я понимаю, что если пишешь void, то метод меняет уже существующий массив. А если возвращает int[], то создает новый массив, да (а что происходит с тем, что ты на вход дал?)? (так вообще сработает? или нужно через for loop все значения передавать)?
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
                    mergedArray[counterForMergedArray] = rightHalf[counterForRightHalf];
                    counterForMergedArray++;
                    counterForRightHalf++;
                }
                else if (leftHalf[counterForLeftHalf] < rightHalf[counterForRightHalf]) {
                    mergedArray[counterForMergedArray] = leftHalf[counterForLeftHalf];
                    counterForMergedArray++;
                    counterForLeftHalf++;
                }
//                else {
//                    // ошибка
//                }
            }
            else if (counterForLeftHalf > (leftHalf.length - 1) && counterForRightHalf <= (rightHalf.length - 1)) {
                // если левый массив уже весь выбран, то вся оставшаяся часть правого массива уходит в мерджЭррэй
                while (counterForMergedArray < mergedArray.length) {
                    mergedArray[counterForMergedArray] = rightHalf[counterForRightHalf];
                    counterForRightHalf++;
                    counterForMergedArray++;
                }
            }
            else if (counterForRightHalf > (rightHalf.length - 1) && counterForLeftHalf <= (leftHalf.length - 1)) {
                // всю оставшуюся левую часть в мердж
                while (counterForMergedArray < mergedArray.length) {
                    mergedArray[counterForMergedArray] = leftHalf[counterForLeftHalf];
                    counterForLeftHalf++;
                    counterForMergedArray++;
                }
            }
//            else if (counterForRightHalf >= (rightHalf.length - 1) && counterForLeftHalf >= (leftHalf.length - 1)) {
//                // ошибка
//            }
//            else if (counterForMergedArray > (mergedArray.length - 1)) {
//                // ошибка
//            }
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = mergedArray[i];
        }
    }

    public void change (int[] array) {
        int a = array[0];
        array[0] = array[2];
        array[2] = a;
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
        MergeSortDemo demo = new MergeSortDemo();
//        int[] array = {2, 1, 7, 0, 22, 18, -9};// demo.convertToArray();
        int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(-100, 100);
        }
        for (int i: array) {
            System.out.print(i + " ");
        }
        System.out.println(" ");
        demo.mergeSort(array);
//        demo.change(array);
//        for (int i: array) { // в for-each не компилируется
//            System.out.print(array[i] + " ");
//        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}