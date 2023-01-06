package model;

import java.time.LocalDateTime;


public class Message {
	
	public static enum TypeMessage {
		MESSAGE_CONV,
		CONNECTE,
		DECONNECTE,
		RENVOIE_PSEUDO
		
	}

	private static int id_dest;
	private int id_expe;
	private String date;
	private String contenu;
	private TypeMessage type ;
	
    public Message(int id_dest, int id_expe, String contenu, TypeMessage type) {
		Message.id_dest = id_dest;
		this.id_expe = id_expe;
		this.date = LocalDateTime.now().toString();
		this.contenu = contenu;
		this.type = type ;
	}
	
	public Message(int id_dest,int id_expe, String date, String content, TypeMessage type){
	        Message.id_dest = id_dest;
			this.id_expe = id_expe;
	        this.date = date;
	        this.contenu = content;
	        this.type = type ;
	}
	
	public static String construire_message(String contenu, int id_dest, TypeMessage type) {
		return (contenu + "&&" + id_dest + "&&"+ type);
	}
	

	public static Message deconstruire_message(String msg, User moi) {
		String[] m = msg.split("&&");
		
		Message message = new Message(moi.getId(), Integer.valueOf(m[1]), m[0], TypeMessage.valueOf(m[2].toUpperCase()));
		return(message);
	}
	
	public int getId_expe() {
		return id_expe;
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
	

	public static int getId_dest() {
		return id_dest;
	}

	public void setId_dest(int id_dest) {
		Message.id_dest = id_dest;
	}

	
    @Override
    public String toString() {
        return "My id is : " + id_expe 
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
