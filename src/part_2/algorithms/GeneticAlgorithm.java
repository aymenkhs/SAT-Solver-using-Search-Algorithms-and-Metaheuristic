package part_2.algorithms;

import utils.Algorithm;

import java.util.ArrayList;

public class GeneticAlgorithm implements Algorithm {

    // Algorithm information variables (execution time, have we found a solution...etc)
    private double time;
    private ArrayList<Integer> solution = null;
    private boolean goal_reached = false;

    // genetic algorithm parameters
    // if "numberGenerations" = -1 then their wil be no limit for the number of iteration
    private final int numberGenerations, populationSize;

    public GeneticAlgorithm(int populationSize) {
        this.populationSize = populationSize;
        // the default value of the number of generation is -1, in this case their wil be no limit for the number of iteration
        this.numberGenerations = -1;
    }

    public GeneticAlgorithm(int populationSize, int numberGenerations) {
        this.populationSize = populationSize;
        // if the the number of generation is specified then wer're gonna do at most "numberGenerations" iterations
        this.numberGenerations = numberGenerations;
    }

    @Override
    public void solve() {

    }

    @Override
    public double getTime() {
        return this.time;
    }

    @Override
    public ArrayList<Integer> getSolution() {
        return this.solution;
    }

    @Override
    public boolean isGoal_reached() {
        return this.goal_reached;
    }

    @Override
    public double getPercent_done() {
        return 0;
    }

    @Override
    public boolean isSatisfiable() {
        return this.goal_reached;
    }

}
