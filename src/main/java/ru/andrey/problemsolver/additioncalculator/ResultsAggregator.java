package ru.andrey.problemsolver.additioncalculator;

import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;

public class ResultsAggregator extends Thread {

    private static Deque<Integer> intermediateResults;
    private static AtomicInteger totalResult = new AtomicInteger();
    private static int numberOfIntermediateResults;
    private static volatile int counter = 0;
    private static Object synchronizer;

    @Override
    public void run() {
        while(counter<numberOfIntermediateResults) {
            int intermediateResult = 0;
            synchronized (synchronizer) {
                if (!intermediateResults.isEmpty()) {
                    intermediateResult = intermediateResults.remove();
                    counter++;
//                    System.out.println(Thread.currentThread().getName() + " Сколько результатов нужно сложить " + numberOfIntermediateResults + " Какой сейчас каунтер: " + counter);
                }
            }
            if (intermediateResult != 0) {
                totalResult.addAndGet(intermediateResult); // и будет ли здесь корректно?
                System.out.println(Thread.currentThread().getName() + " посчитал сумму промежуточных результатов " + totalResult.get()); // как вывести только финальный результат
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
