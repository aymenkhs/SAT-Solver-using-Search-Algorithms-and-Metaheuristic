package part_1.algorithms;


import utils.Algorithm;
import utils.sat_structure.SAT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public abstract class Search_Algorithm implements Algorithm {

    protected SAT sat;

    protected boolean isSatisfiable;

    protected double time;

    protected ArrayList<Integer> solution = null;

    protected int limit = -1;

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

    @Override
    public void solve(){

        long startTime = System.nanoTime( );

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
                this.solution = node.getVars();
                return;
            }

            // if it's not a goal

            if (node.getDepth() < sat.getNbVariables() && node.getDepth() != limit ) {
                // if we are at depth max or we reached the limit then we don't developpe this node
                // the limit is a value that can be specified by DFS Algorithm.

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


        long endTime = System.nanoTime();
        long results =  (endTime - startTime);
        this.time = (double) results/1000000000;
    }

    @Override
    public boolean isSatisfiable() {
        return isSatisfiable;
    }

    @Override
    public ArrayList<Integer> getSolution() {

        if (solution == null){
            System.out.println( "Either the solution doesn't exist (isn't satisfiable) or you didn't run the solve method" );
        }

        return solution;
    }

    protected int binarySearch(LinkedList<Node> open, int value){
        int a = 0, b = open.size() - 1;
        int c = (a + b)/2;

        while (a <= b) {
            c = (a + b)/2;
            if (open.get(c).getValue() == value){
                return c;
            } else if (open.get(c).getValue() < value){
                b = c-1;
            } else {
                a = c+1;
            }
        }

        return open.get(c).getValue() < value ? c : c + 1;
    }

    @Override
    public double getTime() {
        return time;
    }

    @Override
    public boolean isGoal_reached() { return true; }

    @Override
    public double getPercent_done() {
        return 100;
    }
}