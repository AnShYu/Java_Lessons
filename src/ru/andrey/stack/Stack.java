package ru.andrey.stack;

public class Stack<T> {
    private int stackHight = 10;
    private int counter = 0;
    private Object[] stack = new Object[stackHight];

    public void push (Object o) {
        if (counter >= stackHight) {
            Object[] higherStack = new Object[stackHight + 10];
            for (int i = 0; i < stackHight; i++) {
                higherStack[i] = stack[i];
            }
            stack = higherStack;
            stackHight = stackHight + 10;
        }
        stack[counter] = (T) o;
        counter++;
    }

    public T pop () {
        T returnableValue;
        if (counter == 0) {
            return null;
        }
        else {
            returnableValue = (T) stack[counter - 1];
        };
        stack[counter - 1] = null;
        int index = counter - 1;
        if (index != 0) {
            counter--;
        }
        return returnableValue;
    }

}
