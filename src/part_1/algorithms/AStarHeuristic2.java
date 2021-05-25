package part_1.algorithms;

import part_1.algorithms.sat_structure.Clause;
import part_1.algorithms.sat_structure.SAT;

import java.util.HashSet;
import java.util.Set;

public class AStarHeuristic2 extends A_Star {

    public AStarHeuristic2(SAT sat) {
        super(sat);
    }

    @Override
    protected int heuristic(Node node) {
        Set<Clause> set = new HashSet<>(node.getSatisfiableClauseVar());

        if (node.getVars().get(node.getDepth()-1) == 1){
            set.addAll(sat.get(node.getDepth()));
        } else {
            set.addAll(sat.get(-node.getDepth()));
        }
        set.removeAll(node.getSatisfiedClausesNode());
        return set.size();
    }
}