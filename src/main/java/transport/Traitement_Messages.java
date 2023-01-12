package transport;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import manager.Account_manager;
import manager.DB_locale_manager;
import manager.Network_manager;
import model.Message;
import model.User;


public class Traitement_Messages implements Runnable {

	Socket clientSocket ;
	ServerSocket serverSocket ;
	TCP serverTCP ; 
	
	static User moi = new User(1,"User1","MDP1","127.0.0.1",6000);
	
	public static User getMoi() {
		return moi;
	}
	
	public Traitement_Messages(Socket client, ServerSocket serverSocket, TCP serverTCP) {
		this.clientSocket = client ;
		this.serverSocket = serverSocket ;
		this.serverTCP = serverTCP ; 
	}

	public static void differencier_msg(Message msg) throws UnknownHostException, IOException {
		Message.TypeMessage tmsg = msg.getType();
		
		switch(tmsg){
		
		case MESSAGE_CONV :
			Network_manager.message_recu(msg);
			break;
		
		case CONNECTE :
			DB_locale_manager.add_utlisateur_db(msg.getSender());
			Message nmsg = new Message(getMoi(),"", Message.TypeMessage.RENVOIE_PSEUDO);
			TCP.envoyer_msg_tcp(msg.getSender(), nmsg);
			break;
		
		case DECONNECTE :
			//TODO supprimer utilisateur de l'annuaire
			Network_manager.deconnection();
			break;
			
		case RENVOIE_PSEUDO :
			DB_locale_manager.add_utlisateur_db(msg.getSender());
			break;
			
		case CHANGE_PSEUDO : 
			DB_locale_manager.maj_pseudo(msg.getSender().getPseudo(), msg.getSender().getId());
			break;
			
		case PSEUDO_DISPO:
			Account_manager.pseudoPasDispo();
			break;
	
		}
	}

	public void run() { //quand le thread recoit quelque chose 
		BufferedReader input ;
		
		try {
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String recu = input.readLine();
			
			Message msg = Message.deconstruire_message(recu, getMoi());
			differencier_msg(msg);
			
			input.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}


}
