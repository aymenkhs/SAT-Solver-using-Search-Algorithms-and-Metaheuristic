package part_1.src.algorithms;


import part_1.src.algorithms.sat_structure.Clause;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Node {

    private ArrayList<Integer> vars;

    // this is the depth, it may be used as a heuristic alone (in the case of the breadth first search)
    private int depth;

    // value is the f(x) value
    private  int value;

    private Set<Clause> satisfiedClauses;
    private int currentVariable;

    public Node(int number_of_vars) {
        this.vars = new ArrayList<>();

        for(int i = 0 ; i < number_of_vars ; i++ ){
            this.vars.add(null);
        }

        this.depth = 0;
        this.currentVariable = -1;
        this.satisfiedClauses = new HashSet<>();
    }

    public Node(Node node, int var_value, HashSet<Clause> satisfiableClauseVar){
        this.depth = node.getDepth() + 1;
        this.vars = new ArrayList<>(node.getVars());
        this.satisfiedClauses = new HashSet<>(node.getSatisfiedClauses());

        this.currentVariable = node.getCurrentVariable() + 1;
        if (currentVariable < vars.size()){
            this.vars.set(currentVariable, var_value);
            this.satisfiedClauses.addAll(satisfiableClauseVar);
        }

    }


    public ArrayList<Integer> getVars() {
        return vars;
    }

    public Set<Clause> getSatisfiedClauses() {
        return satisfiedClauses;
    }

    public int getCurrentVariable() {
        return currentVariable;
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

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
