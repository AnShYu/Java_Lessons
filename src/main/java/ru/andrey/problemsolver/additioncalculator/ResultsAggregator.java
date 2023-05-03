package ru.andrey.problemsolver.additioncalculator;

import java.util.Deque;

public class ResultsAggregator extends Thread {

    private static volatile Deque<Integer> intermediateResults;
    private static int TotalResult = 0;
    private static int numberOfIntermediateResults;
    private static volatile int counter = 0;
    private final Object lock = new Object();

    @Override
    public void run() {
        while(counter<numberOfIntermediateResults) {
            synchronized (lock) {
                if(!intermediateResults.isEmpty()) {
                    int intermediateResult = intermediateResults.pop();
                    TotalResult = TotalResult + intermediateResult;
                    System.out.println(TotalResult);
                    counter++;
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
}
