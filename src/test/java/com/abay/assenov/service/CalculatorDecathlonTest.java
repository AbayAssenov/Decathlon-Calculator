package com.abay.assenov.service;

import com.abay.assenov.model.Athlet;
import com.abay.assenov.model.Athlets;
import com.abay.assenov.service.impl.*;
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
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.Assert.*;

public class CalculatorDecathlonTest {


    private CalculatorDecathlon calculatorDecathlon;
    private Path workingDir;
    List<String> dataTest;

    @Before
    public void init() throws IOException {

        this.workingDir = Paths.get("src", "test", "resources");

        dataTest = new ArrayList<>();
        dataTest.add("Test First;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72");
        dataTest.add("Test Shared Place;13.04;4.53;7.79;1.55;64.72;18.74;24.20;2.40;28.20;6:50.76");
        dataTest.add("Test Shared Place Duplicate;13.04;4.53;7.79;1.55;64.72;18.74;24.20;2.40;28.20;6:50.76"); // same scores
        dataTest.add("Jaan Lepp;13.75;4.84;10.12;1.50;68.44;19.18;30.85;2.80;33.88;6:22.75");
        dataTest.add("Foo Bar;13.43;4.35;8.64;1.50;66.06;19.05;24.89;2.20;33.48;6:51.01");

        Files.write(this.workingDir.resolve("test_data.csv"), dataTest, Charset.defaultCharset());

        Properties propertiesTest = new Properties();
        propertiesTest.setProperty("default_name_result_file", "test_result.xml"); //
        propertiesTest.setProperty("default_result_path", workingDir.toAbsolutePath().toString() + File.separator);

        calculatorDecathlon = new CalculatorDecathlonImpl(new ReaderDataCSVImpl(),
                new WriterDataXMLImpl<>(),
                new PropertyServiceImpl(propertiesTest),
                new MapperModeAthletImpl());
    }

    @Test
    public void execute_calculate_() throws IOException, JAXBException {
        String pathToTestData = this.workingDir.resolve("test_data.csv").toAbsolutePath().toString();
        calculatorDecathlon.execute(pathToTestData);

        List<String> content = Files.readAllLines(this.workingDir.resolve("test_result.xml"));

        JAXBContext jaxbContext = JAXBContext.newInstance(Athlets.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Athlets athlets = (Athlets) jaxbUnmarshaller.unmarshal(new StringReader(String.join(System.lineSeparator(), content)));
        Optional<Athlet> athletTestFirstPlace = athlets.getAthlets().stream().filter(f-> Objects.equals(f.getPlace(), "1")).findFirst();
        Assert.assertEquals(athletTestFirstPlace.get().getName(), "Test First");

        Optional<Athlet> athletTestSharedPlace = athlets.getAthlets().stream().filter(f-> Objects.equals(f.getPlace(), "3-4")).findAny();

        Assert.assertTrue(Objects.equals(athletTestSharedPlace.get().getName(), "Test Shared Place")
                || Objects.equals(athletTestSharedPlace.get().getName(), "Test Shared Place Duplicate"));
    }

    @After
    public void finalize() throws IOException {
        Files.delete(this.workingDir.resolve("test_data.csv"));
        Files.delete(this.workingDir.resolve("test_result.xml"));
    }
}