package algorithms.sat_structure;

import java.util.ArrayList;

public class Clause {
    private ArrayList<Integer> literals;

    public Clause(String clauseString) {
        this.literals = new ArrayList<>();
        String[] parts = clauseString.split(" ");
        for (String part:parts){
            if (!part.equals("0")){
                literals.add(Integer.parseInt(part));
            }
        }
    }
}
