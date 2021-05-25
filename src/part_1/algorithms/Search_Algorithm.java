package part_1.algorithms;


import part_1.algorithms.sat_structure.SAT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public abstract class Search_Algorithm {

    protected SAT sat;

    protected boolean isSatisfiable;

    public Search_Algorithm(SAT sat) {
        this.sat = sat;
    }

    // set the node value to sort it
    protected abstract Node set_Node_Value(Node node);

    // open is a sorted list
    protected abstract LinkedList<Node> insert_sorted_open(LinkedList<Node> open , Node node);


    private boolean is_goal(Node node){

        return node.getSatisfiedClauses().size() == sat.getNbClauses();
    }

    public ArrayList<Integer> solve(){

        LinkedList<Node> open = new LinkedList<>();

        // initial node is a vector of null
        Node initial_node = new Node();

        open.add(initial_node);

        while( ! open.isEmpty()) {

            Node node = open.get(0);
            //System.out.println( node.getDepth() + " " + node.getValue()  + " " + node.getVars() + " " +  node.getSatisfiedClauses() + node.getSatisfiedClausesNode() + node.getSatisfiableClauseVar() );
            open.remove(0);

            if (is_goal(node)) {
                this.isSatisfiable = true;
                return node.getVars();
            }

            if (node.getDepth() < sat.getNbVariables()) {

                // if it's not a goal
                Node node_true, node_false;

                node_true = new Node(node, 1, new HashSet<>(sat.get(node.getDepth()+1)));
                node_false = new Node(node, 0, new HashSet<>(sat.get(-(node.getDepth()+1))));

                node_true = set_Node_Value(node_true);
                node_false = set_Node_Value(node_false);

                open = insert_sorted_open(open, node_true);
                open = insert_sorted_open(open, node_false);

            }
        }

        // Solution not found
        System.out.println("Not satisfiable");
        return  null;
    }

    public long solve_time(){
        long startTime = System.nanoTime( );
        this.solve();
        long endTime = System.nanoTime( );
        return endTime - startTime;
    }

    public boolean isSatisfiable() {
        return isSatisfiable;
    }
}