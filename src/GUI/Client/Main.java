package GUI.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase que ejecuta la interfaz gráfica
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        primaryStage.setTitle("ChatTEC");
        primaryStage.setScene(new Scene(root, 260, 165));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
