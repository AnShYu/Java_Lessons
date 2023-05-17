package ru.andrey.problemsolver.additioncalculator;

import java.util.Deque;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ProblemSolver extends Thread {

    private static volatile ArrayBlockingQueue<Problem> problemsToSolve;
    private static volatile ArrayBlockingQueue<Integer> intermediateResults;
    private static volatile int numberOfProblems;
    private static volatile AtomicInteger counter = new AtomicInteger();

    @Override
    public void run() {
        while (counter.incrementAndGet()<numberOfProblems + 1) {
            try {
                Problem problem = problemsToSolve.take();
                int result = problem.getX() + problem.getY();
                intermediateResults.put(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setProblemsToSolve(ArrayBlockingQueue<Problem> problemsToSolve) {
        ProblemSolver.problemsToSolve = problemsToSolve;
    }

    public static void setIntermediateResults(ArrayBlockingQueue<Integer> intermediateResults) {
        ProblemSolver.intermediateResults = intermediateResults;
    }

    public static void setNumberOfProblems(int numberOfProblems) {
        ProblemSolver.numberOfProblems = numberOfProblems;
    }
}
