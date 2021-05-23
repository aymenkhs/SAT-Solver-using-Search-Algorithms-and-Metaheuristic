package part_1.src;

import part_1.src.algorithms.AStarHeuristic2;
import part_1.src.algorithms.Breadth_first_search;
import part_1.src.algorithms.depth_first_search;
import part_1.src.algorithms.sat_structure.A_Star;
import part_1.src.algorithms.sat_structure.SAT;
import part_1.src.services.ReadBenchmark;
import part_1.src.services.WriteCSV;

import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        HashMap<String,SAT> sats;

        WriteCSV.writeBFS(ReadBenchmark.readBenchmark());



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
