package algorithms;

import algorithms.sat_structure.Clause;
import algorithms.sat_structure.SAT;
import services.ReadCnfFile;

import java.util.ArrayList;

public abstract class Algorithm {

    protected SAT sat;

    public Algorithm(String fileName) {
        ReadCnfFile CNFFile = new ReadCnfFile(fileName);
        ArrayList<Clause> clauses = new ArrayList<>();
        for(String clauseString : CNFFile.listClauses){
            clauses.add(new Clause(CNFFile.listClauses.indexOf(clauseString), clauseString));
        }
        this.sat = new SAT(CNFFile.numVariables, CNFFile.numClauses, clauses);
    }

    public Algorithm(SAT sat) {
        this.sat = sat;
    }

    protected long runWithTimeCounting(){
        long startTime = System.nanoTime( );
        runAlgorithm();
        long endTime = System.nanoTime( );
        return endTime  - startTime;
    }

    protected abstract void runAlgorithm();
}