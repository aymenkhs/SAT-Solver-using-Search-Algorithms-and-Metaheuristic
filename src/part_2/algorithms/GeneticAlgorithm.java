package part_2.algorithms;

import utils.Algorithm;
import utils.sat_structure.SAT;

import java.util.ArrayList;
import java.util.Collections;

public class GeneticAlgorithm implements Algorithm {

    // Algorithm information variables (execution time, have we found a solution...etc)
    private double time;
    private ArrayList<Integer> solution = null;
    private boolean goal_reached = false;

    //sat instance
    private final SAT sat;

    // genetic algorithm parameters
    // if "numberGenerations" = -1 then their wil be no limit for the number of iteration
    private final int numberGenerations, populationSize;

    private ArrayList<Individual> population, childrens;
    private int currentGeneration = 0;
    private Individual bestIndividual;

    public GeneticAlgorithm(SAT sat, int populationSize) {
        this.sat = sat;
        this.populationSize = populationSize;
        // the default value of the number of generation is -1, in this case their wil be no limit for the number of iteration
        this.numberGenerations = -1;
    }

    public GeneticAlgorithm(SAT sat, int populationSize, int numberGenerations) {
        this.sat = sat;
        this.populationSize = populationSize;
        // if the the number of generation is specified then wer're gonna do at most "numberGenerations" iterations
        this.numberGenerations = numberGenerations;
    }

    private void geneticAlgorithm(){
        // first we initialize the population then we evaluate it, sort it and take the best individual
        this.currentGeneration = 0;
        initializePopulation();
        evaluatePopulation();
        sortingPopulation();
        this.bestIndividual = population.get(0);

        while (!stopCondition()){
            this.childrens = new ArrayList<>(  );

            ArrayList<Individual> selectedParents = selection();
            // we make a crossover between each of the selected parents
            // we make mutation between to the children generation
            // we evaluate the childrens then sort them
            population = childrens;
            evaluatePopulation();
            sortingPopulation();

            // if the best individual of this generation is better then the current best one then we take it as our best individual
        }
    }

    private Individual crossOver(){
        // we're gonna use a single point crossover to merge two individuals
        return new Individual(new ArrayList<>(  ));
    }

    private ArrayList<Individual> selection(){
        int toSelection = 10;

        ArrayList<Individual> selected = new ArrayList<>(  );
        int i = 0;
        while (i<toSelection){
            selected.add(this.population.get(i));
            i++;
        }
        return selected;
    }

    private void evaluatePopulation(){
        for (Individual individual: population){
            individual.fitnessFunction(sat);
        }
    }

    private void sortingPopulation(){
        Collections.sort(this.population, new IndividualComparator());
    }

    private void initializePopulation(){
        this.population = new ArrayList<>(  );
        for(int i=0; i<this.populationSize; i++){
            this.population.add(Individual.generateIndividual(sat.getNbVariables()));
        }
    }

    private boolean stopCondition(){
        if (goal_reached){
            return true;
        } else if (this.numberGenerations == -1){
            return false;
        } else {
            return this.numberGenerations > this.currentGeneration;
        }
    }

    @Override
    public void solve() {
        long startTime = System.nanoTime();

        geneticAlgorithm();

        long endTime = System.nanoTime();

        long results =  (endTime - startTime);
        this.time = (double) results/1000000000;

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
