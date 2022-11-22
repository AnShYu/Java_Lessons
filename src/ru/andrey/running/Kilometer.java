package ru.andrey.running;

public class Kilometer {
    private float kilometers;

    public Kilometer (float kilometers) {
        this.kilometers = kilometers;
    }

    public float convertToFloat () {
        return (float) this.kilometers;
    }
}


