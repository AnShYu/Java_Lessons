package ru.andrey.newgym;

public class UnlimitedArray<T> {
    private int arraySize = 10;
    private int counter = 0;
    private Object[] array = new Object[arraySize];

    public void add(T element) {
        if (counter >= arraySize) {
            Object[] biggerArray = new Object[arraySize + 10];
            for (int i = 0; i < arraySize; i++) {
                biggerArray[i] = array[i];
            }
            array = biggerArray;
            arraySize = arraySize + 10;
        }
        array[counter] = element;
        counter++;
    }

    public T get(int i) {
        T element = (T) array[i];
        return element;
    }

    public int size() {
        int i = 0;
        while (array[i] != null) {
            i++;
        }
        return i;
    }

    public void showAllElements () {
        for (int i = 0; i<size(); i++) {
            System.out.println(get(i));
        }
    }

//    public static void main(String[] args) {
//        UnlimitedArray<String> myArray = new UnlimitedArray<>();
//        myArray.add("1. фва");
//        myArray.add("2. фва");
//        myArray.add("3. фва");
//
//        System.out.println(myArray.size());
//        System.out.println(myArray.get(2));
//        System.out.println("  ");
//        myArray.showAllElements();
//
//    }

}
