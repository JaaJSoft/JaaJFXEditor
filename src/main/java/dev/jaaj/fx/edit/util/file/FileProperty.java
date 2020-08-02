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

import dev.jaaj.file.watchservice.PathWatchService;
import javafx.beans.property.StringPropertyBase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileProperty extends StringPropertyBase {
    private final Path documentPath;

    public FileProperty(Path documentPath) throws IOException {
        PathWatchService pathWatchService = new PathWatchService();
        if (!Files.exists(documentPath)) {
            throw new FileNotFoundException();
        }
        this.documentPath = documentPath;
        loadFromFile();
    }

    @Override
    public Object getBean() {
        return documentPath;
    }

    @Override
    public String getName() {
        return null;
    }

    public void loadFromFile() {
        try {
            setValue(Files.readString(documentPath));
        } catch (IOException ignored) {
        }

    }
}
