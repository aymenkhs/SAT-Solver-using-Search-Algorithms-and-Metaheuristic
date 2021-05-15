package algorithms;

import algorithms.sat_structure.Clause;
import algorithms.sat_structure.SAT;
import services.ReadCnfFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public abstract class Search_Algorithm {

    protected SAT sat;
    /*
    public ArrayList<Integer> solve_time(int number_vars, ArrayList<Object> clauses){
        long startTime = System.nanoTime( );
        solve(number_vars, clauses);
        long endTime = System.nanoTime( );
        return null
    }
    */

    public Search_Algorithm(SAT sat) {
        this.sat = sat;
    }

    // set the node value to sort it
    protected abstract Node set_Node_Value(Node node);

    // open is a sorted list
    protected abstract ArrayList<Node> insert_sorted_open(ArrayList<Node> open , Node node);



    private boolean is_goal(Node node){


        // if the node is not completed, then it's not a goal
        // exemple : 1 0 1 0 0 null null null
        ArrayList<Integer>vars = node.getVars();
        Set<Clause> clausesSatisfaites = new HashSet<Clause>();

        for (int i = 0 ; i < node.getVars().size() ; i++){

            if(vars.get(i) == null){
                return false;
            }

            if(vars.get(i) == 1){
                clausesSatisfaites.addAll(sat.get(i+1));
            } else {
                clausesSatisfaites.addAll(sat.get(-(i+1)));
            }
        }

        // verify whether the node satisfies the SAT instance
        // note that the Node have a getValue and setValue methods

        return clausesSatisfaites.size() == sat.getNbClauses();
    }

    private boolean already_developed(Node node, ArrayList<Node> closed){

        for (Node n : closed) {

            if (node.getVars().equals(n.getVars())) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Integer> solve(int number_vars){


        ArrayList<Node> open = new ArrayList<>();
        ArrayList<Node> closed = new ArrayList<>();

        // initial node is a vector of null
        Node initial_node = new Node(number_vars);

        open.add(initial_node);

        while( ! open.isEmpty()) {

            Node node = open.get(0);
            System.out.println( node.getDepth() );
            open.remove(0);

            if (!already_developed(node, closed)) {

                closed.add(node);

                if (is_goal(node)) {

                    return node.getVars();
                }

                // if it's not a goal or it's not completed develop node
                ArrayList<Integer> vars = node.getVars();

                Node node_true = new Node(vars, 1, node.getDepth() + 1);
                Node node_false = new Node(vars, 0, node.getDepth() + 1);

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


}