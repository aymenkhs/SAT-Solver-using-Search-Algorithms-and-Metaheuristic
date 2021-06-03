package part_1.algorithms;

import utils.sat_structure.Clause;
import utils.sat_structure.SAT;

import java.util.HashSet;

public class AStarHeuristic4 extends A_Star {

    public AStarHeuristic4(SAT sat) {
        super(sat);
    }

    @Override
    protected int heuristic(Node node) {
        HashSet<Clause> set = new HashSet<>(node.getSatisfiableClauseVar());
        set.removeAll(node.getSatisfiedClausesNode());
        return set.size();
    }
}
