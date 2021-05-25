package part_1.services;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class ReadBenchmark {

    private static final String benchmarkFolder75 = "benchmarks75";
    private static final String benchmarkFolder25 = "benchmarks25";

    public static HashMap<String,ReadCnfFile> readBenchmark75(){
        System.out.println(System.getProperty("user.dir"));
        HashMap<String,ReadCnfFile> files = new HashMap<>();
        for(String file : Objects.requireNonNull(new File(benchmarkFolder75).list())){
            files.put(file, new ReadCnfFile(benchmarkFolder75 + File.separator + file));
        }
        return files;
    }

    public static HashMap<String,ReadCnfFile> readBenchmark25(){
        System.out.println(System.getProperty("user.dir"));
        HashMap<String,ReadCnfFile> files = new HashMap<>();
        for(String file : Objects.requireNonNull(new File(benchmarkFolder25).list())){
            files.put(file, new ReadCnfFile(benchmarkFolder25 + File.separator + file));
        }
        return files;
    }
}
