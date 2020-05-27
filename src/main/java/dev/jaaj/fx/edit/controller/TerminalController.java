package dev.jaaj.fx.edit.controller;

import com.kodedu.terminalfx.TerminalBuilder;
import com.kodedu.terminalfx.TerminalTab;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TerminalController implements Initializable {
    public TabPane terminalTabPanes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TerminalBuilder terminalBuilder = new TerminalBuilder();
        TerminalTab terminal = terminalBuilder.newTerminal();

        terminalTabPanes.getTabs().add(terminal);

    }
}
