package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Message;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message_item {

    @FXML
    private Label l_date;

    @FXML
    private Label l_expediteur;

    @FXML
    private Label l_mess;


    public static String formatDate(String input) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
            Date date = inputFormat.parse(input);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            return outputFormat.format(date);
        } catch (Exception e) {
            return "Invalid input";
        }
    }

    public void setData(Message mess) {
        l_date.setText(Message_item.formatDate(mess.getDate()));
        l_mess.setText(mess.getContenu());
        String sender = mess.getSender().getPseudo();
        l_expediteur.setText(sender);
    }



}
