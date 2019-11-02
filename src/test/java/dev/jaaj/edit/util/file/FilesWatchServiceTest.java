package dev.jaaj.edit.util.file;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class FilesWatchServiceTest {
    private FilesWatchService filesWatchService;

    @Before
    public void setUp() throws Exception {
        filesWatchService = new FilesWatchService();
    }

    @Test
    public void register() throws IOException, InterruptedException {
        File ah = Path.of("D:/test/AH.txt").toFile();
        File ah2 = Path.of("D:/AH.txt").toFile();
        File jaaj = Path.of("D:/JAAJ.txt").toFile();
        FileChangedOnDiskListener fileChangedOnDiskListener = new FileChangedOnDiskListener() {
            @Override
            public void changed(File file) {
                System.out.println("MODIFY " + file);
            }

            @Override
            public void deleted(File file) {
                System.out.println("DELETED " + file);
            }
        };
        filesWatchService.register(ah, fileChangedOnDiskListener);
        filesWatchService.register(ah2, fileChangedOnDiskListener);
        filesWatchService.register(jaaj, fileChangedOnDiskListener);

        Thread.sleep(5000);
        filesWatchService.unregister(ah);
        filesWatchService.unregister(ah2);
        while (filesWatchService.isRunning()) ;
    }
}