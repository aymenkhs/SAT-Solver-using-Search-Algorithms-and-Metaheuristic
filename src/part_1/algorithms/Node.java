package part_1.algorithms;


import utils.sat_structure.Clause;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Node {

    private final ArrayList<Integer> vars;

    // this is the depth, it may be used as a heuristic alone (in the case of the breadth first search)
    private final int depth;

    // value is the f(x) value
    private  int value;

    private final Set<Clause> satisfiedClausesNode;
    private Set<Clause> satisfiableClauseVar;

    public Node() {
        this.vars = new ArrayList<>();
        this.depth = 0;
        this.satisfiedClausesNode = new HashSet<>();
        this.satisfiableClauseVar = new HashSet<>();
    }

    public Node(Node node, int var_value, HashSet<Clause> satisfiableClauseVar){
        this.depth = node.getDepth() + 1;
        this.vars = new ArrayList<>(node.getVars());
        this.satisfiedClausesNode = new HashSet<>(node.getSatisfiedClauses());
        this.vars.add(var_value);
        this.satisfiableClauseVar = satisfiableClauseVar;
    }

    public ArrayList<Integer> getVars() {
        return vars;
    }

    public Set<Clause> getSatisfiedClauses() {
        Set<Clause> satisfiedClauses = new HashSet<>(satisfiedClausesNode);
        satisfiedClauses.addAll(satisfiableClauseVar);
        return satisfiedClauses;
    }

    public Set<Clause> getSatisfiedClausesNode() {
        return satisfiedClausesNode;
    }

    public Set<Clause> getSatisfiableClauseVar() {
        return satisfiableClauseVar;
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
