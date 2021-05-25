package part_1;

import part_1.algorithms.*;
import part_1.algorithms.sat_structure.SAT;
import part_1.services.ReadBenchmark;
import part_1.services.WriteCSV;

public class Main {
    public static void main(String[] args){
        WriteCSV.writeAStar(ReadBenchmark.readBenchmark20());
        WriteCSV.writeDFS(ReadBenchmark.readBenchmark20(), 4, 20, 4);
        WriteCSV.writeBFS(ReadBenchmark.readBenchmark20());
        SAT sat = SAT.createSAT("benchmarks20/uf20-01.cnf");

        depth_first_search algo1 = new depth_first_search(sat);
        Breadth_first_search algo2 = new Breadth_first_search(sat);
        A_Star algo3 = new AStarHeuristic2(sat);
        double time;

        time = algo3.solve_time();
        System.out.println( "A* " + time);

        time = algo1.solve_time();
        System.out.println( "depth " + time);

        time = algo2.solve_time();
        System.out.println( "Breadth " + time);
    }
}
