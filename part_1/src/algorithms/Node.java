package algorithms;

import java.util.ArrayList;

public class Node {

    private ArrayList<Integer> vars;

    // this is the depth, it may be used as heuristic alone
    private int depth;

    // value is the heuristic
    private  int value;

    public Node(int number_of_vars) {
        this.vars = new ArrayList<>();

        for(int i = 0 ; i < number_of_vars ; i++ ){
            this.vars.add(null);
        }

        this.depth = 0;
    }

    public Node(ArrayList<Integer> vars, int last_var_value, int depth){

        this.depth = depth;
        this.vars = vars;

        for (int i = 0 ; i < vars.size() ; i++){

            if(vars.get(i) != null) {
                this.vars.set(i, vars.get(i));
            }
            else{
               this.vars.set(i, last_var_value);
                break;
            }
        }
    }

    public ArrayList<Integer> getVars() {
        return vars;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
