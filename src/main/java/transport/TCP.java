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


public class TCP {
	
			//Pour plus tard
			//String adresse_destinataire = Destinataire.getIp();
			//int port = Expediteur.getPort();
	
	String adresse_test = "127.0.0.1";
	int port_test = 1024;
	
	public void envoyer_msg_tcp (User Destinataire, User Expediteur, /*Message msg*/ String message1) throws UnknownHostException, IOException {	 
		
		
		Socket link = new Socket(adresse_test,port_test);
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(link.getOutputStream()));
		
		out.write(message1);
		out.flush();
		
		link.close();
	}
	
	
	public void listen_msg_tcp () throws IOException {
		
		ServerSocket serveur = new ServerSocket(port_test);
		Socket link2 = serveur.accept();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(link2.getInputStream()));
		
		String recu = null;
		while (recu == null) recu = in.readLine() ;
		System.out.println(recu);
		
		link2.close();
		serveur.close();
	}
	
}
