package transport;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

import manager.DB_locale_manager;
import model.Message;
import java.net.InterfaceAddress ; 
import java.net.NetworkInterface ;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDP extends Thread{
	
		static int port;
		int length;
		boolean connecte ; 
		
		public UDP(int port, int length) {
			UDP.port = port;
			this.length = length;
			this.connecte = true ; 
		}
		
		public void setConnecte(boolean state) {
			this.connecte = state ; 
		}
		
	
	/**
	 * broadcast un message vers toutes les interfaces connectées 
	 * @param msg
	 * @throws IOException
	 */
	public void broadcast(String msg) throws IOException {
		
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
	
	
	/**
	 * recoit un broadcast (et va le donner a network manager)
	 * @throws IOException
	 */
	public void run() {
		
		DatagramSocket socket;
		try {
			
			socket = new DatagramSocket(port);
			byte[] buffer = new byte[length];
			
			while(this.connecte) {
				DatagramPacket dp = new DatagramPacket(buffer,buffer.length) ;
				socket.receive(dp);
				String recu = new String(dp.getData(), 0, dp.getLength());
				
				Message msg = Message.deconstruire_message(recu, DB_locale_manager.getMoi());
				Traitement_Messages.differencier_msg(msg);
			}
				
			buffer = new byte[1024];
				
			socket.close();
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
