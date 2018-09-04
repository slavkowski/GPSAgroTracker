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


import java.io.IOException;
import java.net.URL;


import java.text.SimpleDateFormat;
import java.util.*;

public class Controller implements Initializable {
    int initialWidth = 600;
    int initialHeight = 296;
    WindowDimension windowDimension = new WindowDimension(initialWidth, initialHeight);


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
        System.out.println(windowDimension.getWindowWidth() + " " + windowDimension.getWindowHeight());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        gpMap.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> resizeMapCanvas(windowDimension.getWindowWidth(newSceneWidth.intValue()), windowDimension.getWindowHeight()));
        gpMap.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> resizeMapCanvas(windowDimension.getWindowWidth(), windowDimension.getWindowHeight(newSceneHeight.intValue())));

    }

    public void resizeMapCanvas(int newWidth, int newHeight) {
        GraphicsContext gc;
        gc = mapCanvas.getGraphicsContext2D();
//        System.out.println(newWidth + " " + newHeight);
        mapCanvas.setWidth(newWidth);
        mapCanvas.setHeight(newHeight);
        gc.setFill(Color.GREY);
        gc.fillRect(0, 0, newWidth, newHeight);

        gc.strokeOval(newWidth / 2 - 1, newHeight / 2 - 1, 2, 2);

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

    public void drawLine() {
        GraphicsContext gc;
        gc = mapCanvas.getGraphicsContext2D();

    }

    @FXML
    public void readSerialGps(ActionEvent actionEvent) {
        GpsSerialConnection gpsSerialConnection = new GpsSerialConnection();
        GpsSerialConnection rxtx = new GpsSerialConnection();

        try {
            rxtx.connect("COM5");

            //Define a working thread to read RxTx and display in the console
            Thread readData = new Thread(new Runnable() {
                @Override
                public void run() {
                    String data;
                    while(true) {
                        if (Thread.interrupted()) break;
                        data = rxtx.read();
                        if (data != null) {
                            System.out.println(data);
                        } else {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                break;
                            }
                        }
                    }
                }
            });
            readData.start();

            //Define a input thread to get input from the user and send to RxTx
            Thread userInput = new Thread(new Runnable() {
                @Override
                public void run() {
                    int c = 0;
                    byte[] buf = new byte[1204];
                    try {
                        while ( ( c = System.in.read(buf)) > -1 )
                        {
                            if (Thread.interrupted()) break;
                            //"---" is a string to terminate the program
                            if (c > 0) {
                                if (c==5 && buf[0]=='-'
                                        && buf[1]=='-' && buf[2]=='-') {
                                    break;
                                }
                                //"+++" is a special string sent to RxTx
                                if (c==5 && buf[0]=='+'
                                        && buf[1]=='+' && buf[2]=='+') {
                                    c = 3;
                                }
                                rxtx.write(Arrays.copyOf(buf, c));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            userInput.start();

            //Normally the program is terminated by the user
            //so we wait for the input thread to finish
            userInput.join();
            readData.interrupt();
            readData.join();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //close RxTx to release all resources
            rxtx.close();
            System.out.println("Finished!");
        }
    }
}

