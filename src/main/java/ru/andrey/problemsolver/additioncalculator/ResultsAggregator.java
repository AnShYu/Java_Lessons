package ru.andrey.problemsolver.additioncalculator;

import java.util.Deque;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ResultsAggregator extends Thread {

    private static ArrayBlockingQueue<Integer> intermediateResults;
    private static AtomicInteger totalResult = new AtomicInteger();
    private static int numberOfIntermediateResults;
    private static volatile AtomicInteger counter = new AtomicInteger();

    private static CountDownLatch allFinishLatch;

    @Override
    public void run() {
        while(counter.incrementAndGet()<numberOfIntermediateResults + 1) {
            try {
                int intermediateResult = intermediateResults.take();
                totalResult.addAndGet(intermediateResult);
                allFinishLatch.countDown();
//                System.out.println(Thread.currentThread().getName() + " посчитал сумму промежуточных результатов " + totalResult.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setIntermediateResults(ArrayBlockingQueue<Integer> intermediateResults) {
        ResultsAggregator.intermediateResults = intermediateResults;
    }

    public static void setNumberOfIntermediateResults(int numberOfIntermediateResults) {
        ResultsAggregator.numberOfIntermediateResults = numberOfIntermediateResults;
    }

    public static void setAllFinishLatch(CountDownLatch allFinishLatch) {
        ResultsAggregator.allFinishLatch = allFinishLatch;
    }

    public static AtomicInteger getTotalResult() {
        return totalResult;
    }
}
