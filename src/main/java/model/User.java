package model;

import java.net.UnknownHostException;

import manager.DB_locale_manager;
import manager.Network_manager;
import view.ConnexionWindow;

public class User {
	
	
	private int id;
	private String pseudo;
	private String mdp;
	private String ip;
	private int port;
	
	public User(int id, String pseudo, String mdp, String ip, int port) {
		this.id = id;
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.ip = ip;
		this.port = port;
		
	}
	
	//static int myid = ConnexionWindow.getId();
	static int myid = 777;
	public static int getMyId() {
		return myid;
	}
	
	public static void creer_moi() throws UnknownHostException {
		//moi.setId(ConnexionWindow.getId());
		DB_locale_manager.maj_pseudo("moi",ConnexionWindow.getId());
		//moi.setIp(Network_manager.getMyIPString());
		//moi.setPort(6000);
	}
	    
	public static User getMoi() {
		return DB_locale_manager.getUserfromId(myid);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
    @Override
    public String toString() {
        return "[User] My id is " + id
        		+ ", pseudo : " + pseudo
        		+ ", @ip : " + ip
        		+", port_nb : " + port ;
    }

	public static String construire_user(User user) {
		int id = user.getId();
		String pseudo = user.getPseudo();
		String mdp = user.getMdp();
		String ip = user.getIp();
		int port = user.getPort();
		return (id + "/-/" + pseudo + "/-/"+ mdp + "/-/" + ip + "/-/" + port);
	}
	
	public static User deconstruire_user(String user) {
		String[] m = user.split("/-/");
		
		int x=Integer.parseInt(m[0]); 
		int y=Integer.parseInt(m[4]); 
		User Utilisateur = new User(x, m[1], m[2], m[3], y);
		return(Utilisateur);
	}
	

}