package transport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import model.*;


public class TCP extends Thread{
	

	boolean connecte = true;
	boolean available = true;
	 
	public boolean getAvailable() {
		return this.available ; 
	}
		
	public void setConnecte(boolean state) {
		this.connecte = state ; 
	}
		
	
			//Pour plus tard
			//String adresse_destinataire = Destinataire.getIp();
			//int port = Expediteur.getPort();
	
	String adresse_test = "127.0.0.1";
	static int port_test = 1024;
	
	public static void envoyer_msg_tcp (User Destinataire, User Expediteur, Message msg) throws UnknownHostException, IOException {	 
		
		
		Socket link = new Socket(Destinataire.getIp(),Destinataire.getPort());
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(link.getOutputStream()));
		
		//construit un message avec contenu + id_expediteur + typemessage 
		String msgenvoie = Message.construire_message(msg.getContenu(), Expediteur.getId(), Message.TypeMessage.MESSAGE_CONV);
		
		out.write(msgenvoie);
		out.flush();
		
		link.close();
	}
	
	
	public void run () {
		
		ServerSocket serveur;
		try {
			
			serveur = new ServerSocket(port_test);
			Socket link2 = serveur.accept();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(link2.getInputStream()));
			
			String recu = null;
			while (recu == null) recu = in.readLine() ;
			System.out.println(recu);
			
			link2.close();
			serveur.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.available = true ; 
		
	}
	
}
