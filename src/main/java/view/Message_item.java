package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Message;

import java.lang.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Message_item implements Initializable {

    @FXML
    private Label l_date;

    @FXML
    private Label l_expediteur;

    @FXML
    private Label l_mess;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(Message mess) {
        l_date.setText(mess.getDate());
        l_mess.setText(mess.getContenu());
        //TODO changer expe
        String sender = mess.getSender().getPseudo();
        l_expediteur.setText(sender);

        System.out.println("[Message_item.java] Après set data => l_date : "+l_date.getText());
        System.out.println("[Message_item.java] Après set data => l_mess : "+l_mess.getText());
        System.out.println("[Message_item.java] Après set data => l_expediteur : "+l_expediteur.getText());

    }



}
