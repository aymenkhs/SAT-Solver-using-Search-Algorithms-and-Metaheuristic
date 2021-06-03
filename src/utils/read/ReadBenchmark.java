package utils.read;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class ReadBenchmark {

    public static final String BENCHMARKS_FOLDER = "benchmarks";

    private static final String benchmarkFolder75 = BENCHMARKS_FOLDER + "/benchmarks75";
    private static final String benchmarkFolder25 = BENCHMARKS_FOLDER + "/benchmarks20";

    public static HashMap<String, ReadCnfFile> readBenchmark75(){

        HashMap<String,ReadCnfFile> files = new HashMap<>();
        for(String file : Objects.requireNonNull(new File(benchmarkFolder75).list())){
            files.put(file, new ReadCnfFile(benchmarkFolder75 + File.separator + file));
        }
        return files;
    }

    public static HashMap<String,ReadCnfFile> readBenchmark20(){

        HashMap<String,ReadCnfFile> files = new HashMap<>();
        for(String file : Objects.requireNonNull(new File(benchmarkFolder25).list())){
            String fileName = file.substring(0, file.length() - 4);
            files.put(fileName, new ReadCnfFile(benchmarkFolder25 + File.separator + file));
        }
        return files;
    }
}
