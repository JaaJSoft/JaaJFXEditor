package dev.jaaj.edit.util.file;

import javafx.beans.value.ObservableValue;

import java.io.File;

public interface FileChangedOnDiskListener {
    void changed(File file);
    void deleted(File file);
}
