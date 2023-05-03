package ru.andrey.problemsolver.additioncalculator;

public class ThreadLauncher {

    public static void launch (ThreadFactory factory, ThreadType type) {
        switch (type) {
            case PROBLEMMAKER:
                for (ProblemMaker problemMaker : factory.getProblemMakers()) {
                    problemMaker.start();
                }
                break;
            case PROBLEMSOLVER:
                for (ProblemSolver problemSolver : factory.getProblemSolvers()) {
                    problemSolver.start();
                }
                break;
            case RESULTSAGGREGATOR:
                for (ResultsAggregator resultsAggregator : factory.getResultsAggregators()) {
                    resultsAggregator.start();
                }
                break;
        }
    }

    public static void launchAllThreads (ThreadFactory factory) {
        ThreadLauncher.launch(factory, ThreadType.PROBLEMMAKER);
        ThreadLauncher.launch(factory, ThreadType.PROBLEMSOLVER);
        ThreadLauncher.launch(factory, ThreadType.RESULTSAGGREGATOR);
    }

}
