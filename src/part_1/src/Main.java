package part_1.src;

import part_1.src.algorithms.sat_structure.Clause;
import part_1.src.algorithms.sat_structure.SAT;
import part_1.src.services.ReadCnfFile;
import java.util.ArrayList;



public class Main{
    public static void main(String[] args){

        System.out.println(System.getProperty("user.dir"));

        ReadCnfFile CNFFile = new ReadCnfFile("src/part_1/benchmark/uf75-01.cnf");
        System.out.println(CNFFile.numClauses + " " + CNFFile.numVariables);


        ArrayList<Clause> clauses = new ArrayList<>();
        for(String clauseString : CNFFile.listClauses){
            clauses.add(new Clause(CNFFile.listClauses.indexOf(clauseString), clauseString));
        }
        System.out.println(clauses.get(0).getLiterals());

        SAT sat = new SAT(CNFFile.numVariables, CNFFile.numClauses, clauses);
        System.out.println(sat.get(0));


    }
}
