package part_2.algorithms;

import utils.sat_structure.SAT;

import java.util.ArrayList;
import java.util.Random;

public class Individual {

    private ArrayList<Integer> genome;

    private double score;

    public Individual(ArrayList<Integer> genome) {
        this.genome = genome;
    }

    public double fitnessFunction(SAT sat){
        this.score = sat.get_nb_verified_clauses(genome);
        return this.score;
    }

    private void mutation(){
        // we're gonna change some value in the genome with a specified probability
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
}
