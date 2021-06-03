package utils;

import java.util.ArrayList;

public interface Algorithm {

    // get execution time
    double getTime();
    // get solution
    ArrayList<Integer> getSolution();
    // is the solution found
    boolean isGoal_reached();
    // percentage of clauses satisfied
    double getPercent_done();
    // solution found, but is it satisfiable
    boolean isSatisfiable();
    // solve
    void solve();
}
