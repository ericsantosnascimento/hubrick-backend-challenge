package com.hubrick.file.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader {

    private static FileReader instance;

    public static FileReader getInstance() {

        if (instance == null) {
            instance = new FileReader();
        }

        return instance;
    }

    private FileReader() {
        super();
    }

    public BufferedReader readFile(String fileName) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File(fileName));
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
