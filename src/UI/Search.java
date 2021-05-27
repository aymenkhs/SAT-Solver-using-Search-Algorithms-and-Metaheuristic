package UI;

import javafx.concurrent.Task;
import part_1.algorithms.Search_Algorithm;

class Search extends Task<Search_Algorithm> {

    Search_Algorithm algorithm;
    Eval_controller controller;


    public Search(Eval_controller controller, Search_Algorithm algorithm){
        super();

        this.controller = controller;
        this.algorithm = algorithm;
    }


    @Override
    protected Search_Algorithm call() throws Exception {

        controller.add_trace("Searching...");

        try {

            algorithm.solve_time();

        }catch (OutOfMemoryError error){

            controller.add_trace("\nYour machine is out of memory.");

            return algorithm;
        }

        //succeeded();

        return algorithm;
    }

    @Override
    protected void succeeded() {
        super.succeeded();

        String time = Double.toString(algorithm.getTime());

        if(algorithm.isSatisfiable()) {
            controller.add_trace("\nThe dataset is satisfiable");
            controller.add_trace("\nSolution found in " + time + " seconds");
        }else {

            controller.add_trace("\nthe dataset is not satisfiable");
            controller.add_trace("\nExecution time : " + time + " seconds");
        }

        controller.set_time( time + " seconds");
        controller.set_solution(algorithm.getSolution());


    }
}
