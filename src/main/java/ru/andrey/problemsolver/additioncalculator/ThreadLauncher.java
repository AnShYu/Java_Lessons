package ru.andrey.problemsolver.additioncalculator;

import java.util.List;

public class ThreadLauncher {

    public static <T extends Thread> void launchThreads (List<T> threads) {
        for (T thread: threads) {
            thread.start();
        }
    }

}
