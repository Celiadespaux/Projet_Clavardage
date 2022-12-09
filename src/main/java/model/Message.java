package model;

import java.time.LocalDateTime;

public class Message {

	private int id_dest;
	private int id_expe;
	private String date;
	private String contenu;

	
    public Message(int id_dest, int id_expe, String contenu) {
		this.id_dest = id_dest;
		this.id_expe = id_expe;
		this.date = LocalDateTime.now().toString();
		this.contenu = contenu;
	}
	
	public Message(int id_dest,int id_expe, String date, String content){
	        this.id_dest = id_dest;
			this.id_expe = id_expe;
	        this.date = date;
	        this.contenu = content;
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


}
