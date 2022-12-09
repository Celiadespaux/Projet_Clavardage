package model;

import java.time.LocalDateTime;

public class Message {

	private String name;
	private String date;
	private String contenu;

	
    public Message(String name, String contenu) {
		this.name = name;
		this.date = LocalDateTime.now().toString();
		this.contenu = contenu;
	}
	
	public Message(String name, String date, String content){
	        this.name = String.valueOf(name);
	        this.date = date;
	        this.contenu = content;
	}
	

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	

    @Override
    public String toString() {
        return "My name is  " + name + " "
                + date + " -> Message : " + contenu;
    }


}
