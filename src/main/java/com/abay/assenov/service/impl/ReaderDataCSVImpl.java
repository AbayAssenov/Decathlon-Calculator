package com.abay.assenov.service.impl;

import com.abay.assenov.service.ReaderDataCSV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReaderDataCSVImpl implements ReaderDataCSV {
    @Override
    public List<String> readAsListString(String pathToFile) {

        List<String> content = new ArrayList<>();
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(pathToFile))) {
            stream.forEach(content::add);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return content;
    }
}
