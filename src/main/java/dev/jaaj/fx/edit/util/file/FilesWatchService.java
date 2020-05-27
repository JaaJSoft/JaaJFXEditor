package dev.jaaj.fx.edit.util.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class FilesWatchService {
    private final WatchService watchService;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final HashMap<File, FileChangedOnDiskListener> filesToWatch = new HashMap<>();
    private final HashMap<File, WatchKey> directoriesWatchKeyHashMap = new HashMap<>();
    private boolean running = false;

    public FilesWatchService() throws IOException {
        watchService = FileSystems.getDefault().newWatchService();
    }

    private void startService() {
        running = true;
        executor.submit(() -> {
            System.out.println("Start service");
            WatchKey key;
            try {
                while (running) {
                    if ((key = watchService.take()) != null) {
                        for (WatchEvent<?> event : key.pollEvents()) {
                            //because the path returned by context is broken
                            File file = ((Path) key.watchable()).resolve(((WatchEvent<Path>) event).context()).toFile();
                            FileChangedOnDiskListener listener = filesToWatch.get(file);
                            if (listener != null) {
                                if (event.kind() == ENTRY_MODIFY) {
                                    listener.changed(file);
                                } else if (event.kind() == ENTRY_DELETE) {
                                    listener.deleted(file);
                                }
                            }
                        }
                        key.reset();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void register(File fileToWatch, FileChangedOnDiskListener fileChangedOnDiskListener) throws IOException {
        if (!fileToWatch.isFile()) {
            throw new NotFileException();
        }
        if (!isMyParentRegistered(fileToWatch)) {
            Path path = fileToWatch.getParentFile().toPath();
            System.out.println("Register -> " + path);
            WatchKey key = path.register(watchService, ENTRY_MODIFY, ENTRY_DELETE);
            directoriesWatchKeyHashMap.put(fileToWatch.getParentFile(), key);
        }
        filesToWatch.put(fileToWatch, fileChangedOnDiskListener);
    }

    public void unregister(File file) {
        if (filesToWatch.remove(file) != null) {
            if (!isAnotherFileHasTheSameParent(file)) {
                System.out.println("Unregister -> " + file.getParentFile().toPath());
                WatchKey key = directoriesWatchKeyHashMap.remove(file.getParentFile());
                key.cancel();
            }
        }
    }

    private boolean isAnotherFileHasTheSameParent(File file) {
        return filesToWatch.keySet().stream()
                .filter(f -> !f.equals(file))
                .map(File::getParentFile)
                .anyMatch(parent -> parent.equals(file.getParentFile()));
    }

    private boolean isMyParentRegistered(File file) {
        return directoriesWatchKeyHashMap.containsKey(file.getParentFile());
    }

    public boolean isRunning() {
        return running;
    }

    public void stopService() {
        this.running = false;
    }


}

