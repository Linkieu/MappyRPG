package vue;

import javafx.scene.*;
import javafx.application.*;
import javafx.stage.*;

import java.io.File;

public class Main extends Application {
    public static void main(String [] args){
        Application.launch();
    }

    public void start(Stage stage){
        GUI root = new GUI();
        Scene scene = new Scene(root, 1200,600);
        stage.setScene(scene);
        stage.setTitle("Bienvenue sur MappyRPG !");
        File css = new File("css" + File.separator + "css.css");
        scene.getStylesheets().add(css.toURI().toString());
        stage.centerOnScreen();
        stage.show();
    }
}