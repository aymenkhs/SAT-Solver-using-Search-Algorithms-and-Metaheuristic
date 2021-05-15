import algorithms.Breadth_first_search;
import algorithms.depth_first_search;
import algorithms.sat_structure.Clause;
import algorithms.sat_structure.SAT;
import services.ReadCnfFile;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        SAT sat = SAT.createSAT("benchmark/uf75-01.cnf");

        Breadth_first_search algo1 = new Breadth_first_search(sat);
        System.out.println( "breadth " +  algo1.solve(sat.getNbVariables()));
        depth_first_search algo2 = new depth_first_search(sat);
        System.out.println( "depth " +  algo2.solve(sat.getNbVariables()));
    }
}
