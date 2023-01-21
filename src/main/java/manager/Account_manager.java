package manager;

import java.io.IOException;
import java.util.regex.*;
import model.*;
import model.Message.TypeMessage;

public class Account_manager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	static boolean dispo;
	
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
	
	public static boolean verifier_pseudo_libre(String pseudo) throws IOException {
		dispo = true ;
		long timeElapsed = 0;
		long start = System.currentTimeMillis();
		long finish = 0;
		String msg = Message.construire_message(pseudo,  User.getMoi(), TypeMessage.PSEUDO_DISPO);
		
		Network_manager.Udp.broadcast(msg);

		while(timeElapsed<1000) {
			finish = System.currentTimeMillis();
			timeElapsed = finish - start;			
		}
		
		return dispo ;
	}
	
	public static void pseudoPasDispo() {
		dispo = false;
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
	
	public static void changer_pseudo(User sender, String newPseudo ) throws IOException {
		String message = "L'utilisateur " + User.getMoi().getPseudo() + " a changé de pseudo en " + newPseudo;
		String msg = Message.construire_message(message,sender,Message.TypeMessage.CHANGE_PSEUDO);
		Network_manager.Udp.broadcast(msg);
	}
	
	}

