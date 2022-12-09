package transport;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
import java.net.InterfaceAddress ; 
import java.net.NetworkInterface ;
import java.net.SocketException;
import java.net.UnknownHostException; 

public class UDP {
	
	
	
	public void broadcast(String msg) {
		
		int port = 4567;
		
		byte[] addressforbid = new byte[]{(byte)0,(byte)0,(byte)0,(byte)0};
				
		try {
			
			InetAddress addressf = InetAddress.getByAddress(addressforbid);
			
			List<InetAddress> broadcast = new ArrayList<InetAddress>() ;
			
			Enumeration<NetworkInterface> enume = NetworkInterface.getNetworkInterfaces();
			
			//creation liste d'adresse de broadcast
			while(enume.hasMoreElements()) {
				NetworkInterface netIn = enume.nextElement();
				
				List<InterfaceAddress> liste = netIn.getInterfaceAddresses();
				Iterator<InterfaceAddress> ite = liste.iterator();
				
				while(ite.hasNext()) {
					//finir de creer la liste avec les add multicast -> et envoyer le message
				}
				
			}
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	

}
