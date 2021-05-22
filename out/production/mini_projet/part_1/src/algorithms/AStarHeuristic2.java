package algorithms;

import algorithms.sat_structure.Clause;
import algorithms.sat_structure.SAT;

import java.util.HashSet;

public class AStarHeuristic2 extends A_Star {

    public AStarHeuristic2(SAT sat) {
        super(sat);
    }

    @Override
    protected int heuristic(Node node) {
        int current = node.getCurrentVariable();
        if (current == 1){
            HashSet<Clause> set = new HashSet<>(sat.get(current + 1));
            set.addAll(node.getSatisfiedClauses());
            return set.size();
        } else {
            HashSet<Clause> set = new HashSet<>(sat.get(-(current + 1)));
            set.addAll(node.getSatisfiedClauses());
            return set.size();
        }
    }
}
