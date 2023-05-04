package ru.andrey.problemsolver.additioncalculator;

import java.util.Deque;

public class ResultsAggregator extends Thread {

    private static volatile Deque<Integer> intermediateResults;
    private static int TotalResult = 0;
    private static volatile int numberOfIntermediateResults;
    private static volatile int counter = 0;
    private static Object synchronizer;

    @Override
    public void run() {
        while(counter<numberOfIntermediateResults) {
            synchronized (synchronizer) {
                if(!intermediateResults.isEmpty()) {
                    int intermediateResult = intermediateResults.pop();
                    TotalResult = TotalResult + intermediateResult;
                    System.out.println(Thread.currentThread().getName() + " посчитал сумму промежуточных результатов " + TotalResult);
                    counter++;
                    System.out.println(Thread.currentThread().getName() + " Сколько результатов нужно сложить " + numberOfIntermediateResults + " Какой сейчас каунтер: " + counter);
                }
            }
        }
    }

    public static void setIntermediateResults(Deque<Integer> intermediateResults) {
        ResultsAggregator.intermediateResults = intermediateResults;
    }

    public static void setNumberOfIntermediateResults(int numberOfIntermediateResults) {
        ResultsAggregator.numberOfIntermediateResults = numberOfIntermediateResults;
    }

    public static void setSynchronizer(Object synchronizer) {
        ResultsAggregator.synchronizer = synchronizer;
    }
}
