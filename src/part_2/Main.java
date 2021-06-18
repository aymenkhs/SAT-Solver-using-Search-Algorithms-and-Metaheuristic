package part_2;

import part_2.algorithms.PSO.PSO;
import utils.sat_structure.SAT;

public class Main {

    public static void main(String[] args) {


        SAT sat = SAT.createSAT("benchmarks\\benchmarks20\\uf20-01.cnf");

        PSO pso = new PSO(sat, 30, 1,1.5,0.5,1, 10000 );

        /*
        GeneticAlgorithm ga = new GeneticAlgorithm(sat, 100, 10000);
        ga.solve();

        System.out.println("Time : " + ga.getTime());
        System.out.println("Goal reached : " + ga.isGoal_reached());
        System.out.println("Solution : " + ga.getSolution());
        System.out.println(ga.getPercent_done() + "% of clauses were resolved");
        */


        pso.solve();

        System.out.println("Time : " + pso.getTime());
        System.out.println("Goal reached : " + pso.isGoal_reached());
        System.out.println("Solution : " + pso.getSolution());
        System.out.println(pso.getPercent_done() + "% of clauses were resolved");


    }
}
