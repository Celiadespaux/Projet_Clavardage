package transport;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import model.*;


public class TCP extends Thread{
		
	boolean connecte = true ; 
	
	public void setConnecte(boolean state) {
		this.connecte = state ; 
	}
	
	
	public static void envoyer_msg_tcp (User Destinataire, Message msg) throws UnknownHostException, IOException {	 
		
		
		Socket link = new Socket(Destinataire.getIp(),Destinataire.getPort());
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(link.getOutputStream()));
		
		//construit un message avec contenu + id_expediteur + typemessage 
		String msgenvoie = Message.construire_message(msg.getContenu(), msg.getSender(), msg.getType());
		
		out.write(msgenvoie);
		out.flush();
		
		link.close();
	}
	
	
	public void run () {
		
		int port = 6000;
		ServerSocket serveur;
		Socket link2 ;
		
		try {
			
			serveur = new ServerSocket(port);
			
			while(connecte) {
				link2 = serveur.accept();
				new Thread(new Traitement_Messages(link2, serveur, this)).start() ;
			}
			
			serveur.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
}
