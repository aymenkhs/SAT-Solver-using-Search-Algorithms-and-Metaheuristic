package part_1.algorithms;


import part_1.algorithms.sat_structure.Clause;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Node {

    private final ArrayList<Integer> vars;

    // this is the depth, it may be used as a heuristic alone (in the case of the breadth first search)
    private final int depth;

    // value is the f(x) value
    private  int value;

    private Set<Clause> satisfiedClauses;

    public Node() {
        this.vars = new ArrayList<>();
        this.depth = 0;
        this.satisfiedClauses = new HashSet<>();
    }

    public Node(Node node, int var_value, HashSet<Clause> satisfiableClauseVar){
        this.depth = node.getDepth() + 1;
        this.vars = new ArrayList<>(node.getVars());
        this.satisfiedClauses = new HashSet<>(node.getSatisfiedClauses());
        this.vars.add(var_value);
        this.satisfiedClauses.addAll(satisfiableClauseVar);
    }

    public ArrayList<Integer> getVars() {
        return vars;
    }

    public Set<Clause> getSatisfiedClauses() {
        return satisfiedClauses;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public int getDepth() {
        return depth;
    }
}
