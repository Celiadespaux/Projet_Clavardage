package manager;

import java.io.IOException;
import java.util.regex.*;
import model.*;
import transport.*;

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
	
	
	public static void connecte(int id) throws IOException {
		String msg = Message.construire_message("",id,Message.TypeMessage.CONNECTE);
		UDP.broadcast(msg);
	}
	
	
	public static void deconnecte(int id) throws IOException {
		String msg = Message.construire_message("",id,Message.TypeMessage.DECONNECTE);
		UDP.broadcast(msg);
		Network_manager.deconnection();
		}
	}

