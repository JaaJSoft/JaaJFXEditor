package dev.jaaj.edit.util.file;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileProperty {
    private File file;
    private StringProperty fileContent = new SimpleStringProperty();

    public FileProperty(File file) throws NoSuchFileException {
        this.file = file;
        if (!file.exists()) {
            throw new NoSuchFileException(file.getAbsolutePath());
        }
        fileContent.setValue(readAll(file));
    }

    private String readAll(File file) {
        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            return "";
        }
    }

    public FileProperty() {
    }


    public String getFileContent() {
        return fileContent.get();
    }

    public StringProperty fileContentProperty() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent.set(fileContent);
    }

    public File getFile() {
        return file;
    }
}
