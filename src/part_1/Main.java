package part_1;

import part_1.algorithms.*;
import part_1.algorithms.sat_structure.SAT;
import part_1.services.ReadBenchmark;
import part_1.services.WriteCSV;

import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        //WriteCSV.writeBFS(ReadBenchmark.readBenchmark25());

        SAT sat = SAT.createSAT("benchmarks/uf20-01.cnf");

        depth_first_search algo1 = new depth_first_search(sat);
        Breadth_first_search algo2 = new Breadth_first_search(sat);
        A_Star algo3 = new AStarHeuristic1(sat);
        long time;

        time = algo3.solve_time();
        System.out.println( "A* " + time);

        time = algo1.solve_time();
        System.out.println( "depth " + time);

        time = algo2.solve_time();
        System.out.println( "Breadth " + time);
    }
}
