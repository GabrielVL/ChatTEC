package GUI;

import Logic.Server;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase que le asigna métodos a la interfaz gráfica
 */

public class Controller extends Server implements Initializable {

    @FXML private Button serverButton;

//    @FXML public void activateServer(ActionEvent event) {
//
//        try {
//            createServer();
//        } catch (IOException i) {
//            System.out.println(i);
//        }
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
