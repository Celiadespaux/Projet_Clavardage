import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainApp extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //DB
        // DB_manager DB = new DB_manager();

        //Connexion Window
        FXMLLoader fxmlLoader = new FXMLLoader(mainApp.class.getResource("/view/ConnexionWindow.fxml"));

        primaryStage.setTitle("Projet Clavardage");
        primaryStage.setScene(new Scene(fxmlLoader.load(), 1100, 700));
        primaryStage.show();


    }

}
