package ru.andrey.problemsolver.additioncalculator;

import java.util.ArrayList;
import java.util.List;

public class ThreadCreator {

    public List<ProblemMaker> createProblemMakers (int numberOfInstances) {
        List<ProblemMaker> problemMakers = new ArrayList<>();
        for (int i = 0; i < numberOfInstances; i++) {
            problemMakers.add(new ProblemMaker());
        }
        return problemMakers;
    }

    public List<ProblemSolver> createProblemSolvers (int numberOfInstances) {
        List<ProblemSolver> problemSolvers = new ArrayList<>();
        for (int i = 0; i < numberOfInstances; i++) {
            problemSolvers.add(new ProblemSolver());
        }
        return problemSolvers;
    }

    public List<ResultsAggregator> createResultsAggregators (int numberOfInstances) {
        List<ResultsAggregator> resultsAggregators = new ArrayList<>();
        for (int i = 0; i < numberOfInstances; i++) {
            resultsAggregators.add(new ResultsAggregator());
        }
        return resultsAggregators;
    }


}
