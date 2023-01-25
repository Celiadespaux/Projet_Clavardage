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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import javafx.application.Platform;
import transport.TCP;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import manager.*;


public class ChatWindow implements Initializable {
//public class ChatWindow {
	
	@FXML
    private TextField tf_mess_a_envoyer;
	
    @FXML
    private VBox vbox_chat_messages;
    
    @FXML Label l_mon_nom ;

    @FXML
    private HBox hbox_utilisateurs_actifs;

    public static User last_contact_cliked;

    private static ArrayList<Contact_item> contact_items_list = new ArrayList<Contact_item>();

    public void add_contact_item_to_list(Contact_item item) {
        contact_items_list.add(item);
    }

    public static Contact_item get_contact_item_by_user(int id_contact) {
        for (Contact_item item : contact_items_list) {
            if (item.contact.getId() == id_contact) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       // contact_item_controller.setController_chat_windowController(this);
        //Affichage de tous les messages

        /*ArrayList<Message> messages_list;
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
                Message_item m_item_ctrl = fxmlLoader.getController();
                m_item_ctrl.setData(message);
                vbox_chat_messages.getChildren().add(m_item_fxml);
            } catch (IOException e) {
                System.out.println("[ChatWindow.java] Pb load message_item");
                throw new RuntimeException(e);
            }
        }*/


        //Affichage de tous les contacts connectes d'abbord puis les deconnecte
        ArrayList<User> contacts_list;
        int connecte = 1;
        for (int u=0; u<2; u++){

            try {
                contacts_list = DB_locale_manager.getContacts(connecte);
            } catch (SQLException e) {
                System.out.println("[ChatWindow.java] Pb creation liste contacts");
                throw new RuntimeException(e);
            }

            for (User user : contacts_list) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Contact_item.fxml"));
                try {
                    VBox c_item_fxml = fxmlLoader.load();
                    Contact_item c_item_ctrl = fxmlLoader.getController();
                    c_item_ctrl.setContact(user);
                    c_item_ctrl.setData();
                    c_item_ctrl.setBStyle(connecte);
                    c_item_ctrl.setController_chat_windowController(this);
                    hbox_utilisateurs_actifs.getChildren().add(c_item_fxml);
                    this.add_contact_item_to_list(c_item_ctrl);
                } catch (IOException e) {
                    System.out.println("[ChatWindow.java] Pb load contact_item");
                    throw new RuntimeException(e);
                }
            }
            connecte=0;
        }

        Network_manager.setController_chat_windowController(this);

        l_mon_nom.setText(ChoixPseudoWindow.pseudo);
    }

    public void setLast_contact_cliked(User contact){
        last_contact_cliked = contact;
    }
    public void charger_historique(int id_contact){
        vbox_chat_messages.getChildren().clear();
        ArrayList<Message> messages_list;
        try {
            messages_list = DB_locale_manager.getHistory_mess(id_contact);
        } catch (SQLException e) {
            System.out.println("[Contact_item.java] Pb creation liste messages");
            throw new RuntimeException(e);
        }
        for (Message message : messages_list) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ChatWindow.class.getResource("/Message_item.fxml"));
            try {
                VBox m_item_fxml = fxmlLoader.load();
                Message_item m_item_ctrl = fxmlLoader.getController();
                m_item_ctrl.setData(message);
                vbox_chat_messages.getChildren().add(m_item_fxml);
            } catch (IOException e) {
                System.out.println("[Contact_item.java] Pb load message_item");
                throw new RuntimeException(e);
            }
        }
    }

    public void afficher_new_mess (Message mess){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(ChatWindow.class.getResource("/Message_item.fxml"));

                try {
                    VBox m_item_fxml = fxmlLoader.load();
                    Message_item m_item_ctrl = fxmlLoader.getController();
                    m_item_ctrl.setData(mess);
                    vbox_chat_messages.getChildren().add(m_item_fxml);
                } catch (IOException e) {
                    System.out.println("[Contact_item.java] Pb load message_item");
                    throw new RuntimeException(e);
                }
            }
        });


    }

    public void afficher_new_contact(User contact){

        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ChatWindow.class.getResource("/Contact_item.fxml"));
            try {
                VBox c_item_fxml = fxmlLoader.load();
                Contact_item c_item_ctrl = fxmlLoader.getController();
                c_item_ctrl.setContact(contact);
                c_item_ctrl.setData();
                c_item_ctrl.setController_chat_windowController(this);
                hbox_utilisateurs_actifs.getChildren().add(c_item_fxml);
            } catch (IOException e) {
                System.out.println("[ChatWindow.java] Pb load contact_item");
                throw new RuntimeException(e);
            }
        });
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

    public void bouton_deconnection(ActionEvent event) throws IOException {
        Account_manager.deconnecte(User.getMoi());
        changeScene_DeconnexionWindow(event);
    }
    
    public void rafraichir_pseudo(ActionEvent event) {
    	l_mon_nom.setText(ChangerPseudoWindow.pseudo);
    }
    
    public void changer_pseudo(ActionEvent event) throws IOException { 
    	Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(ConnexionWindow.class.getResource("/ChangerPseudoWindow.fxml"));
        root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void handle_notification_pseudo_change(String new_pseudo, User new_contact) {
        if (new_contact.getId()!=User.getMoi().getId()) {
            Platform.runLater(() -> {
                try {
                   // NotificationPseudo.setController_chat_windowController(this);
                    FXMLLoader loader = new FXMLLoader(ChatWindow.class.getResource("/NotificationPseudo.fxml"));
                    Parent root = loader.load();
                    NotificationPseudo controller = loader.getController();
                    controller.setNewPseudo(new_pseudo);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root, 450, 170));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    
    public void envoyer_message(ActionEvent event) throws UnknownHostException, IOException {
    	String msg = tf_mess_a_envoyer.getText();
    	Message message = new Message(User.getMoi(), msg, Message.TypeMessage.MESSAGE_CONV);
    	DB_locale_manager.insert_message_db(message,0,last_contact_cliked.getId());
        afficher_new_mess(message);
    	TCP.envoyer_msg_tcp(last_contact_cliked, message);
    	tf_mess_a_envoyer.setText("");
    }



}
