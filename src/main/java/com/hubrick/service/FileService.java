package com.hubrick.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileService {

    private static FileService instance;

    public static FileService getInstance() {

        if (instance == null) {
            instance = new FileService();
        }

        return instance;
    }

    private FileService() {
        super();
    }

    public BufferedReader readFile(String fileName) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File(fileName));
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
