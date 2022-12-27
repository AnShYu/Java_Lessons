package ru.andrey.queu;

public class Queu<T> {
    private int queuLength = 10;
    private int counter = 0;
    private Object[] queu = new Object[queuLength];

    public void enqueu (Object o) {
        if (counter >= queuLength) {
            Object[] longerQueu = new Object[queuLength + 10];
            for (int i = 0; i < queuLength; i++) {
                longerQueu[i] = queu[i];
            }
            queu = longerQueu;
            queuLength = queuLength + 10;
        }
        queu[counter] = (T) o;
        counter++;
    }

    public T dequeu () {
        T returnableValue;
        if (counter == 0) {
            return null;
        }
        else {
            returnableValue = (T) queu[0];
        }
        for (int i = 0; i <= counter; i++) {
            queu[i] = queu[i + 1];
        }
        int index = counter - 1;
        if (index != 0) {
            counter--;
        }
        return returnableValue;
    }

}
