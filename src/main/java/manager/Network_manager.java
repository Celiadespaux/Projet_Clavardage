package manager;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.User;
import transport.TCP;

public class Network_manager {

	public Network_manager() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static enum TypeMessage {
		MESSAGE_CONV,
		MESSAGE_BROADCAST,
		CONNEXION,
		DECONNEXION,
		PSEUDO_CHANGE
	}
	//run servers ??
	
	//envoyer un message (tcp)
	public void envoyer_message(User Destinataire, String msg) throws IOException {
		TCP.envoyer_msg_tcp(Destinataire, msg);
	}
	
	//recevoir un message (tcp) et ajouter a la db
	public void message_recu(User Emetteur, String msg) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		String dateformatter = formatter.format(date);
		
		//TODO ajouter message a la db avec emetteur.ip, myip, msg, dateformatter
	} 
	
	
	//pseudo dispo
	//dire qu'on est connecté
	//dire qu'on change de pseudo 
	//dire qu'on se deconnecte
	//
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
