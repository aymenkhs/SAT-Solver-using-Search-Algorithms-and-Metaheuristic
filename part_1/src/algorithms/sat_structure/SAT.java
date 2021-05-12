package algorithms.sat_structure;

import java.util.ArrayList;

public class SAT {

    private final int n, m;
    private ArrayList<ArrayList<Clause>> structureSAT;

    public SAT(int n, int m, ArrayList<Clause> clauses) {
        this.n = n;
        this.m = m;
        // cree la structure SAT a patire des clauses
        this.SATcreation(clauses);
    }

    private void SATcreation(ArrayList<Clause> clauses){
        ArrayList<Clause> temp;
        structureSAT = new ArrayList<>();
        int i=1;
        while(i<=n){
            temp = new ArrayList<>();
            for (Clause clause: clauses){
                if (clause.contains(i)){
                    temp.add(clause);
                }
            }
            structureSAT.add(temp);
            if (i<0){
                i = -i + 1;
            } else {
                i = -i;
            }
        }
    }

    public ArrayList<Clause> get(int i){
        int index = i>0?(i-1)*2:-2*i+1;
        return structureSAT.get(index);
    }
}
