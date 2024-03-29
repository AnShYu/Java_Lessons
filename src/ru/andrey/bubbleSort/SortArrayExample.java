package ru.andrey.bubbleSort;

public class SortArrayExample {

    public <T extends Comparable> void sortArray(T[] array) {
        int counter;
        do {
            counter = 0;
            for (int i = 0; i < (array.length - 1); i++) {
                if (array[i].compareTo(array[i+1]) > 0) {
                    T currentElement = array[i];
                    array[i] = array[i+1];
                    array[i+1] = currentElement;
                    counter++;
                }
            }
        } while (counter > 0);
    }

    public static void main(String[] args) {
        Integer[] testArray = {-2, 1, 7, 9, 20, 0, 8};

//        Student st1 = new Student("Ivan", "Ivanov", 19);
//        Student st2 = new Student ("Ivan", "Petrov", 20);
//        Student st3 = new Student ("Ivan", "Sidorov", 17);
//        Student[] testArray = {st1, st2, st3};

//        Name n1 = new Name("Andrey");
//        Name n2 = new Name("Ivan");
//        Name n3 = new Name("Anna");
//        Name n4 = new Name ("Sergey");
//        Name[] testArray = {n1, n2, n3, n4};

        SortArrayExample sortArrayExample = new SortArrayExample();
        sortArrayExample.sortArray(testArray);
        for (int i = 0; i < testArray.length; i++) {
            System.out.println(testArray[i] + " ");
        }
    }
}








//        if (T instanceof Comparable) {
//            processBubbleSort(array);
//        }
//        else {
//            throw new NotComparableDataException("Входные данные не реализуют интерфейс Comparable");
//        }
//    }
//
//    public <T> void processBubbleSort(T[] arr) {
//
//    }
//
//}
