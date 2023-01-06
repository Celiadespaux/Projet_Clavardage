package manager;

import java.io.IOException;
import model.*;
import transport.*;

public class Network_manager {

	static TCP Tcp;
	static UDP Udp;
	
	public Network_manager() {
		// TODO Auto-generated constructor stub
		Tcp = new TCP();
		Udp = new UDP(5001, 1000) ;

	}
	
	
	
	
	//run servers ??
	public void runservers() throws IOException {
		new Thread(Network_manager.Udp).start();; //preciser le num de port ??
		new Thread(Network_manager.Tcp).start();;
	}

	
	//envoyer un message (tcp)
	public void envoyer_message(User Destinataire, User Expediteur, Message msg) throws IOException {
		TCP.envoyer_msg_tcp(Destinataire, msg);
	}
	
	//recevoir un message (tcp) et ajouter a la db
	public static void message_recu(Message msg) {
		DB_manager.insert_message_db(msg);
	} 
	
	public static void deconnection() {
		Tcp.setConnecte(false);
		Udp.setConnecte(false) ;
	}
	
	
	//dire qu'on est connecté
	//dire qu'on change de pseudo 
	//dire qu'on se deconnecte
	//
	
	

	public static void main(String[] args) {
		new Thread(new UDP(5001, 1000)).start();
	}

}
