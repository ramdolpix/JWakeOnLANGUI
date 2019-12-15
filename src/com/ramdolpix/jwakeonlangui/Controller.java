package com.ramdolpix.jwakeonlangui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
    @FXML TextField ipField;
    @FXML TextField macField;

    @FXML
    private void wakeUpAction(ActionEvent event){
       String ip = ipField.getText();
       String mac = macField.getText();
       WakeOnLAN.wakeUp(ip,mac);
    }

}
