package transport;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import manager.Network_manager;
import model.Message;
import model.User;
import model.Message.TypeMessage;


public class Traitement_MessagesTCP implements Runnable {

	Socket clientSocket ;
	ServerSocket serverSocket ;
	TCP serverTCP ; 
	
	User moi ;
	
	public Traitement_MessagesTCP(Socket client, ServerSocket serverSocket, TCP serverTCP) {
		this.clientSocket = client ;
		this.serverSocket = serverSocket ;
		this.serverTCP = serverTCP ; 
	}

	public static void differencier_msg(Message msg) {
		Message.TypeMessage tmsg = msg.getType();
		
		switch(tmsg){
		
		case MESSAGE_CONV :
			Network_manager.message_recu(msg);
			break;
		
		case CONNECTE :
			//ajouter nouveau utilisateur a l'annuaire
			String nmsg = Message.construire_message("", msg.getId_expe(), Message.TypeMessage.RENVOIE_PSEUDO);
			envoyer_msg_tcp(Destinataire, nmsg);
			break;
		
		case DECONNECTE :
			//supprimer utilisateur de l'annuaire
			break;
			
		case RENVOIE_PSEUDO :
			//ajouter utilisateur a l'annuaire
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
