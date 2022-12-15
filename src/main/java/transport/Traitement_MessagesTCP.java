package transport;


import java.io.*;
import java.net.Socket;

import manager.Network_manager;
import model.Message;
import model.User;


public class Traitement_MessagesTCP implements Runnable {

	Socket recuSocket ;
	
	User moi ;
	
	public Traitement_MessagesTCP() {
		// TODO Auto-generated constructor stub
	}

	public void differencier_msg(Message msg) {
		Message.TypeMessage tmsg = msg.getType();
		
		switch(tmsg){
		
		case MESSAGE_CONV :
			Network_manager.message_recu(msg);
			break;
		
		case MESSAGE_BROADCAST :
			break;
		
		case CONNEXION :
			break;
			
		case DECONNEXION :
			break;
			
		case PSEUDO_CHANGE :
			break;
		
		}
	}

	public void run() { //quand le thread recoit quelque chose 
		BufferedReader input ;
		
		try {
			input = new BufferedReader(new InputStreamReader(recuSocket.getInputStream()));
			String recu = input.readLine();
			
			input.close();
			
			Message msg = Message.deconstruire_message(recu, moi);
			differencier_msg(msg);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
