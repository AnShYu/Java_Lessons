package ru.andrey.problemsolver.additioncalculator;

import java.util.Deque;

public class ProblemSolver extends Thread {

    private static volatile Deque<Problem> problemsToSolve;
    private static volatile Deque<Integer> intermediateResults;
    private static int numberOfProblems;
    private static volatile int counter = 0;
    private final Object lock = new Object();

    @Override
    public void run() {
        while (counter<numberOfProblems) {
            synchronized (lock) {
                if (!problemsToSolve.isEmpty()) {
                    Problem problem = problemsToSolve.pop();
                    int result = problem.getX() + problem.getY();
                    intermediateResults.add(result);
                    counter++;
                }
            }
        }
    }

    public static void setProblemsToSolve(Deque<Problem> problemsToSolve) {
        ProblemSolver.problemsToSolve = problemsToSolve;
    }

    public static void setIntermediateResults(Deque<Integer> intermediateResults) {
        ProblemSolver.intermediateResults = intermediateResults;
    }

    public static void setNumberOfProblems(int numberOfProblems) {
        ProblemSolver.numberOfProblems = numberOfProblems;
    }
}
