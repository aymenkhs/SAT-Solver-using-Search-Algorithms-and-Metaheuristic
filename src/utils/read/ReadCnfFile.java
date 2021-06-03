package utils.read;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCnfFile {
    public ArrayList<String> listClauses;
    public int numClauses, numVariables;

    public ReadCnfFile(String filepath) {
        listClauses = new ArrayList<>();
        readFile(filepath);
    }

    private void readFile(String filepath){
        try {
            File myFileObj = new File(filepath);
            Scanner myReader = new Scanner(myFileObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if (!line.equals("%") && !line.equals("0") && !line.startsWith("c")){
                    if(line.startsWith("p")){
                        getNumVarClauses(line);
                    } else {
                        listClauses.add(line);
                    }
                }
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void getNumVarClauses(String startLine){
        String[] parts = startLine.split(" ");
        int i = 0;
        while(true){
            if(!parts[i].trim().isEmpty() && !parts[i].equals("p") && !parts[i].equals("cnf")){
                break;
            } else {
                i++;
            }
        }
        numVariables = Integer.parseInt(parts[i]);
        i++;
        while(true){
            if(!parts[i].trim().isEmpty()){
                break;
            } else {
                i++;
            }
        }
        numClauses = Integer.parseInt(parts[i]);
    }
}
