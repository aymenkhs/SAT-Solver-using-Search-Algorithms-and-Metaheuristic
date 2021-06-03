package part_1.algorithms;

import utils.sat_structure.SAT;

public class AStarHeuristic5 extends A_Star {

    public AStarHeuristic5(SAT sat) {
        super(sat);
    }

    @Override
    protected int heuristic(Node node) {
        return node.getSatisfiedClauses().size();
    }
}