package manager;

import model.Message;
import model.User;
import transport.TCP;
import transport.UDP;

import java.io.IOException;

public class Network_manager {

    User moi;
    TCP Tcp;
    UDP Udp;
    public Network_manager() {
        // TODO Auto-generated constructor stub
    }

    //recevoir un message (tcp) et ajouter a la db
    public static void message_recu(Message msg) {
        DB_locale_manager.insert_message_db(msg);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    //run servers ??
    public void runservers() throws IOException {
        new Thread(this.Udp).start();
        ; //preciser le num de port ??
        new Thread(this.Tcp).start();
        ;
    }


    //dire qu'on est connecté
    //dire qu'on change de pseudo
    //dire qu'on se deconnecte
    //

    //envoyer un message (tcp)
    public void envoyer_message(User Destinataire, User moi, Message msg) throws IOException {
        TCP.envoyer_msg_tcp(Destinataire, moi, msg);
    }

}
