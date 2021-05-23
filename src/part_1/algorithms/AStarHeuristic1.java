package part_1.algorithms;

import part_1.algorithms.sat_structure.SAT;

public class AStarHeuristic1 extends A_Star {

    public AStarHeuristic1(SAT sat) {
        super(sat);
    }

    @Override
    protected int heuristic(Node node) {
        int current = node.getCurrentVariable();
        if (current == 1){
            return sat.get(current + 1).size();
        } else {
            return sat.get(-(current + 1)).size();
        }
    }
}
