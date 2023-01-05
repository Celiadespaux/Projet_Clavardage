package manager;

import java.io.IOException;
import model.*;
import transport.*;

public class Network_manager {

	public Network_manager() {
		// TODO Auto-generated constructor stub
	}
	
	User moi ;
	TCP Tcp;
	UDP Udp;
	
	
	//run servers ??
	public void runservers() throws IOException {
		new Thread(this.Udp).start();; //preciser le num de port ??
		new Thread(this.Tcp).start();;
	}

	
	//envoyer un message (tcp)
	public void envoyer_message(User Destinataire, User moi, Message msg) throws IOException {
		TCP.envoyer_msg_tcp(Destinataire, moi, msg);
	}
	
	//recevoir un message (tcp) et ajouter a la db
	public static void message_recu(Message msg) {
		DB_manager.insert_message_db(msg);
	} 
	
	
	
	
	//dire qu'on est connecté
	//dire qu'on change de pseudo 
	//dire qu'on se deconnecte
	//
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
