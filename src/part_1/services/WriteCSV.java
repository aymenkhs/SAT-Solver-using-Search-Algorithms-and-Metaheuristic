package part_1.services;

import part_1.algorithms.*;
import part_1.algorithms.sat_structure.SAT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WriteCSV {

    private static final String resultsFolder = "results";

    public static void writeBFS(HashMap<String, ReadCnfFile> satStructures){
        long time;
        Search_Algorithm algorithm;

        File directory = new File(resultsFolder  + File.separator+ "part1");
        if (! directory.exists()){
            directory.mkdir();
        }

        try{
            FileWriter csvWriter = new FileWriter( resultsFolder  + File.separator+ "part1" + File.separator + "bfs.csv");
            csvWriter.append("file,solved,executionTime\n");

            for (String bench: satStructures.keySet()){
                SAT sat = SAT.createSAT(satStructures.get(bench));
                algorithm = new Breadth_first_search(sat);
                time = algorithm.solve_time();
                String str = bench + "," + algorithm.isSatisfiable() + "," + time + "\n";
                csvWriter.append(str);
                csvWriter.flush( );
                System.out.println( str );
            }
            csvWriter.close( );
        } catch (IOException E){
            System.out.println( "Error in CVS file" );
        }

    }

    public static void writeDFS(HashMap<String, ReadCnfFile> satStructures, int begin, int end, int step){
        long time;
        Search_Algorithm algorithm;

        File directory = new File(resultsFolder  + File.separator+ "part1");
        if (! directory.exists()){
            directory.mkdir();
        }

        try{
            FileWriter csvWriter = new FileWriter( resultsFolder  + File.separator+ "part1" + File.separator + "dfs.csv");
            csvWriter.append("file,limit,solved,executionTime\n");

            for (String bench: satStructures.keySet()){
                SAT sat = SAT.createSAT(satStructures.get(bench));
                for (int limit=begin; limit<=end; limit+=step){
                    algorithm = new depth_first_search(sat, limit);
                    time = algorithm.solve_time();
                    String str = bench + "," + limit + "," + algorithm.isSatisfiable( ) + "," + time + "\n";
                    csvWriter.append(str);
                    csvWriter.flush( );
                    System.out.println( str );
                }
            }
            csvWriter.close( );
        } catch (IOException E){
            System.out.println( "Error in CVS file" );
        }

    }

    public static void writeAStar(HashMap<String, ReadCnfFile> satStructures){



        File directory = new File(resultsFolder  + File.separator+ "part1");
        if (! directory.exists()){
            directory.mkdir();
        }

        try{
            FileWriter csvWriter = new FileWriter( resultsFolder  + File.separator+ "part1" + File.separator + "astar.csv");
            csvWriter.append("file,Heuristique,solved,executionTime\n");

            for (String bench: satStructures.keySet()){
                SAT sat = SAT.createSAT(satStructures.get(bench));
                for (int i=1; i<=5; i++){
                    String str = ExecutingWichHeuristic(sat, i, bench);
                    csvWriter.append(str);
                    csvWriter.flush( );
                    System.out.println( str );
                }
            }
            csvWriter.close( );
        } catch (IOException E){
            System.out.println( "Error in CVS file" );
        }

    }

    private static String ExecutingWichHeuristic(SAT sat, int i, String bench){
        A_Star algorithm;
        long time;

        switch (i){
            case 1:
                algorithm = new AStarHeuristic1(sat);
                break;
            case 2:
                algorithm = new AStarHeuristic2(sat);
                break;
            case 3:
                algorithm = new AStarHeuristic3(sat);
                break;
            case 4:
                algorithm = new AStarHeuristic4(sat);
                break;
            case 5:
                algorithm = new AStarHeuristic5(sat);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
        time = algorithm.solve_time();
        return bench + "," + i + "," + algorithm.isSatisfiable( ) + "," + time + "\n";
    }
}
