package utils;

import part_1.algorithms.*;
import part_2.algorithms.PSO.PSO;
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
    public static void solvePso(){
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


        //WArNING : If you are on windows you might need to  change the / to \\
        SAT sat = SAT.createSAT("benchmarks/benchmarks20/uf20-01.cnf");
        String bench= "uf20-01";
        numTraining = 0 ;


        File directory = new File(resultsFolder  + File.separator+ "part2");
        if (! directory.exists()){
            directory.mkdir();
        }
        try{
            FileWriter csvWriter = new FileWriter( resultsFolder  + File.separator+ "part2" + File.separator + "pso.csv");
            csvWriter.append("file,iteration,particles,c1,c2,w,percentDone,executionTime\n");
            System.out.println("Executing PSO please wait ... ");
            for(int ws=0;ws<w.length;ws++) {

                for(int c1in=0;c1in<c1.length;c1in++){

                    for (int c2in=0;c2in<c2.length;c2in++){

                        for(int par=0;par<particles.length;par++){

                            for (int ite=0;ite<iterations.length;ite++) {

                                String res = executePso(bench,sat,particles[par],w[ws],c1[c1in],c2[c2in],1,iterations[ite]);
                                csvWriter.append(res);
                                csvWriter.flush();
                                numTraining++;
                            }
                        }
                    }
                }
           }
            if (numTraining != iterations.length*particles.length*c1.length*c2.length*w.length){
                System.out.println("ERROR in execution of PSO ");
            }
                csvWriter.close();
            System.out.println("Execution finished succesfuly.");

        } catch (IOException E){
            System.out.println( "Error in CVS file" );
        }


    }
}

