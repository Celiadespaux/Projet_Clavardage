package manager;

public class Account_manager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//verifie si pseudo pas trop long ou pas caracteres speciaux
	public static void verifier_pseudo_valide(String pseudo) {
		Pattern p = Pattern.compile("[^A-Za-z0-9]");
	    Matcher m = p.matcher(pseudo);
	    boolean res = true ; 
		res = res && (pseudo.length() < maxLength) ; 
		res = res && (pseudo.length() > 0) ; 
		res = res && !m.find();
		return res ; 
	}
	
	public static void demander_pseudo(Int id) {
		String msg = Message.construire_message("",id,TypeMessage.DEMANDE_PSEUDO);
		UDP.broadcast(msg);
	}
	
	public static void demander_pseudo_libre(String pseudo) {
		available = true ;
		long time = 0;
		long start = System.currentTimeMillis();
		long finish = 0;
		String msg = Message.construire_message(pseudo, id ,MessageType.PSEUDO_LIBRE);

		UDP.broadcast(msg);

		while(timeElapsed<1000) {
			finish = System.currentTimeMillis();
			timeElapsed = finish - start;			
		}
		return available ;
	}
	
	public static void pseudo_pas_dispo() {
		available = false ;
	}

}
