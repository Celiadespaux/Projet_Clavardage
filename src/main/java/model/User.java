package model;

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
        return "My id is " + id 
        		+ ", pseudo : " + pseudo
        		+ ", @ip : " + ip
        		+", port_nb : " + port ;
    }
	
	
	

}