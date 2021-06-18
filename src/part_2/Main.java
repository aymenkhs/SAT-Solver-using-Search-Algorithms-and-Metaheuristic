package part_2;

import part_2.algorithms.PSO;
import sun.applet.AppletSecurity;
import utils.sat_structure.SAT;

public class Main {

    public static void main(String[] args) {

        //SAT sat = SAT.createSAT("benchmarks\\benchmarks20\\uf20-01.cnf");
        SAT sat = SAT.createSAT("benchmarks\\benchmarks75\\uf75-07.cnf");
        PSO pso = new PSO(sat, 30, 1,1.5,0.5,1, 10000 );
        pso.solve();

        System.out.println("Time : " + pso.getTime());
        System.out.println("Goal reached : " + pso.isGoal_reached());
        System.out.println("Solution : " + pso.getSolution());
        System.out.println(pso.getPercent_done() + "% of clauses were resolved");

    }
}
