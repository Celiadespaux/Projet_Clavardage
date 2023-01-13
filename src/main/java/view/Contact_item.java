package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.*;

public class Contact_item {

    @FXML
    private Label l_contactname;

    @FXML
    private Button b_contact;

    public static String capitalizeFirstLetter(String name) {
        if (name == null || name.length() == 0) {
            return name;
        }
        return name.substring(0, 1).toUpperCase();
    }

    public void setData(User user) {
        l_contactname.setText(user.getPseudo());
        b_contact.setText(capitalizeFirstLetter(user.getPseudo()));
    }

    public void afficher_convo_contact(User contact){

    }
}
