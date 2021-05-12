import algorithms.sat_structure.Clause;
import algorithms.sat_structure.SAT;
import services.ReadCnfFile;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        ReadCnfFile CNFFile = new ReadCnfFile("part_1/benchmark/uf75-01.cnf");
        System.out.println(CNFFile.numClauses + " " + CNFFile.numVariables);
        //System.out.println(CNFFile.listClauses);

        ArrayList<Clause> clauses = new ArrayList<>();
        for(String clauseString : CNFFile.listClauses){
            clauses.add(new Clause(CNFFile.listClauses.indexOf(clauseString), clauseString));
        }
        System.out.println(clauses.get(0).getLiterals());

        SAT sat = new SAT(CNFFile.numVariables, CNFFile.numClauses, clauses);
        System.out.println(sat.get(0));
        
    }
}
