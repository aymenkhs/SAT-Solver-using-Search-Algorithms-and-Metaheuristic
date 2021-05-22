import algorithms.*;
import algorithms.sat_structure.Clause;
import algorithms.sat_structure.SAT;
import services.ReadCnfFile;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        SAT sat = SAT.createSAT("exemple1.cnf");

        depth_first_search algo1 = new depth_first_search(sat);
        Breadth_first_search algo2 = new Breadth_first_search(sat);
        A_Star algo3 = new AStarHeuristic2(sat);
        long time;

        time = algo3.solve_time();
        System.out.println( "A* " + time);

        time = algo1.solve_time();
        System.out.println( "depth " + time);

        time = algo2.solve_time();
        System.out.println( "Breadth " + time);
    }
}
