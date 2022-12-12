package TestMessages;

import java.net.*;

public class UDPReceiver {
    public static void main(String[] args) throws Exception {
        /*DatagramSocket ds = new DatagramSocket(1234);
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, 1024);
        ds.receive(dp);*/
    		
    		DatagramSocket socket = new DatagramSocket(1234);
    		byte[] buffer = new byte[1024];
    		
    		DatagramPacket dp = new DatagramPacket(buffer,buffer.length) ;
    		socket.receive(dp);
    		
    		buffer = new byte[1024];
    		
    		socket.close();
    		
        String mesg = new String(dp.getData(), 0, dp.getLength());
        System.out.println("Recu sur cette adress IP :  " +dp.getAddress() + dp.getPort()+"  Son contenu est : " + mesg +"taille de message " +mesg.length());
        //ds.close();
    }
}
