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
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import part_1.algorithms.*;
import part_1.algorithms.sat_structure.SAT;
import part_1.services.ReadBenchmark;

import java.io.File;
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
    TextField dataset_field;
    @FXML
    ComboBox<String> algorithm_combox;

    private File choosen_file;


    private void init_algo_combox(){

        algorithm_combox.getItems().add("Depth First");
        algorithm_combox.getItems().add("Breadth First");
        algorithm_combox.getItems().add("A* - Heuristic 1");
        algorithm_combox.getItems().add("A* - Heuristic 2");
        algorithm_combox.getItems().add("A* - Heuristic 3");
        algorithm_combox.getItems().add("A* - Heuristic 4");
        algorithm_combox.getItems().add("A* - Heuristic 5");

    }






    @FXML
    void launch(ActionEvent event) throws IOException {

        if(choosen_file != null && algorithm_combox.getSelectionModel().getSelectedItem() != null) {


            String file_name = choosen_file.getName();
            String algorithm_name = algorithm_combox.getSelectionModel().getSelectedItem();


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Eval_Scene.fxml"));
            Parent eval_scene = loader.load();

            Eval_controller controller = loader.getController();
            controller.init(algorithm_name, choosen_file.getAbsolutePath(), file_name);

            Stage window = (Stage) algorithm_combox.getScene().getWindow();
            window.setScene(new Scene(eval_scene));

        }

    }

    @FXML
    private void browse(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CNF files", "*.cnf")
        );
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        choosen_file = fileChooser.showOpenDialog(null);

        if (choosen_file != null){
            dataset_field.setText(choosen_file.getAbsolutePath());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dataset_field.editableProperty().setValue(false);
        init_algo_combox();

    }
}
