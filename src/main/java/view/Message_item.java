package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Message;

public class Message_item {

    @FXML
    private Label l_date;

    @FXML
    private Label l_expediteur;

    @FXML
    private Label l_mess;


    public void setData(Message mess) {
        l_date.setText(mess.getDate());
        l_mess.setText(mess.getContenu());
        String sender = mess.getSender().getPseudo();
        l_expediteur.setText(sender);
    } 
    
    



}
