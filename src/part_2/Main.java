package part_2;

import part_2.algorithms.PSO;
import sun.applet.AppletSecurity;
import utils.sat_structure.SAT;

public class Main {

    public static void main(String[] args) {

        SAT sat = SAT.createSAT("benchmarks\\benchmarks20\\uf20-01.cnf");
        PSO pso = new PSO(sat, 10, 1,1,1,1, 100000 );
        pso.solve();

        System.out.println("Time : " + pso.getTime());
        System.out.println("Goal reached : " + pso.isGoal_reached());
        System.out.println("Solution : " + pso.getSolution());
        System.out.println(pso.getPercent_done() + "% of clauses were resolved");

    }
}
