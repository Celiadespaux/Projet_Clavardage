package model;

import java.time.LocalDateTime;


public class Message {
	
	public static enum TypeMessage {
		MESSAGE_CONV,
		CONNECTE,
		DECONNECTE,
		RENVOIE_PSEUDO,
		CHANGE_PSEUDO
		
	}

	private User sender;
	private String date;
	private String contenu;
	private TypeMessage type ;
	
    public Message(User sender, String contenu, TypeMessage type) {
		this.sender = sender;
		this.date = LocalDateTime.now().toString();
		this.contenu = contenu;
		this.type = type ;
	}
	
	public Message(User sender, String date, String content, TypeMessage type){
	        this.sender = sender;
	        this.date = date;
	        this.contenu = content;
	        this.type = type ;
	}
	
	/*public static String construire_message(String contenu, int id_dest, TypeMessage type) {
		return (contenu + "&&" + id_dest + "&&"+ type);
	}*/
	public static String construire_message(String contenu, User sender, TypeMessage type) {
		String util = User.construire_user(sender);
		return (contenu + "&&" + util + "&&"+ type);
	}
	

	public static Message deconstruire_message(String msg, User moi) {
		String[] m = msg.split("&&");
		
		User util = User.deconstruire_user(m[1]);
		Message message = new Message(util, m[0], TypeMessage.valueOf(m[2].toUpperCase()));
		return(message);
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	

	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}


    @Override
    public String toString() {
        return "[Message] Sender id : " + sender.getId()
        		+ ", date :  " + date 
        		+ " -> Message : " + contenu;
    }

	public TypeMessage getType() {
		return type;
	}

	public void setType(TypeMessage type) {
		this.type = type;
	}


}
