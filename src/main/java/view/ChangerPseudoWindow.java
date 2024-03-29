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
import model.User;

import java.io.IOException;
import java.sql.SQLException;

public class ChangerPseudoWindow {

    static String pseudo;
    @FXML TextField tf_pseudo2;
    
    @FXML Label l_mess_pseudo2 ;

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
        pseudo = tf_pseudo2.getText();
        if (Account_manager.verifier_pseudo_valide(pseudo)) {
            if (DB_locale_manager.verifier_pseudo_libre(pseudo)) {
            	try {
            	    String old_pseudo = User.getMoi().getPseudo();
                    DB_locale_manager.maj_pseudo(pseudo,User.getMyId());
                    Account_manager.changer_pseudo(User.getMoi(),old_pseudo);
            		//User.getMoi().setPseudo(pseudo);
				    changeScene_ChatWindow(event);
				} catch (IOException e) {
					System.out.println("[ChangerPseudo] pb dans valider choix pseudo");
					e.printStackTrace();
				}
            }
            else {
            	l_mess_pseudo2.setText("Pseudo non libre, choisir un autre pseudo :");
            }
        }
        else {
        	l_mess_pseudo2.setText("Pseudo non valide, choisir un autre pseudo :");
        }
    }
}
