package com.example.firstfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.fazecast.jSerialComm.*;

public class MainController implements Initializable {
    @FXML
    public Label nema1SliderVal;
    public TextField valChangeFieldId;
    public Slider nema2Slider;
    public Slider servo1Slider;
    public Slider servo2Slider;

    int value = 1;

    @FXML
    private Slider nema1Slider;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane rootAncorPane;

    @FXML
    void onClickHomeBtn(MouseEvent event) {
        borderPane.setCenter(rootAncorPane);
    }

    @FXML
    void onClickPageBtn1(MouseEvent event) {
        loadPage("page1");
    }

    @FXML
    void onClickPageBtn2(MouseEvent event) {
        loadPage("page1");

    }

    private void loadPage(String page){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        borderPane.setCenter(root);
    }

    public void nema1Minus(ActionEvent actionEvent) {
        int val = (int) nema1Slider.getValue();
        val -= value;
        nema1Slider.setValue(val);
    }

    public void nema1Plus(ActionEvent actionEvent) {
        int val = (int) nema1Slider.getValue();
        val += value;
        nema1Slider.setValue(val);
    }

    public SerialPort serialPort;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        serialPort = SerialPort.getCommPort("COM3");
        serialPort.setBaudRate(9600);
//        valChangeFieldId.setText(String.valueOf(value));

    }


    public void btnRun(ActionEvent actionEvent) {

        // yha pr map krna hoga servos ka value ko to make that value fit in a byte - or do somthing else
        int nema1 = (int) nema1Slider.getValue();
        String s = String.valueOf(nema1);
        serialPort.writeBytes(s.getBytes(), 1);
        System.out.println("send - " + s);
    }

    public void valChangeField(ActionEvent actionEvent) {
        value = Integer.parseInt(valChangeFieldId.getText());

    }

    public void nema2Plus(ActionEvent actionEvent) {
        int val = (int) nema2Slider.getValue();
        val += value;
        nema2Slider.setValue(val);
    }

    public void nema2Minus(ActionEvent actionEvent) {
        int val = (int) nema2Slider.getValue();
        val -= value;
        nema2Slider.setValue(val);
    }

    public void servo1Minus(ActionEvent actionEvent) {
        int val = (int) servo1Slider.getValue();
        val -= value;
        servo1Slider.setValue(val);
    }

    public void servo1Plus(ActionEvent actionEvent) {
        int val = (int) servo1Slider.getValue();
        val += value;
        servo1Slider.setValue(val);
    }


    public void servo2Minus(ActionEvent actionEvent) {
        int val = (int) servo2Slider.getValue();
        val -= value;
        servo2Slider.setValue(val);
    }

    public void servo2Plus(ActionEvent actionEvent) {
        int val = (int) servo2Slider.getValue();
        val += value;
        servo2Slider.setValue(val);
    }

    long myMap(int x, int in_min, int in_max, int out_min, int out_max)
    {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}