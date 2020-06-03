package dev.jaaj.fx.edit.util.file;

import dev.jaaj.event.EventInvoker;

import java.io.FileNotFoundException;
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

    private final HashMap<Path, EventInvoker<FileChangedEvent>> eventInvokersFileChanged = new HashMap<>();
    private final HashMap<Path, EventInvoker<FileDeletedEvent>> eventInvokersFileDeleted = new HashMap<>();

    private final HashMap<Path, WatchKey> directoriesWatchKeyHashMap = new HashMap<>();
    private boolean running = false;
    private static FilesWatchService instance = null;

    public static FilesWatchService getInstance() throws IOException {
        if (instance == null) {
            instance = new FilesWatchService();
        }
        return instance;
    }

    private FilesWatchService() throws IOException {
        watchService = FileSystems.getDefault().newWatchService();
        startService();
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
                            Path file = ((Path) key.watchable()).resolve(((WatchEvent<Path>) event).context());
                            if (event.kind() == ENTRY_MODIFY) {
                                EventInvoker<FileChangedEvent> eventInvoker = eventInvokersFileChanged.get(file);
                                if (eventInvoker != null) {
                                    eventInvoker.invoke(new FileChangedEvent(file));
                                }
                            } else if (event.kind() == ENTRY_DELETE) {
                                EventInvoker<FileDeletedEvent> eventInvoker = eventInvokersFileDeleted.get(file);
                                if (eventInvoker != null) {
                                    eventInvoker.invoke(new FileDeletedEvent(file));
                                    unregister(file);
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

    public void addListener(Path fileToWatch, FileDeletedListener fileDeletedListener) throws IOException {
        register(fileToWatch);
        EventInvoker<FileDeletedEvent> eventInvoker = eventInvokersFileDeleted.getOrDefault(fileToWatch, new EventInvoker<>());
        eventInvoker.addListener(fileDeletedListener);
        eventInvokersFileDeleted.put(fileToWatch, eventInvoker);
    }

    public void addListener(Path fileToWatch, FileChangedListener fileChangedListener) throws IOException {
        register(fileToWatch);
        EventInvoker<FileChangedEvent> eventInvoker = eventInvokersFileChanged.getOrDefault(fileToWatch, new EventInvoker<>());
        eventInvoker.addListener(fileChangedListener);
        eventInvokersFileChanged.put(fileToWatch, eventInvoker);
    }

    private void register(Path fileToWatch) throws IOException {
        if (!Files.exists(fileToWatch) || Files.isDirectory(fileToWatch)) {
            throw new FileNotFoundException();
        }
        if (!isMyParentRegistered(fileToWatch)) {
            Path path = fileToWatch.getParent();
            System.out.println("Register -> " + path);
            WatchKey key = path.register(watchService, ENTRY_MODIFY, ENTRY_DELETE);
            directoriesWatchKeyHashMap.put(fileToWatch.getParent(), key);
        }
    }

    public boolean removeListener(Path file, FileDeletedListener fileDeletedListener) {
        EventInvoker<FileDeletedEvent> eventInvoker = eventInvokersFileDeleted.get(file);
        if (eventInvoker != null) {
            return eventInvoker.removeListener(fileDeletedListener);
        }
        return false;
    }

    public boolean removeListener(Path file, FileChangedListener fileChangedListener) {
        EventInvoker<FileChangedEvent> eventInvoker = eventInvokersFileChanged.get(file);
        if (eventInvoker != null) {
            return eventInvoker.removeListener(fileChangedListener);
        }
        return false;
    }

    public void unregister(Path file) {
        if (eventInvokersFileChanged.remove(file) != null || eventInvokersFileDeleted.remove(file) != null) {
            if (!isAnotherFileHasTheSameParent(file)) {
                System.out.println("Unregister -> " + file.getParent());
                WatchKey key = directoriesWatchKeyHashMap.remove(file.getParent());
                key.cancel();
            }
        }
    }

    private boolean isAnotherFileHasTheSameParent(Path file) {
        return eventInvokersFileDeleted.keySet().stream()
                .filter(f -> !f.equals(file))
                .map(Path::getParent)
                .anyMatch(parent -> parent.equals(file.getParent()))
                || eventInvokersFileChanged.keySet().stream()
                .filter(f -> !f.equals(file))
                .map(Path::getParent)
                .anyMatch(parent -> parent.equals(file.getParent()));
    }

    private boolean isMyParentRegistered(Path file) {
        return directoriesWatchKeyHashMap.containsKey(file.getParent());
    }

    public boolean isRunning() {
        return running;
    }

    public void stopService() {
        this.running = false;
    }


}

