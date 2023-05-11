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
        while (counter<numberOfProblems) {  // Здесь каунтер не отсечет нужное количесвто. Он нужен для остановки программы. Передача нужного количества задач обеспечивается через проверку на null. При использовании БО не понял, как ограничить количество вызовов.
            Problem problem = null;
            synchronized (synchronizer) {
                if (!problemsToSolve.isEmpty()) {
                    problem = problemsToSolve.remove();
                    counter++;
                }
            }
            if (problem != null) {
                int result = problem.getX() + problem.getY();

                synchronized (synchronizer) {
                    intermediateResults.add(result);
//                    System.out.println(Thread.currentThread().getName() + " нашел и передал промежуточный результат: " + result);
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
