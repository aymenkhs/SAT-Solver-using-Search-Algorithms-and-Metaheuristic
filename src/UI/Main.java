package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;


public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Main_Scene.fxml"));
        stage.setTitle("MAE project");
        Scene scene = new Scene(root, 700, 400);


        URL icon_url = getClass().getResource("/icon.png");
        Image icon = new Image(icon_url.toURI().toString());


        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){

        launch(args);

    }
}
