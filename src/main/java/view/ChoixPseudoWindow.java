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
import manager.Account_manager;

import java.io.IOException;

public class ChoixPseudoWindow {

    String pseudo;
    @FXML
    final TextField tf_pseudo = new TextField();

    @FXML
    public void changeScene_ChatWindow(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(ConnexionWindow.class.getResource("/ChatWindow.fxml"));
        root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void valider_choix_pseudo(ActionEvent event) {

        pseudo = tf_pseudo.getText();
        if (Account_manager.verifier_pseudo_valide(pseudo)) {
            //if pseudo libre
        }

    }


}
