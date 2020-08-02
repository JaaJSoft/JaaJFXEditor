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

import java.io.File;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        HashMap<File, String> fileStringHashMap = new HashMap<>();
        File file = new File("D:/AH.txt");
        fileStringHashMap.put(file, "JAAJ");
        File file2 = new File("D:/AH.txt");
        String s = fileStringHashMap.get(file2);
        System.out.println(s);
    }
}
