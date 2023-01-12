package manager;

import java.io.IOException;
import java.util.regex.*;
import model.*;

public class Account_manager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	
	static int maxLength = 30;
	//verifie si pseudo pas trop long ou pas caracteres speciaux
	public static boolean verifier_pseudo_valide(String pseudo) {
		Pattern p = Pattern.compile("[^A-Za-z0-9]");
	    Matcher m = p.matcher(pseudo);
	    boolean res = true ; 
		res = res && (pseudo.length() < maxLength ) ; 
		res = res && (pseudo.length() > 0) ; 
		res = res && !m.find();
		return res ; 
	}
	
	
	public static void connecte(User sender) throws IOException {
		String msg = Message.construire_message("nul",sender,Message.TypeMessage.CONNECTE);
		Network_manager.Udp.broadcast(msg);
	}
	
	
	public static void deconnecte(User sender) throws IOException {
		String msg = Message.construire_message("",sender,Message.TypeMessage.DECONNECTE);
		Network_manager.Udp.broadcast(msg);
		Network_manager.deconnection();
		}
	
	public static void changer_pseudo(User sender) throws IOException {
		String msg = Message.construire_message("",sender,Message.TypeMessage.CHANGE_PSEUDO);
		Network_manager.Udp.broadcast(msg);
	}
	
	}

