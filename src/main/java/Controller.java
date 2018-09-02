import gnu.io.CommPortIdentifier;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;



import java.net.URL;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {



    private Enumeration ports = null;
    private HashMap portMap = new HashMap();



    @FXML
    Canvas mapCanvas;
    @FXML
    GridPane gpMap;
    @FXML
    GridPane gp;
    @FXML
    Tab tbMap;
    @FXML
    ChoiceBox cb;
    @FXML
    ComboBox combobox;
    @FXML
    Button btn1;

    @FXML
    public void getSize(ActionEvent actionEvent) {


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int initialWidth = 600;
        int initialHeight = 296;

        WindowDimension windowDimension = new WindowDimension(initialWidth, initialHeight);


//        gpMap.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> System.out.println("Width: " + newSceneWidth));
//        gpMap.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> System.out.println("Height: " + newSceneHeight));

        gpMap.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> resizeMapCanvas(windowDimension.getWindowWidth(newSceneWidth.intValue()), windowDimension.getWindowHeight()));
        gpMap.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> resizeMapCanvas(windowDimension.getWindowWidth(), windowDimension.getWindowHeight(newSceneHeight.intValue())));





    }

    public void resizeMapCanvas(int newWidth, int newHeight){
        GraphicsContext gc;
        gc = mapCanvas.getGraphicsContext2D();
        mapCanvas.setWidth(newWidth);
        mapCanvas.setHeight(newHeight);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, newWidth, newHeight);
    }

    public void searchForPorts() {
        ports = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements()) {
            CommPortIdentifier curPort = (CommPortIdentifier) ports.nextElement();
            //get only serial ports
            if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println(curPort.getName());
                portMap.put(curPort.getName(), curPort);
                cb.setValue(curPort.getName());
                combobox.setValue(curPort.getName());
            }
        }
//        logText = "Choose port and push CONNECT";
//        window.txtLog.setForeground(java.awt.Color.blue);
//        window.txtLog.append(logText + "\n");
    }

//    public void getSize(javafx.event.ActionEvent actionEvent) {
//    }
}
