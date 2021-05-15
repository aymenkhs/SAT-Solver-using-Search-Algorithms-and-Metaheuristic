package algorithms;

import algorithms.sat_structure.SAT;

import java.util.ArrayList;

public class Breadth_first_search extends Search_Algorithm{


    public Breadth_first_search(SAT sat) {
        super(sat);
    }

    @Override
    protected Node set_Node_Value(Node node) {

        /* we don't need a value, we just insert it at the end with
         FIFO in insert_sorted*/
        return node;
    }

    @Override
    protected ArrayList<Node> insert_sorted_open(ArrayList<Node> open, Node node) {

        // we insert at the end to follow FIFO
        open.add(node);
        return open;
    }
}
