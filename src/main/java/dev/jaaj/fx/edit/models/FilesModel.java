package dev.jaaj.fx.edit.models;

import dev.jaaj.fx.edit.util.file.FileProperty;
import dev.jaaj.fx.edit.util.file.FilesWatchService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class FilesModel {
    private ObservableList<FileProperty> openedFiles = FXCollections.observableArrayList();
    private FilesWatchService filesWatchService;

    public FilesModel() throws IOException {
        filesWatchService = new FilesWatchService();
    }
}
