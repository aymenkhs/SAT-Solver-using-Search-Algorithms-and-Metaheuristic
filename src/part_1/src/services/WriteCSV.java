package part_1.src.services;

import part_1.src.algorithms.Breadth_first_search;
import part_1.src.algorithms.Search_Algorithm;
import part_1.src.algorithms.sat_structure.SAT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WriteCSV {

    private static final String resultsFolder = "results";

    public static void writeBFS(HashMap<String, ReadCnfFile> satStructures){
        long time;
        Search_Algorithm algorithm;

        File directory = new File(resultsFolder  + File.separator+ "bfs");
        if (! directory.exists()){
            directory.mkdir();
        }

        try{
            FileWriter csvWriter = new FileWriter( resultsFolder  + File.separator+ "bfs" + File.separator + "bfs.csv");
            csvWriter.append("file,solved,executionTime\n");

            for (String bench: satStructures.keySet()){
                SAT sat = SAT.createSAT(satStructures.get(bench));
                algorithm = new Breadth_first_search(sat);
                time = algorithm.solve_time();
                String str = bench + "," + algorithm.isSatisfiable() + "," + time + "\n";
                csvWriter.append(str);
            }
            csvWriter.flush( );
            csvWriter.close( );
        } catch (IOException E){
            System.out.println( "Error in CVS file" );
        }

    }
}
