package model;


import java.time.LocalDate;
import java.time.LocalTime;

public class Message {
	
	private String contenu;
	public LocalDate date;
	public LocalTime heure;
	
	public Message(String contenu, LocalDate date, LocalTime heure) {
		this.contenu = contenu;
		this.date = date;
		this.heure = heure;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	

}
