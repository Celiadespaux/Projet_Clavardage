package transport;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
import java.net.InterfaceAddress ; 
import java.net.NetworkInterface ;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDP {
	
	
	/**
	 * broadcast un message vers toutes les interfaces connectées 
	 * @param msg
	 * @throws IOException
	 */
	public static void broadcast(String msg) throws IOException {
		
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
					InterfaceAddress interaddress = ite.next();
					
					if (interaddress.getBroadcast()!= null && !interaddress.getBroadcast().equals(addressf)) {
						broadcast.add(interaddress.getBroadcast());
					}
				}
				
			}
			
			DatagramSocket socket = new DatagramSocket();
			byte buffer[] = null ;
			buffer = msg.getBytes();
			
			
			for(InetAddress ip : broadcast) {
				
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length, ip, port);
				socket.send(dp);
			}
			
			socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	static int length ; 
	
	/**
	 * recoit un broadcast (et va le donner a network manager)
	 * @throws IOException
	 */
	public static void recevoir_broadcast() throws IOException {
		
		DatagramSocket socket = new DatagramSocket(4568);
		byte[] buffer = new byte[length];
		
		DatagramPacket dp = new DatagramPacket(buffer,buffer.length) ;
		socket.receive(dp);
		//System.out.println("Data : " + Arrays.toString(dp.getData()));
		
		buffer = new byte[length];
		
		socket.close();
		
	}
	
	
	 public static void main(String[] argv) throws IOException {
		 
			 broadcast("coucou");
			 recevoir_broadcast();
		 
		//TODO tester la fct broadcast
		//TODO
	}
	
}
