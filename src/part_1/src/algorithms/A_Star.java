package part_1.src.algorithms;

import java.util.ArrayList;

public class A_Star extends Search_Algorithm{

    @Override
    protected Node set_Node_Value(Node node) {

        return node;
    }

    @Override
    protected ArrayList<Node> insert_sorted_open(ArrayList<Node> open, Node node) {

        return open;
    }
}
