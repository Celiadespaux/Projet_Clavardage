package manager;

import java.sql.*;
import java.util.*;
import model.*;

public class DB_manager {
	
	//
	// CONFIGURATION
	//
	
	static Connection con = null;

    
	// TODO prends en arg l'url du path du fichier a creer localement
	/**
	 * Charge le pilote et se connecte a la bdd
	 * @return une connection a une bdd
	 */
    public static void connectionDB(){

        //Chargement pilote
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("[DB_Manager] Problème chargement pilote");
            e.printStackTrace();
        }
        
        

        //Connexion à la BDD 
  
        //String url = "jdbc:sqlite:" + Path+ BdName;
        
        try {
        	// TODO recuperer le path des utilisateurs qd ils se connectent a l'application pour le mettre a la place de testDB
            con = DriverManager.getConnection("jdbc:sqlite:testDB"); // //localhost/test"   
            System.out.println("[DB_Manager] Connection a la bdd ok");
        } catch (Exception e) {
            System.out.println("[DB_Manager] Problème connexion à la BDD");
            e.printStackTrace();
        }
        
        
    }
    
    
  
         
    /**
     * Creation des tables utilsateur, discussion et annuaire
     * @param con la connection a la BDD
     */
    public static void creer_tables_DB() {
    	
    	  String sql_utilisateur = "CREATE TABLE IF NOT EXISTS utilisateur (\n"
                  + "	cle integer PRIMARY KEY AUTOINCREMENT,\n"
                  + "	id integer,\n"
                  + "	pseudo varchar(20),\n"
                  + "	mdp varchar(20),\n"
                  + "	ip_adr varchar(20),\n"
                  + "	port_nb integer,\n"
                  + "	UNIQUE (ID)\n"
                  + ");";
    	  
    	  //1 table par utilisateur
    	  String sql_discussion = "CREATE TABLE IF NOT EXISTS discussion (\n"
                  + "	id_dest integer,\n"
                  + "	date text,\n"
                  + "	message text,\n"
                  + "   FOREIGN KEY (id_dest) REFERENCES utilisateur(id),"
                  + "	PRIMARY KEY (id_dest,date)\n"
                  + ");";
    	  
    	  String sql_annuaire = "CREATE TABLE IF NOT EXISTS annuaire (\n"
                  + "	id_user integer PRIMARY KEY,\n"
                  + "	id_ami1 integer,\n"
                  + "	id_ami2 integer,\n"
                  + "	id_ami3 integer,\n"
                  + "	id_ami4 integer,\n"
                  + "	id_ami5 integer,\n"
                  + "	id_ami6 integer,\n"
                  + "	id_ami7 integer,\n"
                  + "	id_ami8 integer,\n"
                  + "	id_ami9 integer,\n"
                  + "	id_ami10 integer,\n"
                  + "   FOREIGN KEY (id_user) REFERENCES utilisateur(id)"
                  + ");";
    	  
    	  
    	try {
			Statement stmt = con.createStatement();
			stmt.execute(sql_utilisateur);
			stmt.execute(sql_discussion);
			stmt.execute(sql_annuaire);
			System.out.println("[DB_Manager] Creation tables bdd ok");
		} catch (SQLException e) {
			System.out.println("[DB_Manager] Problème creation tables DB");
			e.printStackTrace();
		}
  
    }
    
    //
    // TABLE MESSAGES
    //
  
    /**
     * Enregistre un message dans le tableau discussion
     * @param con la connection
     * @param msg
     * @param id_user1
     * @param id_user2
     */
    public static void insert_message_db (Message msg) {
    	
        String sql = "INSERT INTO discussion VALUES(?,?,?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, msg.getId_dest());
            pstmt.setString(2, msg.getDate());
            pstmt.setString(3, msg.getContenu());
            pstmt.executeUpdate();
            System.out.println("[DB_Manager] Ajout message dans table ok");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    

    /**
     *
     * @return un tabeau de tous les messages de l'utlisateur
     * @throws SQLException
     */
    public static ArrayList<Message> showHistory() throws SQLException{
    	//TODO poffiner en fct de la convo
        String query = "SELECT * FROM discussion ";

        Statement statement = con.createStatement();

        ResultSet result = statement.executeQuery(query);
        ArrayList<Message> list = new ArrayList<>();

        // loop through the result set
        while (result.next()) {

        	//TODO changer id expe
            Message m = new Message(
            		result.getInt("id_dest"),
            		01,
                    result.getString("date"),
                    result.getString("message"),
                    Message.TypeMessage.MESSAGE_CONV);
            list.add(m);
        }
        System.out.println(list);
        return list;
    }
    
    
    /**
     * Effacer l'historique
     * @throws SQLException
     */
    private static void deleteHistory() throws SQLException{
        String query = "DELETE FROM discussion";
        Statement statement = con.createStatement();
        statement.execute(query);
        System.out.println("[DB_Manager] Le contenu de la table discussion est supprimé");

    }
    
    //
    // TABLE UTILISATEURS
    //
    
    
    /**
     *  Ajout d'un utlisateur dans le tableau utilisateur
     * @param utilisateur objet User
     */
    public static void add_utlisateur_db (User utilisateur) {
    	
        String sql = "INSERT INTO utilisateur ('id','pseudo','mdp','ip_adr','port_nb') VALUES (?,?,?,?,?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)){
        	pstmt.setInt(1, utilisateur.getId());
            pstmt.setString(2, utilisateur.getPseudo());
            pstmt.setString(3, utilisateur.getMdp());
            pstmt.setString(4, utilisateur.getIp());
            pstmt.setInt(5,utilisateur.getPort());
            pstmt.executeUpdate();
            System.out.println("[DB_Manager] Ajout utlisateur dans table ok");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	
    }
    
    /**
     * Checks if a user with the login is already created or not
     * @param 
     * @return true if already exists, else false
     * @throws SQLException 
     */
    public static boolean verifier_pseudo_libre(String pseudo) throws SQLException {
    	
        
        
        String sql = "SELECT * FROM utilisateur where pseudo='"+pseudo+"'";
        
       
        
    	Statement stmt = con.createStatement();
        
    	
        ResultSet rs = stmt.executeQuery(sql);
        
  
        //System.out.println("login Aux = " + loginAux);
     
        return (!rs.next());
    }
    
    
    /**
     * Changer le pseudo de l'utilisateur
     * @param pseudo
     * @param id
     */
    public static void maj_pseudo(String pseudo, int id) {
    	
    	String sql = "UPDATE utilisateur SET pseudo = ? WHERE id = ?";
    
        PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pseudo);
		    pstmt.setDouble(2, id);
		    pstmt.executeUpdate();
		    System.out.println("[DB_Manager] Pseudo utilisateur correctement maj");
		} catch (SQLException e) {
			System.out.println("[DB_Manager] Probleme lors de la maj du pseudo");
			e.printStackTrace();
		}
        
       
    }
    
    /**
    *
    * @return un tabeau de tous les messages de l'utlisateur
    * @throws SQLException
    */
   public static ArrayList<User> showUsers() throws SQLException{
   	//TODO poffiner en fct de la convo
       String query = "SELECT * FROM utilisateur ";

       Statement statement = con.createStatement();

       ResultSet result = statement.executeQuery(query);
       ArrayList<User> list = new ArrayList<>();

       // loop through the result set
       while (result.next()) {

       	//TODO changer id expe
           User u = new User (
        		   result.getInt("id"),
        		   result.getString("Pseudo"),
        		   result.getString("mdp"),
        		   result.getString("ip_adr"),
        		   result.getInt("port_nb"));
           list.add(u);
       }
       System.out.println(list);
       return list;
   }
    
    
    /**
     * Effacer tous les utilisateurs
     * @throws SQLException
     */
    private static void deleteUsers() throws SQLException{
        String query = "DELETE FROM utilisateur";
        Statement statement = con.createStatement();
        statement.execute(query);
        System.out.println("[DB_Manager] Le contenu de la table utilisateur est supprimé");

    }
    
    //
    // MAIN
    //
 

   

    public static void main(String[] args) throws SQLException {
		
    	
    	
    	connectionDB();
		 deleteUsers();
	        
		
		Message msg = new Message(01,02, "test", Message.TypeMessage.MESSAGE_CONV);
		insert_message_db(msg);
        showHistory();
        deleteHistory();
        showHistory();
        User user1 = new User(111,"toto", "motdepasse", "143.112.212.233", 3348);
        User user2 = new User(222,"pierre", "motdepass24e", "143.112.212.233", 3358);
        User user3 = new User(333,"jack", "motdepass24e", "143.112.212.233", 3379);
        add_utlisateur_db(user1);
        add_utlisateur_db(user2);
        add_utlisateur_db(user3);
        
        showUsers(); 
        if (verifier_pseudo_libre("paul")) {
        	System.out.println("[DB_Manager] Pseudo paul libre");
        	} 
        else {
        	System.out.println("[DB_Manager] Pseudo paul occupe");
        }
        if (verifier_pseudo_libre("toto")) {
        	System.out.println("[DB_Manager] Pseudo toto libre");
        	} 
        else {
        	System.out.println("[DB_Manager] Pseudo toto occupe");
        }
        
        maj_pseudo("jackie",333);
        
       
    }
    
    
}