package ru.andrey.problemsolver.additioncalculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class AdditionCalculator {

    private static volatile Deque<Problem> problems = new ArrayDeque<>();
    private static volatile Deque<Integer> intermediateResults = new ArrayDeque<>();
    private static int numberOfProblems;
    private static Object lock = new Object();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество задач:");
        numberOfProblems = scanner.nextInt();
        System.out.println("Введите интервал между созданием задач (сек.):");
        long pause = scanner.nextLong()*1000l;
        System.out.println("Введите количество потоков каждого типа:");
        int numberOfThreads = scanner.nextInt();

        ProblemMaker.setNumberOfProblems(numberOfProblems);
        ProblemMaker.setPause(pause);
        ProblemMaker.setMadeProblems(problems);
        ProblemMaker.setSynchronizer(lock);

        ProblemSolver.setProblemsToSolve(problems);
        ProblemSolver.setIntermediateResults(intermediateResults);
        ProblemSolver.setNumberOfProblems(numberOfProblems);
        ProblemSolver.setSynchronizer(lock);

        ResultsAggregator.setIntermediateResults(intermediateResults);
        ResultsAggregator.setNumberOfIntermediateResults(numberOfProblems);
        ResultsAggregator.setSynchronizer(lock);

        ThreadFactory factory = new ThreadFactory();
        factory.createInstances(ThreadType.PROBLEMMAKER, numberOfThreads);
        factory.createInstances(ThreadType.PROBLEMSOLVER, numberOfThreads);
        factory.createInstances(ThreadType.RESULTSAGGREGATOR, numberOfThreads);
        ThreadLauncher.launchAllThreads(factory);
    }

}
