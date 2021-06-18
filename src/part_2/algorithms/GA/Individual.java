package part_2.algorithms.GA;

import utils.sat_structure.SAT;

import java.util.ArrayList;
import java.util.Random;

public class Individual {

    private ArrayList<Integer> genome;

    private int score;

    // chance of a mutation happening
    private final static float percentageMutation = 0.10f;

    public Individual(ArrayList<Integer> genome) {
        this.genome = genome;
    }

    public void fitnessFunction(SAT sat){
        this.score = (int) sat.get_nb_verified_clauses(genome);
    }

    public void mutation(){
        // we're gonna change some value in the genome with a specified probability
        Random rand = new Random();
        float chance = rand.nextFloat();
        if (chance <= percentageMutation){
            int value = rand.nextInt(genome.size());
            int valueSet = genome.get(value) == 0 ? 1 : 0;
            genome.set(value, valueSet);
        }
    }

    public Individual crossOver(Individual individual){
        // we're gonna use a single point crossover to merge two individuals
        int breakPoint = genome.size()/2;
        ArrayList<Integer> newGenome = new ArrayList<>();
        newGenome.addAll(new ArrayList<>( this.genome.subList(0,breakPoint)));
        newGenome.addAll(new ArrayList<>( individual.getSolution().subList(breakPoint,genome.size())));
        return new Individual(newGenome);
    }

    public ArrayList<Integer> getSolution() {
        return genome;
    }

    public static Individual generateIndividual(int nbVars){
        Random rand = new Random();
        ArrayList<Integer> genome = new ArrayList<>(  );

        for (int i=0; i<nbVars; i++){
            genome.add(rand.nextInt(2));
        }
        return new Individual(genome);
    }

    public int getScore() {
        return score;
    }
}
