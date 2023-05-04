package ru.andrey.problemsolver.additioncalculator;

public class Problem {
    int x;
    int y;

    public Problem(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
