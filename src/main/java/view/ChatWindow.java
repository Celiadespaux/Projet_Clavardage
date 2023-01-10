package view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

//public class ChatWindow implements Initializable {
public class ChatWindow {

    @FXML
    private VBox vbox_chat_messages;
/*

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Message> messages;
        try {
            messages = new ArrayList<>(getHistory_mess());
        } catch (SQLException e) {
            System.out.println("[ChatWindow.java] Pb creation liste message");
            throw new RuntimeException(e);
        }
        for (Message message : messages) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Message_item.fxml"));
            try {
                AnchorPane message_pane = fxmlLoader.load();
                Message_item m_item = new Message_item();
                m_item.setData(message);
                vbox_chat_messages.getChildren().add(message_pane);

            } catch (IOException e) {
                System.out.println("[ChatWindow.java] Pb load message_item");
                throw new RuntimeException(e);
            }
        }


    }
*/

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
