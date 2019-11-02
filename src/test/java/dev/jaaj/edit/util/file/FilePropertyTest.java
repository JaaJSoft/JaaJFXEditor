package dev.jaaj.edit.util.file;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.nio.file.NoSuchFileException;

public class FilePropertyTest {
    private static FileProperty testFile;

    @BeforeClass
    public static void setUp() throws Exception {
        testFile = new FileProperty(new File(FilePropertyTest.class.getResource("AAAAA.txt").getPath()));

    }

    @Test
    public void init() throws NoSuchFileException {
        new FileProperty(new File(getClass().getResource("AAAAA.txt").getPath()));
    }

    @Test(expected = NoSuchFileException.class)
    public void initNoSuchFile() throws NoSuchFileException {
        new FileProperty(new File("notafile.txt"));
    }

    @Test
    public void testContent() {
         ;
        testFile.fileContentProperty().getValue();
    }
}