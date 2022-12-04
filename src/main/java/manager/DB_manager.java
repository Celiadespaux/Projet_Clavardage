package manager;

import java.sql.*;
import java.io.*;

public class DB_manager {

    

    public void ConnectionDB(){

        //Chargement pilote
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //Class.forName("java.sql.Driver");
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("[DB_Manager] Problème chargement pilote");
        }

        //Connexion à la BDD 
        String DB_URL = "";
        try {
            this.get = DriverManager.getConnection("jdbc:sqlite:");

        } catch (Exception e) {
            System.out.println("[DB_Manager] Problème connexion à la BDD");
        }
    }

    

}