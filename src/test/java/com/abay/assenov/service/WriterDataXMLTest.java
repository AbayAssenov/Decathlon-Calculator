package com.abay.assenov.service;

import com.abay.assenov.model.Athlet;
import com.abay.assenov.model.Athlets;
import com.abay.assenov.service.impl.WriterDataXMLImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class WriterDataXMLTest {

    private Path workingDir;
    private WriterData<Athlets> writerDataXML;
    private Athlets athletsTest;
    private Athlet athletTest;

    @Before
    public void init() {
        this.workingDir = Paths.get("src", "test", "resources");

        writerDataXML = new WriterDataXMLImpl();

        athletTest = new Athlet();
        athletTest.setName("Test");
        athletTest.setDecathlon100M(100.0);
        List<Athlet> athletListTest = new ArrayList<>();
        athletListTest.add(athletTest);
        athletsTest = new Athlets(athletListTest);
    }

    @Test
    public void marshaling_data_to_file_after_test_on_exist() throws IOException, JAXBException {

        writerDataXML.marshaling(workingDir.toFile().getAbsolutePath() + File.separator, "text.xml", athletsTest, Athlets.class);

        Path file = this.workingDir.resolve("text.xml");

        Assert.assertEquals(Files.exists(file), true);

    }

    @After
    public void finalize() throws IOException {
        Files.delete(this.workingDir.resolve("text.xml"));
    }
}