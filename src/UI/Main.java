package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        String path = "";
        Parent root = FXMLLoader.load(getClass().getResource(path + "Main_Scene.fxml"));
        stage.setTitle("MAE project");
        Scene scene = new Scene(root, 700, 400);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){

        launch(args);

    }
}
