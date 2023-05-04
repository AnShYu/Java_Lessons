package ru.andrey.problemsolver.additioncalculator;


import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ProblemMaker extends Thread {

    private static volatile int numberOfProblems;
    private static long pause;
    private static volatile Deque<Problem> madeProblems;
    private static volatile int counter = 0;
    private static Object synchronizer;

    @Override
    public void run() {
        Random rand = new Random();
        while (counter<numberOfProblems) {
            synchronized (synchronizer) {
                int x = rand.nextInt(100);
                int y = rand.nextInt(100);
                Problem problem = new Problem(x, y);
//                System.out.println(Thread.currentThread().getName() + " создал проблему: " + problem);
                madeProblems.add(problem);
                counter++;
//                System.out.println(Thread.currentThread().getName() + " Какое количество проблем нужно создать: " + numberOfProblems + " Какой сейчас каунтер: " + counter);
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

    public static void setSynchronizer(Object synchronizer) {
        ProblemMaker.synchronizer = synchronizer;
    }
}
