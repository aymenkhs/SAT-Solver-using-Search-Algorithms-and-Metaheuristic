package UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    ComboBox<String> dataset_combox;

    @FXML
    ComboBox<String> algorithm_combox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        algorithm_combox.getItems().add("A*");
        algorithm_combox.getItems().add("Depth First");
        algorithm_combox.getItems().add("Breadth First");


    }
}
