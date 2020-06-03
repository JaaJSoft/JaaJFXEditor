package dev.jaaj.fx.edit.util.file;

import dev.jaaj.event.JaaJEvent;

import java.nio.file.Path;

public class FileChangedEvent extends JaaJEvent<Path> {

    public FileChangedEvent(Path source) {
        super(source);
    }
}
