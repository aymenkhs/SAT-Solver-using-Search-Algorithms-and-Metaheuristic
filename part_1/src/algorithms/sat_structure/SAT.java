package algorithms.sat_structure;

import java.util.ArrayList;

public class SAT {

    private int n, m;
    private ArrayList<ArrayList<Clause>> structureSAT;

    public SAT(int n, int m, ArrayList<Clause> clauses) {
        this.n = n;
        this.m = m;
        // cree la structure SAT a patire des clauses
        this.SATcreation(clauses);
    }

    private void SATcreation(ArrayList<Clause> clauses){
        structureSAT = new ArrayList<>();
    }
}
