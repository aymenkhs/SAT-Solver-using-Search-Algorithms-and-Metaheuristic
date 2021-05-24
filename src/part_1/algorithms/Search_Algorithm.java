package part_1.algorithms;

import part_1.algorithms.sat_structure.Clause;
import part_1.algorithms.sat_structure.SAT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Search_Algorithm {

    protected SAT sat;

    protected boolean isSatisfiable;

    public Search_Algorithm(SAT sat) {
        this.sat = sat;
    }

    // set the node value to sort it
    protected abstract Node set_Node_Value(Node node);

    // open is a sorted list
    protected abstract ArrayList<Node> insert_sorted_open(ArrayList<Node> open , Node node);


    private boolean is_goal(Node node){


        ArrayList<Integer>vars = node.getVars();
        Set<Clause> clausesSatisfaites = new HashSet<Clause>();

        for (int i = 0 ; i < node.getVars().size() ; i++){

            if(vars.get(i) == 1){
                clausesSatisfaites.addAll(sat.get(i+1));
            } else {
                clausesSatisfaites.addAll(sat.get(-(i+1)));
            }

            if (clausesSatisfaites.size() == sat.getNbClauses()){
                return true;
            }

        }
        return false;
    }

    public ArrayList<Integer> solve(){

        ArrayList<Node> open = new ArrayList<>();

        // initial node is a vector of null
        Node initial_node = new Node();

        open.add(initial_node);

        while( ! open.isEmpty()) {

            Node node = open.get(0);

            open.remove(0);

            if (is_goal(node)) {
                this.isSatisfiable = true;
                return node.getVars();
            }

            if (node.getDepth() < sat.getNbVariables()) {

                // if it's not a goal
                Node node_true, node_false;

                if (node.getDepth() < sat.getNbVariables()){
                    node_true = new Node(node, 1, new HashSet<>(sat.get(node.getDepth()+1)));
                } else {
                    node_true = new Node(node, 1, new HashSet<>());
                }

                if (node.getDepth() < sat.getNbVariables()){
                    node_false = new Node(node, 0, new HashSet<>(sat.get(-(node.getDepth()+1))));
                } else {
                    node_false = new Node(node, 0, new HashSet<>());
                }

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