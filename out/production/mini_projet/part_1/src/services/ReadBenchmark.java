package services;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class ReadBenchmark {

    private static final String benchmarkFolder = "benchmark";

    public static HashMap<String,ReadCnfFile> readBenchmark(){
        HashMap<String,ReadCnfFile> files = new HashMap<>();
        for(String file : Objects.requireNonNull(new File(benchmarkFolder).list())){
            files.put(file, new ReadCnfFile(benchmarkFolder + File.separator + file));
        }
        return files;
    }
}
