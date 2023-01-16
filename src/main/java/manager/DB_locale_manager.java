package manager;

import model.Message;
import model.User;
import view.ConnexionWindow;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;

public class DB_locale_manager {

    //
    // CONFIGURATION
    //

    public static Connection con;
    //TODO modifier moi par getmoi de connexion window
    static User moi = new User(777,"bidon_moi","mdp","127.0.0.1",6000);
    
    //static User moi;
    
    public static User creer_moi() throws UnknownHostException {
    	InetAddress myip = InetAddress.getLocalHost();
    	String myips = myip.toString();
    	return moi = new User(ConnexionWindow.getId(),"","",myips,6000 );
    }
    
    public static User getMoi() {
		return moi;
	}

    public static void setMoi(User moi) {
		DB_locale_manager.moi = moi;
	}

	public DB_locale_manager() throws SQLException {

        //CONNEXION
        try {
            Class.forName("org.sqlite.JDBC");
            // TODO recuperer le path des utilisateurs qd ils se connectent a l'application pour le mettre a la place de testDB
            //TODO prends en arg l'url du path du fichier a creer localement
            con = DriverManager.getConnection("jdbc:sqlite:testDB"); // //localhost/test"
            System.out.println("[DB_Manager] Connection a la bdd ok");

        } catch (ClassNotFoundException e) {
            System.out.println("[DB_Manager] Problème connexion à la BDD");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("[DB_Manager] Problème connexion à la BDD");
            throw new RuntimeException(e);
        }

        //INITIALISATION
        drop_table("annuaire");
        drop_table("utilisateur");
        drop_table("discussion");


        creer_tables_DB();

        delete_entire_content("utilisateur");
        User user1 = new User(111, "toto", "motdepasse", "143.112.212.233", 3348);
        User user2 = new User(222, "pierre", "motdepass24e", "143.112.212.233", 3358);
        User user3 = new User(333, "jack", "motdepass24e", "143.112.212.233", 3379);
        User user4 = new User(777,"bidon_moi","mdp","127.0.0.1",6000);
        add_utlisateur_db(user1);
        add_utlisateur_db(user2);
        add_utlisateur_db(user3);
        add_utlisateur_db(user4);

        delete_entire_content("discussion");
        Message msg = new Message(moi, "hey ca va ?", Message.TypeMessage.MESSAGE_CONV);
        insert_message_db(msg,0);
        Message msg2 = new Message(user2, "ca va trql", Message.TypeMessage.MESSAGE_CONV);
        insert_message_db(msg2,1);
        Message msg3 = new Message(user3, "je suis user3", Message.TypeMessage.MESSAGE_CONV);
        insert_message_db(msg3,1);
        Message msg4 = new Message(moi, "hey u3 !", Message.TypeMessage.MESSAGE_CONV);
        insert_message_db(msg4,0);

        getHistory_mess();

        delete_entire_content("annuaire");
        add_user_annuaire(111);
        add_user_annuaire(222);
        add_user_annuaire(333);
        getContacts();


    }

    //
    // CREATION DES TABLES
    //

