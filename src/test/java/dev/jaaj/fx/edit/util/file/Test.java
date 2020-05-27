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
