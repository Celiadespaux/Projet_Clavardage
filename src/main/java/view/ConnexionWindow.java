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
import manager.DB_locale_manager;
import model.User;

import java.io.IOException;

public class ConnexionWindow {

	static String id ;
	String mdp ;
	String mdpbon ;
	User user ;
	@FXML TextField tf_id ;
	@FXML TextField tf_mdp ;
	@FXML Label l_mess_connexion ;
	
	public static Integer getId() {
		return Integer.valueOf(id);
	}
	
    //FCT Get_moi pour savoir qui s'est connecte
    @FXML
    public void changeScene_ChoixPseudo(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(ConnexionWindow.class.getResource("/ChoixPseudoWindow.fxml"));
        root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void verifier_mdp(ActionEvent event) throws IOException {
    	id = tf_id.getText();
    	mdp = tf_mdp.getText();
    	user = DB_locale_manager.getUserfromId(Integer.valueOf(id));
    	mdpbon = user.getMdp();
    	if (mdp.equals(mdpbon)) {
    		DB_locale_manager.creer_moi();
    		changeScene_ChoixPseudo(event);
    		System.out.println(DB_locale_manager.getMoi());
    	}
    	else {
    		l_mess_connexion.setText("Mot de passe erroné, veuillez réessayer");
    	}
    }

}
