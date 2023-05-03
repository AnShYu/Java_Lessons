package ru.andrey.problemsolver.additioncalculator;

import java.util.ArrayList;
import java.util.List;

public class ThreadFactory {

    private List<ProblemMaker> problemMakers = new ArrayList<>();
    private List<ProblemSolver> problemSolvers = new ArrayList<>();
    private List<ResultsAggregator> resultsAggregators = new ArrayList<>();

    public void createInstances (ThreadType type, int numberOfInstances) {
        switch (type) {
            case PROBLEMMAKER:
                for (int i = 0; i<numberOfInstances; i++) {
                    problemMakers.add(new ProblemMaker());
                }
                break;
            case PROBLEMSOLVER:
                for (int i = 0; i<numberOfInstances; i++) {
                    problemSolvers.add(new ProblemSolver());
                }
                break;
            case RESULTSAGGREGATOR:
                for (int i = 0; i<numberOfInstances; i++) {
                    resultsAggregators.add(new ResultsAggregator());
                }
                break;
        }
    }

    public List<ProblemMaker> getProblemMakers() {
        return problemMakers;
    }

    public List<ProblemSolver> getProblemSolvers() {
        return problemSolvers;
    }

    public List<ResultsAggregator> getResultsAggregators() {
        return resultsAggregators;
    }
}
