package part_1.algorithms;

import part_1.algorithms.A_Star;
import part_1.algorithms.sat_structure.Clause;
import part_1.algorithms.sat_structure.SAT;

import java.util.HashSet;

public class AStarHeuristic2 extends A_Star {

    public AStarHeuristic2(SAT sat) {
        super(sat);
    }

    @Override
    protected int heuristic(Node node) {
        HashSet<Clause> set = new HashSet<>(node.getSatisfiableClauseVar());
        set.removeAll(node.getSatisfiedClausesNode());
        return set.size();
    }
}
