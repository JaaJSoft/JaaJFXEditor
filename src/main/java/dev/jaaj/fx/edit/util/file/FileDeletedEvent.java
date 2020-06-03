package dev.jaaj.fx.edit.util.file;

import dev.jaaj.event.JaaJEvent;

import java.nio.file.Path;

public class FileDeletedEvent extends JaaJEvent<Path> {
    public FileDeletedEvent(Path source) {
        super(source);
    }
}
