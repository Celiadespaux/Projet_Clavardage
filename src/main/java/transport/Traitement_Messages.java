package transport;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import manager.Network_manager;
import model.Message;
import model.User;


public class Traitement_Messages implements Runnable {

	Socket clientSocket ;
	ServerSocket serverSocket ;
	TCP serverTCP ; 
	
	static User moi = new User(1,"User1","MDP1","",1);
	
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
			//TODO ajouter nouveau utilisateur a l'annuaire (sender)
			Message nmsg = new Message(moi,"", Message.TypeMessage.RENVOIE_PSEUDO);
			TCP.envoyer_msg_tcp(msg.getSender(), nmsg);
			break;
		
		case DECONNECTE :
			//TODO supprimer utilisateur de l'annuaire
			Network_manager.deconnection();
			break;
			
		case RENVOIE_PSEUDO :
			//TODO ajouter utilisateur a l'annuaire (sender)
			break;
			
		case CHANGE_PSEUDO : 
			//TODO changer le pseudo de sender dans l'annuaire
			break;
	
		}
	}

	public void run() { //quand le thread recoit quelque chose 
		BufferedReader input ;
		
		try {
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String recu = input.readLine();
			
			Message msg = Message.deconstruire_message(recu, moi);
			differencier_msg(msg);
			
			input.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
