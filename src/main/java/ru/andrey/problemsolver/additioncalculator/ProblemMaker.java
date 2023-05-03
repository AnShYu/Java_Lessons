package ru.andrey.problemsolver.additioncalculator;


import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class ProblemMaker extends Thread {

    private static int numberOfProblems;
    private static long pause;
    private static volatile Deque<Problem> madeProblems;
    private static volatile int counter = 0;
    private final Object lock = new Object();

    @Override
    public void run() {
        Random rand = new Random();
        while (counter<numberOfProblems) {
            synchronized (lock) {
                int x = rand.nextInt(100);
                int y = rand.nextInt(100);
                Problem problem = new Problem(x, y);
                madeProblems.add(problem);
                counter++;
            }
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void setNumberOfProblems(int numberOfProblems) {
        ProblemMaker.numberOfProblems = numberOfProblems;
    }

    public static void setPause(long pause) {
       ProblemMaker.pause = pause;
    }

    public static void setMadeProblems(Deque<Problem> madeProblems) {
        ProblemMaker.madeProblems = madeProblems;
    }
}
