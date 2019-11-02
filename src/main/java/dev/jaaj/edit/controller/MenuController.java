package dev.jaaj.edit.controller;

import com.pixelduke.control.ribbon.RibbonTab;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public RibbonTab homeTab;

    public void print() {
        homeTab.setText("JAAJ");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
