package manager;

import java.sql.*;
import java.util.*;
import java.io.*;
import model.*;
import manager.*;

public class DB_manager {
	
	static Connection con = null;
	
	//TODO mettre @param @retuen

    
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
            con = DriverManager.getConnection("jdbc:sqlite:testDB2"); // //localhost/test"   
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
                  + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                  + "	pseudo varchar(20),\n"
                  + "	mdp varchar(20),\n"
                  + "	ip_adr varchar(20),\n"
                  + "	port_nb integer\n"
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
    
    //TODO supprimer ou completer
    public static boolean verfier_convo_existante() {
    	return false ;
    	
    }
    
  
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
            System.out.println("[DB_Manager] Ajout ligne dans table ok");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    public static void delethistory() throws SQLException{
        String query = "DELETE FROM discussion";
        Statement statement = con.createStatement();
        statement.execute(query);
        System.out.println("[DB_Manager] Le contenu de la table est supprimé");

    }

    public static ArrayList<Message> showHistory() throws SQLException{
    	//TODO poffiner en fct de la convo
        String query = "SELECT * FROM discussion ";

        Statement statement = con.createStatement();

        ResultSet result = statement.executeQuery(query);
        ArrayList<Message> list = new ArrayList<>();

        // loop through the result set
        while (result.next()) {

        	//changer id expe
            Message m = new Message(result.getInt("id_dest"),01,
                    result.getString("date"),
                    result.getString("message"),Message.TypeMessage.MESSAGE_CONV);
            list.add(m);
        }
        System.out.println(list);
        return list;
    }

   

    public static void main(String[] args) throws SQLException {
		connectionDB();
		creer_tables_DB();
		Message msg = new Message(01,02, "test", Message.TypeMessage.MESSAGE_CONV);
		insert_message_db(msg);
        showHistory();
        delethistory();
        showHistory();
    }
    
    
}