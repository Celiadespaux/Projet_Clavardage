package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import manager.*;
import transport.*;

import java.io.IOException;
import java.sql.SQLException;

public class ChoixPseudoWindow {

    String pseudo;
    @FXML
    private TextField tf_pseudo;
    
    String mess_pseudo;
    @FXML
    private Label l_mess_pseudo;

    @FXML
    public void changeScene_ChatWindow(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(ConnexionWindow.class.getResource("/ChatWindow.fxml"));
        root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void valider_choix_pseudo(ActionEvent event) throws SQLException, IOException {

        pseudo = tf_pseudo.getText();
        if (Account_manager.verifier_pseudo_valide(pseudo)) {
            if (DB_locale_manager.verifier_pseudo_libre(pseudo)) {
            	Account_manager.connecte(Traitement_Messages.getMoi());
            	Network_manager.runservers();
            }
            else {
            	mess_pseudo = "Pseudo non valide, choisir un autre pseudo";
            	valider_choix_pseudo(event);
            }
        }
        else {
        	mess_pseudo = "Pseudo non libre, choisir un autre pseudo";
        	valider_choix_pseudo(event);
        }
        changeScene_ChatWindow(event);
    }

}
