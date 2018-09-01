import com.mysql.cj.jdbc.MysqlDataSource;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;

public class Controller implements Initializable {

    GraphicsContext gc;

    @FXML
    Canvas cv;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        gc = cv.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 500, 300);

    }
}
