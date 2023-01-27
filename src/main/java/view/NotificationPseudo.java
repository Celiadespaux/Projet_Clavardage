package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.User;

public class NotificationPseudo {

    @FXML
    private Label label_new_pseudo;

    public void setController_chat_windowController(ChatWindow controller) {
    }

    public void setStage(Stage stage) {
    }

    public void setNewPseudo(String new_pseudo) {
        //label_new_pseudo.setText("Le nouveau pseudo est: " + new_pseudo);
        label_new_pseudo.setText(new_pseudo);
    }

    public static void setNew_contact(User contact) {
    }

}
