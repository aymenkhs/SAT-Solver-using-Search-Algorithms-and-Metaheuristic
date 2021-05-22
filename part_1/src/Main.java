import algorithms.AStarHeuristic1;
import algorithms.A_Star;
import algorithms.Breadth_first_search;
import algorithms.depth_first_search;
import algorithms.sat_structure.Clause;
import algorithms.sat_structure.SAT;
import services.ReadCnfFile;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        SAT sat = SAT.createSAT("exemple1.cnf");

        depth_first_search algo1 = new depth_first_search(sat);
        Breadth_first_search algo2 = new Breadth_first_search(sat);
        A_Star algo3 = new AStarHeuristic1(sat);
        long startTime, endTime;
        ArrayList<Integer> tab;

        startTime = System.nanoTime( );
        tab = algo3.solve(sat.getNbVariables());
        endTime = System.nanoTime( );
        System.out.println( "A* " +  tab + " " + (endTime - startTime));

        startTime = System.nanoTime( );
        tab = algo1.solve(sat.getNbVariables());
        endTime = System.nanoTime( );
        System.out.println( "depth " +  tab + " " + (endTime - startTime));

        startTime = System.nanoTime( );
        tab = algo2.solve(sat.getNbVariables());
        endTime = System.nanoTime( );
        System.out.println( "Breadth " +  tab + " " + (endTime - startTime));
    }
}
