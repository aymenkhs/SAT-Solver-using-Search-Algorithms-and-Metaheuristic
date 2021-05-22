package algorithms;

import algorithms.sat_structure.SAT;

import java.util.ArrayList;

public abstract class A_Star extends Search_Algorithm{

    public A_Star(SAT sat) {
        super(sat);
    }

    @Override
    protected Node set_Node_Value(Node node) {
        int H = heuristic(node);
        int G = node.getSatisfiedClauses().size();
        node.setValue(G + H);
        return node;
    }

    @Override
    protected ArrayList<Node> insert_sorted_open(ArrayList<Node> open, Node node) {
        int i = 0;
        while(i < open.size() && open.get(i).getValue() > node.getValue()){
            i++;
        }
        open.add(i, node);
        return open;
    }

    protected abstract int heuristic(Node node);
}
