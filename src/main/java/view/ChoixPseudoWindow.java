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

public class ChoixPseudoWindow {

    static String pseudo;
    @FXML TextField tf_pseudo ;
    
    @FXML Label l_mess_pseudo ;

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
	            if (Account_manager.verifier_pseudo_libre(pseudo)) {
	            	try {
						Account_manager.connecte(User.getMoi());
						DB_locale_manager.getContacts(1);
						//User.getMoi().setPseudo(pseudo);
						DB_locale_manager.maj_pseudo(pseudo,User.getMyId());
					    changeScene_ChatWindow(event);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	            else {
	            	l_mess_pseudo.setText("Pseudo non libre, choisir un autre pseudo :");
	            }
	        }
	        else {
	        	l_mess_pseudo.setText("Pseudo non valide, choisir un autre pseudo :");
	        }
    }

}
