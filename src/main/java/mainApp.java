import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import manager.*;
import model.*;

public class mainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // AFFICHAGE PAGE DE CONNEXION
        FXMLLoader fxmlLoader = new FXMLLoader(mainApp.class.getResource("/ConnexionWindow.fxml"));
        primaryStage.setTitle("Projet Clavardage");
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.show();

    }

    public static void main(String[] args) throws Exception {

        // INITIALISATION DES MANAGER
        
        Network_manager networkManager = new Network_manager();
		networkManager.runservers();
		DB_locale_manager DB = new DB_locale_manager();

        // LANCEMENT APP
        launch(args);
    }

}
