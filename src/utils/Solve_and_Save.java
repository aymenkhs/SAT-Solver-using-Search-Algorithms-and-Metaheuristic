package utils;

import part_1.algorithms.*;
import part_2.algorithms.PSO.PSO;
import part_2.algorithms.GA.GeneticAlgorithm;
import utils.read.ReadCnfFile;
import utils.sat_structure.SAT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Solve_and_Save {

    private static final String resultsFolder = "results";

    public static void solve_BFS(HashMap<String, ReadCnfFile> satStructures){
        double time;
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
                algorithm.solve();
                time = algorithm.getTime();
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

    public static void solve_DFS(HashMap<String, ReadCnfFile> satStructures, int begin, int end, int step){
        double time;
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
                    algorithm.solve();
                    time = algorithm.getTime();
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

    public static void solve_AStar(HashMap<String, ReadCnfFile> satStructures){
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
        double time;

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

        algorithm.solve();
        time = algorithm.getTime();
        return bench + "," + i + "," + algorithm.isSatisfiable( ) + "," + time + "\n";
    }


    /* PSO */

    private static  String executePso(String bench, SAT sat, int num_particles, float w, double c1, double c2, double initial_velocity, int max_iteration){

        //executer PSO
        PSO pso = new PSO(sat, num_particles,w,c1,c2,1,max_iteration);
        pso.solve();
        //Sauvegarder dans le csv
        return  bench + "," + max_iteration + "," + num_particles + "," + c1 + "," + c2 + "," + w + "," + pso.getPercent_done() +"," + pso.getTime() +"\n";

    }
    public static void solvePso(HashMap<String, ReadCnfFile> satStructures){
        /*
            execute Pso algorithm with different paramters and save results into pso.csv
            TODO: maybe adding a funtion to get the best parameters, i don't know if it's realy
                    necessary
         */

        //loop through the iteration
        int[] iterations = {100,200,300,400,500,1000},
                particles = {50,100,200,};

        double[] c1 ={1,2},
                c2 ={1,2};

        float w[] = {0.5F,1},
              numTraining ;


        numTraining = 0 ;

        File directory = new File(resultsFolder  + File.separator + "part2");
        if (! directory.exists()){
            directory.mkdir();
        }

        directory = new File(resultsFolder  + File.separator + "part2" + File.separator + "pso");
        if (! directory.exists()){
            directory.mkdir();
        }
        try{
            System.out.println("Executing PSO please wait ... ");
            for (String bench: satStructures.keySet()){
                SAT sat = SAT.createSAT(satStructures.get(bench));
                FileWriter csvWriter = new FileWriter( resultsFolder  + File.separator+ "part2"  + File.separator + "pso" + File.separator + "pso_" + bench + ".csv");
                csvWriter.append("file,iteration,particles,c1,c2,w,percentDone,executionTime\n");
                for (float v : w) {

                    for (double value : c1) {

                        for (double item : c2) {

                            for (int particle : particles) {

                                for (int iteration : iterations) {

                                    String res = executePso(bench, sat, particle, v, value, item, 1, iteration);
                                    csvWriter.append(res);
                                    csvWriter.flush( );
                                    numTraining++;
                                }
                            }
                        }
                    }
                }
                csvWriter.close();
            }
            if (numTraining != iterations.length*particles.length*c1.length*c2.length*w.length){
                System.out.println("ERROR in execution of PSO ");
            }
            System.out.println("Execution finished succesfuly.");

        } catch (IOException E){
            System.out.println( E );
        }

    }

    private static  String executeGA(String bench, SAT sat, int nbGenerations, int sizePopulation){

        //executer PSO
        GeneticAlgorithm ga = new GeneticAlgorithm(sat, nbGenerations, sizePopulation);
        ga.solve();
        //Sauvegarder dans le csv
        return  bench + "," + sizePopulation + "," + nbGenerations + "," + ga.getPercent_done() +"," + ga.getTime() +"\n";

    }
    public static void solveGA(HashMap<String, ReadCnfFile> satStructures){
        /*
            execute Pso algorithm with different paramters and save results into pso.csv
            TODO: maybe adding a funtion to get the best parameters, i don't know if it's realy
                    necessary
         */

        //loop through the iteration
        int nbGeneration_min = 10, nbGeneration_max = 100, gen_step = 10;

        int sizePop_min = 50, sizePop_max = 500, pop_step = 50;

        int numTraining = 0;


        File directory = new File(resultsFolder  + File.separator + "part2");
        if (! directory.exists()){
            directory.mkdir();
        }

        directory = new File(resultsFolder  + File.separator + "part2" + File.separator + "ga");
        if (! directory.exists()){
            directory.mkdir();
        }
        try{
            System.out.println("Executing Genetic Algorithm please wait ... ");
            for (String bench: satStructures.keySet()){
                SAT sat = SAT.createSAT(satStructures.get(bench));
                FileWriter csvWriter = new FileWriter( resultsFolder  + File.separator+ "part2"  + File.separator + "ga" + File.separator + "ga_" + bench + ".csv");
                csvWriter.append("file,iteration,particles,c1,c2,w,percentDone,executionTime\n");
                for (int popsize=sizePop_min; popsize<=sizePop_max; popsize+=pop_step) {

                    for (int nbGen=nbGeneration_min; nbGen<=nbGeneration_max; nbGen+=gen_step) {
                        String res = executeGA(bench, sat, popsize, nbGen);
                        csvWriter.append(res);
                        csvWriter.flush( );
                        numTraining++;
                    }
                }
                csvWriter.close();
            }
            System.out.println("Execution finished succesfuly.");

        } catch (IOException E){
            System.out.println( E );
        }
    }
}

