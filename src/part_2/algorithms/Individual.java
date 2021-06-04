package part_2.algorithms;

import java.util.ArrayList;

public class Individual {

    private ArrayList<Integer> genome;

    private int score;

    public Individual(ArrayList<Integer> genome) {
        this.genome = genome;
    }

    public double fitnessFunction(){
        return this.score;
    }
}
