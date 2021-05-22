package services;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class ReadBenchmark {

    private static final String benchmarkFolder = "benchmark";

    public static ArrayList<ReadCnfFile> readBenchmark(){
        ArrayList<ReadCnfFile> files = new ArrayList<>();
        for(String file : Objects.requireNonNull(new File(benchmarkFolder).list())){
            files.add(new ReadCnfFile(benchmarkFolder + File.separator + file));
        }
        return files;
    }
}
