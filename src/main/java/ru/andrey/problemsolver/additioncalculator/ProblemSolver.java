package ru.andrey.problemsolver.additioncalculator;

import java.util.Deque;

public class ProblemSolver extends Thread {

    private static volatile Deque<Problem> problemsToSolve;
    private static volatile Deque<Integer> intermediateResults;
    private static volatile int numberOfProblems;
    private static volatile int counter = 0;
    private static Object synchronizer;

    @Override
    public void run() {
        while (counter<numberOfProblems) {
            synchronized (synchronizer) {
                if (!problemsToSolve.isEmpty()) {
                    Problem problem = problemsToSolve.pop();
                    int result = problem.getX() + problem.getY();
//                    System.out.println(Thread.currentThread().getName() + " нашел промежуточный результат: " + result);
                    intermediateResults.add(result);
                    counter++;
//                    System.out.println(Thread.currentThread().getName() + " Сколько промежуточных результатов нужно найти " + numberOfProblems + " Какой сейчас каунтер: " + counter);
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

    public static void setSynchronizer(Object synchronizer) {
        ProblemSolver.synchronizer = synchronizer;
    }
}
