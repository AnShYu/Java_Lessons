package ru.andrey.problemsolver.additioncalculator;


import java.util.Deque;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ProblemMaker extends Thread {

    private static volatile int numberOfProblems;
    private static long pause;
    private static volatile Deque<Problem> madeProblems;
    private static volatile AtomicInteger counter = new AtomicInteger();
    private static Object synchronizer;

    @Override
    public void run() {
        Random rand = new Random();
        while (counter.incrementAndGet()<numberOfProblems + 1) {
            int x = rand.nextInt(100);
            int y = rand.nextInt(100);
            Problem problem = new Problem(x, y);
            synchronized (synchronizer) {
                madeProblems.add(problem);
//                System.out.println(Thread.currentThread().getName() + " создал и передал проблему: " + problem);
            }
            try {
                Thread.sleep(pause); // Зачем нужен sleep?
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

    public static void setSynchronizer(Object synchronizer) {
        ProblemMaker.synchronizer = synchronizer;
    }
}
