package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.User;

public class NotificationPseudo {

    @FXML
    private Label label_new_pseudo;

    @FXML
    private Button b_ok;

    private Stage stage;

    private static User new_contact;

    private ChatWindow controller_chat_window;;

    public void setController_chat_windowController(ChatWindow controller) {
        this.controller_chat_window = controller ;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setNewPseudo(String new_pseudo) {
        //label_new_pseudo.setText("Le nouveau pseudo est: " + new_pseudo);
        label_new_pseudo.setText(new_pseudo);
    }

    public static void setNew_contact(User contact) {
        new_contact = contact;
    }

    @FXML
    void handleButtonOK(ActionEvent event) {
        stage.close();
        controller_chat_window.afficher_new_contact(new_contact);
    }
}
