package manager;

import java.io.IOException;
import model.*;
import transport.*;
import view.ChatWindow;

public class Network_manager {

	static TCP Tcp;
	static UDP Udp;

	private static ChatWindow controller_chat_window;;

	public Network_manager() {
		// TODO Auto-generated constructor stub
		Tcp = new TCP();
		Udp = new UDP(5001, 10000) ;

	}

	public static void setController_chat_windowController(ChatWindow controller) {
		controller_chat_window = controller ;
	}


	//run servers 
	public void runservers() throws IOException {
		new Thread(Network_manager.Udp).start();; 
		new Thread(Network_manager.Tcp).start();;
	}
	
	
	//recevoir un message (tcp) et ajouter a la db
	public static void message_recu(Message msg) {
		DB_locale_manager.insert_message_db(msg,1,msg.getSender().getId());
		controller_chat_window.afficher_new_mess(msg);
	} 
	
	public static void deconnection() {
		Tcp.setConnecte(false);
		Udp.setConnecte(false) ;
	}
	

	public static void main(String[] args) {
		new Thread(new UDP(5001, 1000)).start();
	}

}
