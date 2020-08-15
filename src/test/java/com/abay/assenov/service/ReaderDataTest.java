package com.abay.assenov.service;

import com.abay.assenov.service.impl.ReaderDataCSVImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReaderDataTest {

    private ReaderData readerData;
    private Path workingDir;
    String contentTest;

    @Before
    public void init() throws IOException {
        readerData = new ReaderDataCSVImpl();
        this.workingDir = Paths.get("src", "test", "resources", "read_data_test.txt");

        contentTest = "Test";
        Files.write(workingDir, contentTest.getBytes());
    }

    @Test
    public void read_file_and_chek_to_equal() {

        List<String> readTestContent = readerData.readAsListString(workingDir.toAbsolutePath().toString());

        Assert.assertEquals(contentTest, readTestContent.get(0));
    }

    @After
    public void finalize() throws IOException {
        Files.delete(this.workingDir);
    }
}