package ru.andrey.problemsolver.additioncalculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class AdditionCalculator {
    // Как уйти от статик полей через создание специального класса?
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

        ThreadCreator creator = new ThreadCreator();
        List<ProblemMaker> problemMakers = creator.createProblemMakers(numberOfThreads);
        List<ProblemSolver> problemSolvers = creator.createProblemSolvers(numberOfThreads);
        List<ResultsAggregator> resultsAggregators = creator.createResultsAggregators(numberOfThreads);
        ThreadLauncher.launchThreads(problemMakers);
        ThreadLauncher.launchThreads(problemSolvers);
        ThreadLauncher.launchThreads(resultsAggregators);
    }

}