import services.ReadCnfFile;

public class Main {
    public static void main(String[] args){
        ReadCnfFile CNFFile = new ReadCnfFile("uf75-01.cnf");
        System.out.println(CNFFile.numClauses + " " + CNFFile.numVariables);
        System.out.println(CNFFile.listClauses);
    }
}
