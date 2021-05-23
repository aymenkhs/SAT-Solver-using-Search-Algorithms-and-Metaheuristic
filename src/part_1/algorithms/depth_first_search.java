package part_1.algorithms;

import part_1.algorithms.sat_structure.SAT;

import java.util.ArrayList;

public class depth_first_search extends Search_Algorithm{

    public depth_first_search(SAT sat) {
        super(sat);
    }

    @Override
    protected Node set_Node_Value(Node node) {

        // the value of the node is it's depth
        node.setValue(node.getDepth());
        return node;
    }

    @Override
    protected ArrayList<Node> insert_sorted_open(ArrayList<Node> open, Node node) {

        int i = 0;

        // we insert in a sorted list décroissant by depth

        while (i < open.size() && node.getDepth() < open.get(i).getDepth()){
            i++;}

        open.add(i, node);

        return open;
    }
}
