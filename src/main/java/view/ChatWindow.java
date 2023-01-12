package view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import model.*;
import manager.*;

public class ChatWindow implements Initializable {

    @FXML private VBox vbox_chat_messages;
    //final VBox vbox_chat_messages = new VBox();

    @FXML private TextField tf_mess_a_envoyer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Message> messages_list;
        try {
            messages_list = DB_locale_manager.getHistory_mess();
        } catch (SQLException e) {
            System.out.println("[ChatWindow.java] Pb creation liste messages");
            throw new RuntimeException(e);
        }
        for (Message message : messages_list) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Message_item.fxml"));
            try {

                VBox m_item_fxml = fxmlLoader.load();
                System.out.println("[ChatWindow.java] Apres load VBOX");

                //Message_item m_item_ctrl = new Message_item();
                Message_item m_item_ctrl = fxmlLoader.getController();
                m_item_ctrl.setData(message);


                //vbox_chat_messages = new VBox();
                vbox_chat_messages.getChildren().add(m_item_fxml);
                System.out.println("[ChatWindow.java] item :"+m_item_fxml);

                System.out.println("[ChatWindow.java] Apres ajout de message a vbox_chat_messages ");


            } catch (IOException e) {
                System.out.println("[ChatWindow.java] Pb load message_item");
                throw new RuntimeException(e);
            }
        }


    }

    // Affichage page de deconnexion qd on clique sur le bouton deconnexion
    @FXML
    public void changeScene_DeconnexionWindow(ActionEvent event) throws IOException {
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(ConnexionWindow.class.getResource("/DeconnexionWindow.fxml"));
        root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


}
