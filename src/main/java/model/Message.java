package model;

import java.time.LocalDateTime;




public class Message {
	
	public static enum TypeMessage {
		MESSAGE_CONV,
		MESSAGE_BROADCAST,
		CONNEXION,
		DECONNEXION,
		PSEUDO_CHANGE
	}

	private int id_dest;
	private int id_expe;
	private String date;
	private String contenu;
	private TypeMessage type ;
	
	
	
    public Message(int id_dest, int id_expe, String contenu, TypeMessage type) {
		this.id_dest = id_dest;
		this.id_expe = id_expe;
		this.date = LocalDateTime.now().toString();
		this.contenu = contenu;
		this.type = type ;
	}
	
	public Message(int id_dest,int id_expe, String date, String content, TypeMessage type){
	        this.id_dest = id_dest;
			this.id_expe = id_expe;
	        this.date = date;
	        this.contenu = content;
	        this.type = type ;
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
	

	public int getId_dest() {
		return id_dest;
	}

	public void setId_dest(int id_dest) {
		this.id_dest = id_dest;
	}

	
    @Override
    public String toString() {
        return "My id is  " + id_expe + " "
                + date + " -> Message : " + contenu;
    }

	public TypeMessage getType() {
		return type;
	}

	public void setType(TypeMessage type) {
		this.type = type;
	}


}
