package dev.jaaj.fx.edit.controls.editor;

import dev.jaaj.fx.control.SkinFXML;
import javafx.scene.control.Control;

import java.nio.file.Path;

public class DocumentEditorSkin extends SkinFXML<DocumentEditor> {

    protected DocumentEditorSkin(DocumentEditor control) {
        super(control, Path.of(""));
    }
}
