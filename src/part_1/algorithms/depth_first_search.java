package part_1.algorithms;

import utils.sat_structure.SAT;

import java.util.LinkedList;

public class depth_first_search extends Search_Algorithm{

    public depth_first_search(SAT sat) {
        super(sat);
    }


    public depth_first_search(SAT sat, int limitDepth) {
        super(sat);
        this.limit = limitDepth;
    }

    @Override
    protected Node set_Node_Value(Node node) {

        // the value of the node is it's depth
        node.setValue(node.getDepth());
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
}
