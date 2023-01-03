package ru.andrey.stack;

public class Stack<T> {
    private int stackHeight = 10;
    private int counter = 0;
    private Object[] stack = new Object[stackHeight];

    public void push (T o) {
        if (counter >= stackHeight) {
            Object[] higherStack = new Object[stackHeight + 10];
            for (int i = 0; i < stackHeight; i++) {
                higherStack[i] = stack[i];
            }
            stack = higherStack;
            stackHeight = stackHeight + 10;
        }
        stack[counter] = o;
        counter++;
    }

    public T pop () {
        if (counter == 0) {
            return null;
        } else {
            T value = (T) stack[counter - 1];
            counter--;
            return value;
        }
    }

}
