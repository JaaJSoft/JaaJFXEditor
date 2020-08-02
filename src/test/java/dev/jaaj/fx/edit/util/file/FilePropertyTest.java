/*
 * Copyright 2020 JaaJSoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.jaaj.fx.edit.util.file;

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
