package ru.andrey.problemsolver.additioncalculator;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ProblemMaker extends Thread {

    private static volatile int numberOfProblems;
    private static long pause;
    private static volatile ArrayBlockingQueue<Problem> madeProblems;
    private static volatile AtomicInteger counter = new AtomicInteger();

    @Override
    public void run() {
        Random rand = new Random();
        while (counter.incrementAndGet()<numberOfProblems + 1) {
            int x = rand.nextInt(100);
            int y = rand.nextInt(100);
            Problem problem = new Problem(x, y);
            try {
                madeProblems.put(problem);
//                System.out.println(Thread.currentThread().getName() + " Создал и положил проблему: " + problem);
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

    public static void setMadeProblems(ArrayBlockingQueue<Problem> madeProblems) {
        ProblemMaker.madeProblems = madeProblems;
    }

}
