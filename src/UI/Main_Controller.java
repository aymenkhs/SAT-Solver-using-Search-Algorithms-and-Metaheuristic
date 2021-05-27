package UI;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import part_1.algorithms.*;
import part_1.algorithms.sat_structure.SAT;
import part_1.services.ReadBenchmark;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main_Controller implements Initializable {


    @FXML
    ComboBox<String> dataset_combox;

    @FXML
    ComboBox<String> algorithm_combox;

    private List<Path> paths = null;


    private void init_algo_combox(){

        algorithm_combox.getItems().add("Depth First");
        algorithm_combox.getItems().add("Breadth First");
        algorithm_combox.getItems().add("A* - Heuristic 1");
        algorithm_combox.getItems().add("A* - Heuristic 2");
        algorithm_combox.getItems().add("A* - Heuristic 3");
        algorithm_combox.getItems().add("A* - Heuristic 4");
        algorithm_combox.getItems().add("A* - Heuristic 5");

    }


    private void init_dataset_combox(){

        Path path = Paths.get(ReadBenchmark.BENCHMARKS_FOLDER);
        List<Path> result = null;

        //get the benchmarks files
        try (Stream<Path> walk = Files.walk(path)) {

            result = walk.filter(Files::isRegularFile).collect(Collectors.toList());
        }
        catch (Exception e){
            e.printStackTrace();
        }


        if(result != null) {

            this.paths = result;
            for (Path file : result){
                dataset_combox.getItems().add(file.getFileName().toString());
            }
        }

    }

    private String get_file_path(){

        String file_name = dataset_combox.getSelectionModel().getSelectedItem();

        for(Path file : this.paths){

            if(file_name.equals(file.getFileName().toString())){
                return file.toString();
            }
        }
        return null;
    }

    @FXML
    void launch(ActionEvent event) throws IOException {

        String file_name = dataset_combox.getSelectionModel().getSelectedItem();
        String file_path = get_file_path();

        String algorithm_name = algorithm_combox.getSelectionModel().getSelectedItem();


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Eval_Scene.fxml"));
        Parent eval_scene = loader.load();

        Eval_controller controller = loader.getController();
        controller.init(algorithm_name, file_path, file_name);

        Stage window = (Stage) dataset_combox.getScene().getWindow();
        window.setScene(new Scene(eval_scene));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        init_algo_combox();
        init_dataset_combox();
    }
}
