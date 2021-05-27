package UI;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import part_1.algorithms.*;
import part_1.algorithms.sat_structure.SAT;

import java.nio.file.Path;
import java.util.ArrayList;

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

public class Eval_controller {


    @FXML
    private TextArea algo_name_Text_area;
    @FXML
    private TextArea dataset_name_Text_area;
    @FXML
    private TextArea trace_Text_area;
    @FXML
    private TextArea time_Text_area;
    @FXML
    private ListView<String> solution_list;


    private String algorithm_name;
    private String file_path;


    public void init(String algorithm_name, String file_path, String file_name){

        algo_name_Text_area.editableProperty().setValue(false);
        algo_name_Text_area.setText(algorithm_name);

        dataset_name_Text_area.editableProperty().setValue(false);
        dataset_name_Text_area.setText(file_name);

        trace_Text_area.editableProperty().setValue(false);

        time_Text_area.editableProperty().setValue(false);

        this.algorithm_name = algorithm_name;
        this.file_path = file_path;
    }

    @FXML
    private void launch(){

        SAT sat = SAT.createSAT(file_path);

        Search_Algorithm algorithm;
        switch (algorithm_name){

            case "Depth First":
                algorithm =  new depth_first_search(sat);
                break;
            case "Breadth First":
                algorithm =  new Breadth_first_search(sat);
                break;
            case "A* - Heuristic 1":
                algorithm =  new AStarHeuristic1(sat);
                break;
            case "A* - Heuristic 2":
                algorithm =  new AStarHeuristic2(sat);
                break;
            case "A* - Heuristic 3":
                algorithm =  new AStarHeuristic3(sat);
                break;
            case "A* - Heuristic 4":
                algorithm =  new AStarHeuristic4(sat);
                break;
            case "A* - Heuristic 5":
                algorithm =  new AStarHeuristic5(sat);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + algorithm_name);
        }





        Search task = new Search(this, algorithm);

        new Thread(task).start();

    }

    public void add_trace(String message){

        String current_message = trace_Text_area.getText();
        trace_Text_area.setText(current_message + message);

    }

    public void set_time(String time){
        time_Text_area.setText(time);
    }

    public void set_solution(ArrayList<Integer> solution){

        int cpt = 1;
        for(Integer literal : solution){

            String value = literal == 1 ? "true" : "false";

            String lit = "x" + cpt + " = " + value;

            solution_list.getItems().add(lit);

            cpt += 1;
        }
    }
}
