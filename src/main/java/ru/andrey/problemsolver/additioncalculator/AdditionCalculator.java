package ru.andrey.problemsolver.additioncalculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

public class AdditionCalculator {
    // Как уйти от статик полей через создание специального класса?

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество задач:");
        int numberOfProblems = scanner.nextInt();
        System.out.println("Введите интервал между созданием задач (сек.):");
        long pause = scanner.nextLong()*1000l;
        System.out.println("Введите количество потоков каждого типа:");
        int numberOfThreads = scanner.nextInt();

        ArrayBlockingQueue<Problem> problems = new ArrayBlockingQueue<>(numberOfProblems);
        ArrayBlockingQueue<Integer> intermediateResults = new ArrayBlockingQueue<>(numberOfProblems);

        ProblemMaker.setNumberOfProblems(numberOfProblems);
        ProblemMaker.setPause(pause);
        ProblemMaker.setMadeProblems(problems);

        ProblemSolver.setProblemsToSolve(problems);
        ProblemSolver.setIntermediateResults(intermediateResults);
        ProblemSolver.setNumberOfProblems(numberOfProblems);

        ResultsAggregator.setIntermediateResults(intermediateResults);
        ResultsAggregator.setNumberOfIntermediateResults(numberOfProblems);

        ThreadCreator creator = new ThreadCreator();
        List<ProblemMaker> problemMakers = creator.createProblemMakers(numberOfThreads);
        List<ProblemSolver> problemSolvers = creator.createProblemSolvers(numberOfThreads);
        List<ResultsAggregator> resultsAggregators = creator.createResultsAggregators(numberOfThreads);
        ThreadLauncher.launchThreads(problemMakers);
        ThreadLauncher.launchThreads(problemSolvers);
        ThreadLauncher.launchThreads(resultsAggregators);

        CountDownLatch allFinishLatch = new CountDownLatch(numberOfProblems);
        ResultsAggregator.setAllFinishLatch(allFinishLatch);
        allFinishLatch.await();
        System.out.println("Итоговый результат: " + ResultsAggregator.getTotalResult());
    }

}
