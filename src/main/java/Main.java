import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(400.0);
        primaryStage.setMinWidth(600.0);
//        primaryStage.setWidth(600);
//        primaryStage.setHeight(400.0);
        primaryStage.setTitle("GPS Agro Tracker");
        primaryStage.show();

//        primaryStage.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> System.out.println("Width: " + newSceneWidth));
//        primaryStage.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> System.out.println("Height: " + newSceneHeight));

    }
    public static void main(String[] args) {
        launch(args);
    }
}
