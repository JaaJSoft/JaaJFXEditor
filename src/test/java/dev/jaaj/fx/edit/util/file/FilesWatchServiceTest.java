package dev.jaaj.fx.edit.util.file;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class FilesWatchServiceTest {
    private final FilesWatchService filesWatchService =  FilesWatchService.getInstance();

    public FilesWatchServiceTest() throws IOException {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void register() throws IOException, InterruptedException {
        Path ah = Path.of("D:/test/AH.txt");
        Path ah2 = Path.of("D:/AH.txt");
        Path jaaj = Path.of("D:/JAAJ.txt");
        FileChangedListener fileChangedOnDiskListener = event -> System.out.println("CHANGED : " + event.getSource());
        FileDeletedListener fileDeletedListener = event -> System.out.println("DELETED : " + event.getSource());

        filesWatchService.addListener(ah, fileChangedOnDiskListener);
        filesWatchService.addListener(ah, fileChangedOnDiskListener);

        filesWatchService.addListener(ah2, fileChangedOnDiskListener);
        filesWatchService.addListener(jaaj, fileChangedOnDiskListener);

        Thread.sleep(5000);
        filesWatchService.unregister(ah);
        filesWatchService.unregister(ah2);

        while (filesWatchService.isRunning()) ;
    }
}
