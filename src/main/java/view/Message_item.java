package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Message;

import java.lang.*;

public class Message_item {

    @FXML
    private Label l_date;

    @FXML
    private Label l_mess;

    @FXML
    private Label l_expediteur;

    public void setData(Message mess) {
        l_date.setText(mess.getDate());
        l_mess.setText(mess.getContenu());
        //TODO changer expe
        int expe = mess.getId_expe();
        l_expediteur.setText(Integer.toString(expe));
    }

}
