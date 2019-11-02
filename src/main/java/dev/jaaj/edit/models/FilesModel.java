package dev.jaaj.edit.models;

import dev.jaaj.edit.util.file.FileProperty;
import dev.jaaj.edit.util.file.FilesWatchService;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FilesModel {
    private ObservableList<FileProperty> openedFiles = FXCollections.observableArrayList();
    private FilesWatchService filesWatchService;

    public FilesModel() throws IOException {
        filesWatchService = new FilesWatchService();
    }
}
