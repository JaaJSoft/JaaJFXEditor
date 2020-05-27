package dev.jaaj.fx.edit.util.file;

import java.io.IOException;

public class NotFileException extends IOException {
    public NotFileException() {
        super("Must be a file and not a directory");
    }
}
