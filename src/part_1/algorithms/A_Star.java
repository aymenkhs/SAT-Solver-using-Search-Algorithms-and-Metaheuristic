package part_1.algorithms;

import utils.sat_structure.SAT;

import java.util.LinkedList;

public abstract class A_Star extends Search_Algorithm {

    public A_Star(SAT sat) {
        super(sat);
    }

    @Override
    protected Node set_Node_Value(Node node) {

        int H = heuristic(node);
        int G = node.getSatisfiedClausesNode().size();
        node.setValue(G + H);
        return node;
    }

    @Override
    protected LinkedList<Node> insert_sorted_open(LinkedList<Node> open, Node node) {
        if (open.isEmpty()){
            open.add(node);
            return open;
        }

        int i = binarySearch(open, node.getValue());
        open.add(i, node);
        return open;
    }

    protected abstract int heuristic(Node node);
}
