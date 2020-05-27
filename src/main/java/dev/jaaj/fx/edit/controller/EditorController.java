package dev.jaaj.fx.edit.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class EditorController implements Initializable {

    public CodeArea codeArea;
    public TabPane tabPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        codeArea.appendText("import dev.jaaj.edit");
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
    }
}
