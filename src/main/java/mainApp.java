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
        // DB
        // DB_manager DB = new DB_manager();

        // Connexion Window
        FXMLLoader fxmlLoader = new FXMLLoader(mainApp.class.getResource("/ConnexionWindow.fxml"));
        //FXMLLoader loader = new FXMLLoader(ReportMenu.this.getClass().getResource("/fxml/" + report.getClass().getCanonicalName().substring(18).replaceAll("Controller", "ConnexionWindow") + ".fxml"));
        primaryStage.setTitle("Projet Clavardage");
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.show();

    }

}
