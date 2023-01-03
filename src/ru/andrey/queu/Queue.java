package ru.andrey.queu;

public class Queue<T> {
    private int queueLength = 10;
    private int counter = 0;
    private Object[] queue = new Object[queueLength];

    public void enqueue(T o) {
        if (counter >= queueLength) {
            Object[] longerQueue = new Object[queueLength + 10];
            for (int i = 0; i < queueLength; i++) {
                longerQueue[i] = queue[i];
            }
            queue = longerQueue;
            queueLength = queueLength + 10;
        }
        queue[counter] = o;
        counter++;
    }

    public T dequeue() {
        if (counter == 0) {
            return null;
        }
        else {
            T value = (T) queue[0];
            for (int i = 0; i <= counter; i++) {
                queue[i] = queue[i + 1];
            }
            counter--;
            return value;
        }
    }

}