    /**
     * Creation des tables utilsateur, discussion et annuaire
     */
    public static void creer_tables_DB() {

        String sql_utilisateur = "CREATE TABLE IF NOT EXISTS utilisateur (\n"
                + "	cle integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	id integer,\n"
                + "	pseudo varchar(20),\n"
                + "	mdp varchar(20),\n"
                + "	ip_adr varchar(20),\n"
                + "	port_nb integer,\n"
                + "	connecte integer,\n" //1 si connecte 0 si deconnecte
                + "	UNIQUE (ID)\n"
                + ");";

        //1 table par utilisateur
        String sql_discussion = "CREATE TABLE IF NOT EXISTS discussion (\n"
                + "	id_user integer,\n"
                + "	date text,\n"
                + "	message text,\n"
                + " recu text ,\n" //1 si recu 0 si envoye
                + "   FOREIGN KEY (id_user) REFERENCES utilisateur(id),"
                + "	PRIMARY KEY (id_user,date)\n"
                + ");";

        String sql_annuaire = "CREATE TABLE IF NOT EXISTS annuaire (\n"
                + "	id_ami integer PRIMARY KEY,\n"
                + "   FOREIGN KEY (id_ami) REFERENCES utilisateur(id)"
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
    // TABLE discussion
    //

    /**
     * Enregistre un message dans le tableau discussion
     * @param msg le message à enregistrer
     */
    public static void insert_message_db(Message msg, int recu) {

        String sql = "INSERT INTO discussion VALUES(?,?,?,?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, msg.getSender().getId());
            pstmt.setString(2, msg.getDate());
            pstmt.setString(3, msg.getContenu());
            pstmt.setInt(4,recu);
            pstmt.executeUpdate();
            System.out.println("[DB_Manager] Ajout message dans table ok");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Cree un table de Messages de tous les messages dans le l'utilisateur (envoyes et recu)
     * @return un tabeau de tous les messages de l'utlisateur
     * @throws SQLException
     */
    //TODO trier en fct de expe / dest
    public static ArrayList<Message> getHistory_mess() throws SQLException {

        String query = "SELECT * FROM discussion ";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(query);
        ArrayList<Message> list = new ArrayList<>();

        while (result.next()) {

            User sender;

            // si je suis le destinataire
            if (result.getInt("recu")==1){
                sender = DB_locale_manager.getUserfromId(result.getInt("id_user"));
            }
            //si je suis l'expediteur
            else {
                sender = moi;
            }
            Message m = new Message(
                    sender,
                    result.getString("date"),
                    result.getString("message"),
                    Message.TypeMessage.MESSAGE_CONV
            );
            list.add(m);
        }
        System.out.println("[DB_Manager] Liste messages finale:");
        System.out.println(list);
        return list;
    }

    public static ArrayList<Message> getHistory_mess(int id_contact) throws SQLException {

        String query = "SELECT * FROM discussion WHERE id_user = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, id_contact);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<Message> list = new ArrayList<>();

        while (resultSet.next()) {

            User sender;

            System.out.println("[DB_Manager-getHistory_mess] Valeur recu : "+resultSet.getInt("recu"));

            // si je suis le destinataire
            if (resultSet.getInt("recu")==1){
                sender = DB_locale_manager.getUserfromId(resultSet.getInt("id_user"));
            }
            //si je suis l'expediteur
            else {
                sender = moi;
                System.out.println("[DB_Manager-getHistory_mess] Je suis le sender dans gethistory mess");

            }
            System.out.println("[DB_Manager-getHistory_mess] Le sender avant ajout du messages dans la table est :"+ sender);
            Message m = new Message(
                    sender,
                    resultSet.getString("date"),
                    resultSet.getString("message"),
                    Message.TypeMessage.MESSAGE_CONV
            );
            list.add(m);
        }
        System.out.println("[DB_Manager] Liste messages finale avec "+id_contact+": ");
        System.out.println(list);
        return list;
    }

    //
    // TABLE utilisateur
    //

    /**
     * Ajout d'un utilisateur dans le tableau utilisateur
     *
     * @param utilisateur objet User
     */
    public static void add_utlisateur_db(User utilisateur) {

        String sql = "INSERT INTO utilisateur ('id','pseudo','mdp','ip_adr','port_nb','connecte') VALUES (?,?,?,?,?,?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, utilisateur.getId());
            pstmt.setString(2, utilisateur.getPseudo());
            pstmt.setString(3, utilisateur.getMdp());
            pstmt.setString(4, utilisateur.getIp());
            pstmt.setInt(5, utilisateur.getPort());
            //TODO changer connecte
            pstmt.setInt(6, 1);
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
        String sql = "SELECT * FROM utilisateur where pseudo='" + pseudo + "'";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
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


    //TODO supprimer showusers
    /**
     * @return un tabeau de tous les messages de l'utlisateur
     * @throws SQLException
     */
    public static ArrayList<User> showUsers() throws SQLException {
        //TODO poffiner en fct de la convo
        String query = "SELECT * FROM utilisateur ";

        Statement statement = con.createStatement();

        ResultSet result = statement.executeQuery(query);
        ArrayList<User> list = new ArrayList<>();

        // loop through the result set
        while (result.next()) {

            //TODO changer id expe
            User u = new User(
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

    //
    // TABLE ANNUAIRE
    //

    /**
     * Ajoute un utilisateur à partir de son id à l'annuaire
     * @param id de l'utilisateur
     */
    public static void add_user_annuaire(int id) {
        String sql = "INSERT INTO annuaire (id_ami) VALUES (?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("[DB_Manager] Utilisateur "+ id +" bien ajouté dans l'annuaire");
        } catch (SQLException e) {
            System.out.println("[DB_Manager] Probleme lors de l'ajout de l'user dans l'annuaire");
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getContacts() throws SQLException {
         String query = "SELECT * FROM annuaire ";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            ArrayList<User> list = new ArrayList<>();

            while (result.next()) {
                list.add(getUserfromId(result.getInt("id_ami")));
            }
            System.out.println("[DB_Manager] Liste contacts finale:");
            System.out.println(list);
            return list;
    }

    //
    // AUTRES
    //

    /**
     * Renvoie un User construit à partir de son id
     * @param id
     * @return l'User si trouvé ou null
     */
    public static User getUserfromId (Integer id){

        try {
            String query = "SELECT * FROM utilisateur WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String pseudo = resultSet.getString("pseudo");
                String mdp = resultSet.getString("mdp");
                String ip = resultSet.getString("ip_adr");
                int port = resultSet.getInt("port_nb");
                return new User(userId, pseudo, mdp, ip, port);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

        /* String id_string = id.toString();
        String sql = "SELECT * FROM utilisateur where id='"+id_string+"'";

        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                return new User(
                        id,
                        rs.getString("pseudo"),
                        rs.getString("mdp"),
                        rs.getString("ip_adr"),
                        rs.getInt("port_nb")
                );
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }*/

    }

    /**
     * Supprime la table passée en arg
     * @param table le nom de la table à supprimer
     * @throws SQLException
     */
    private static void drop_table(String table) throws SQLException {
        String sql = "DROP TABLE "+table;
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
        System.out.println("[DB_Manager] Table"+table+" correctement supprimee");
    }

    private static void delete_entire_content(String table) throws SQLException {
        String query = "DELETE FROM " + table;
        Statement statement = con.createStatement();
        statement.execute(query);
        System.out.println("[DB_Manager] Le contenu de la table " + table + " est supprimé");
    }

        //
    // MAIN
    //

    public static void main(String[] args) throws SQLException {
    }
}