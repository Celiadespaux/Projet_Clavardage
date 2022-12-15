package TestMessages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import model.Message;
import model.User;

/*DatagramSocket ds = new DatagramSocket(1234);
byte[] buf = new byte[1024];
DatagramPacket dp = new DatagramPacket(buf, 1024);
ds.receive(dp);*/

public class UDPReceiver {
    static /*public static void main(String[] args) throws Exception {
        
    		
    		DatagramSocket socket = new DatagramSocket(1234);
    		byte[] buffer = new byte[1024];
    		
    		DatagramPacket dp = new DatagramPacket(buffer,buffer.length) ;
    		socket.receive(dp);
    		
    		buffer = new byte[1024];
    		
    		socket.close();
    		
        String mesg = new String(dp.getData(), 0, dp.getLength());
        System.out.println("Recu sur cette adress IP :  " +dp.getAddress() + dp.getPort()+"  Son contenu est : " + mesg +"taille de message " +mesg.length());
        //ds.close();
    }*/
	
	User moi = new User(11,"celia","motdepasse","127.0.0.1",1234);
	public static void main(String[] args) throws IOException {
		
		ServerSocket serveur = new ServerSocket(1234);
		Socket link2 = serveur.accept();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(link2.getInputStream()));
		
		String recu = null;
		while (recu == null) recu = in.readLine() ;
		System.out.println(recu);
		
		Message msg = Message.deconstruire_message(recu, moi);
		System.out.println(msg.getContenu());
		System.out.println(msg.getDate());
		
		link2.close();
		serveur.close();
	}
}
