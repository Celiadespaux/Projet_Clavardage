package TestMessages;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;
import model.Message;

public class UDPSender{
    /*public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        String mesg = "Je suis nouveau connecté";
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        DatagramPacket dp = new DatagramPacket(mesg.getBytes(), mesg.length(), ip, 1234);
        ds.send(dp);
        ds.close();
    }*/

	
	/*public static void main(String[] args) throws Exception {
		String msg = "Je suis la ";
		//int port = 4567;
				
		try {
			
			InetAddress addressforbid = InetAddress.getByName("0.0.0.0");
			
			List<InetAddress> broadcast = new ArrayList<InetAddress>() ;
			
			Enumeration<NetworkInterface> enume = NetworkInterface.getNetworkInterfaces();
			
			//creation liste d'adresse de broadcast
			while(enume.hasMoreElements()) {
				NetworkInterface netIn = enume.nextElement();
				
				List<InterfaceAddress> liste = netIn.getInterfaceAddresses();
				Iterator<InterfaceAddress> ite = liste.iterator();
				
				while(ite.hasNext()) {
					InterfaceAddress interaddress = ite.next();
					
					if (interaddress.getBroadcast()!= null && !interaddress.getBroadcast().equals(addressforbid)) {
						broadcast.add(interaddress.getBroadcast());
					}
				}
				
			}
			
			DatagramSocket socket = new DatagramSocket();
			byte buffer[] = null ;
			buffer = msg.getBytes();
			
			
			//for(InetAddress ip : broadcast) {
				
				InetAddress ip1 = InetAddress.getByName("127.0.0.1");
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length, ip1, 1234);
				socket.send(dp);
			//}
			
			socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}*/
	
	public static void main(String[] args) throws IOException {
		
		Socket link = new Socket("127.0.0.1",1234);
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(link.getOutputStream()));
		
		//construit un message avec contenu + id_expediteur + typemessage 
		String msgenvoie = Message.construire_message("Bonjour", 12, Message.TypeMessage.MESSAGE_CONV);
		
		out.write(msgenvoie);
		out.flush();
		
		link.close();
		
	}
}  
