package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import manager.DB_locale_manager;
import model.*;
import view.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Contact_item {

    @FXML
    private Label l_contactname;

    @FXML
    private Button b_contact;

    public User contact;

    private ChatWindow controller_chat_window;;

    public void setController_chat_windowController(ChatWindow controller) {
        this.controller_chat_window = controller ;
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        controller_chat_window.setLast_contact_cliked(this.contact);
        controller_chat_window.charger_historique(this.contact.getId());

    }

    public void setContact(User contact) {
        this.contact = contact;
    }

    public static String capitalizeFirstLetter(String name) {
        if (name == null || name.length() == 0) {
            return name;
        }
        return name.substring(0, 1).toUpperCase();
    }

    public void setData() {
        l_contactname.setText(this.contact.getPseudo());
        b_contact.setText(capitalizeFirstLetter(this.contact.getPseudo()));
    }

    /* public void afficher_convo_contact(){
        int id_contact = this.contact.getId();
        FXMLLoader fxmlLoader = new FXMLLoader(ConnexionWindow.class.getResource("/ChatWindow.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("[Contact_item.java] Pb load ChatWindow.fxml");
            throw new RuntimeException(e);
        }
        ChatWindow cw = fxmlLoader.getController();
        cw.afficher_messages(id_contact);
    }*/
}
