package part_1;

import part_1.algorithms.AStarHeuristic2;
import part_1.algorithms.A_Star;
import part_1.algorithms.Breadth_first_search;
import part_1.algorithms.depth_first_search;
import utils.sat_structure.SAT;
import utils.ReadBenchmark;
import utils.Solve_and_Save;

public class Main {
    public static void main(String[] args){
        Solve_and_Save.solve_AStar(ReadBenchmark.readBenchmark20());
        Solve_and_Save.solve_DFS(ReadBenchmark.readBenchmark20(), 4, 20, 4);
        Solve_and_Save.solve_BFS(ReadBenchmark.readBenchmark20());
        SAT sat = SAT.createSAT("benchmarks20/uf20-01.cnf");



        depth_first_search algo1 = new depth_first_search(sat);
        Breadth_first_search algo2 = new Breadth_first_search(sat);
        A_Star algo3 = new AStarHeuristic2(sat);

        double time;

        algo3.solve_time();
        time = algo3.getTime();
        System.out.println( "A* " + time);

        algo1.solve_time();
        time = algo1.getTime();
        System.out.println( "depth " + time);

        algo2.solve_time();
        time = algo2.getTime();
        System.out.println( "Breadth " + time);
    }
}
