import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(400.0);
        primaryStage.setMinWidth(600.0);
        primaryStage.setTitle("GPS Agro Tracker");
        Image ico = new Image("/ico/tractor.png");
        primaryStage.getIcons().setAll(ico);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
