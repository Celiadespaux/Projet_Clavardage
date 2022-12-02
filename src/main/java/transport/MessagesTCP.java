package transport;


import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class MessagesTCP implements Runnable {

	Socket recuSocket ;
	
	public MessagesTCP() {
		// TODO Auto-generated constructor stub
	}


	public void run() { //quand le thread recoit quelque chose 
		BufferedReader input ;
		
		try {
			input = new BufferedReader(new InputStreamReader(recuSocket.getInputStream()));
			String recu ;
			recu = input.readLine();
			
			input.close();
			
			//si message conversation -> prevenir le network manager et il affichera le msg
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
