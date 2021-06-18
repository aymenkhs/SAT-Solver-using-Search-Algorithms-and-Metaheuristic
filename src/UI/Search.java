package UI;

import UI.controllers.Eval_controller;
import javafx.concurrent.Task;
import utils.Algorithm;

public class Search extends Task<Algorithm> {

    Algorithm algorithm;
    Eval_controller controller;


    public Search(Eval_controller controller, Algorithm algorithm){
        super();

        this.controller = controller;
        this.algorithm = algorithm;
    }


    @Override
    protected Algorithm call() throws Exception {

        controller.add_trace("Searching...");

        try {

            algorithm.solve();

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

        controller.add_trace("\nDone");
        controller.add_trace("\nExecution time : " + time + " seconds");

        controller.set_time( time + " seconds");
        controller.set_solution(algorithm.getSolution());
        controller.set_percent(algorithm.getPercent_done() + " %");


    }
}
