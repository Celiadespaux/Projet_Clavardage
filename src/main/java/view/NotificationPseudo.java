package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class NotificationPseudo {

    @FXML
    private Label label_new_pseudo;

    @FXML
    private Button b_ok;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setNewPseudo(String new_pseudo) {
        //label_new_pseudo.setText("Le nouveau pseudo est: " + new_pseudo);
        label_new_pseudo.setText(new_pseudo);
    }

    @FXML
    void handleButtonOK(ActionEvent event) {
        stage.close();
    }
}
