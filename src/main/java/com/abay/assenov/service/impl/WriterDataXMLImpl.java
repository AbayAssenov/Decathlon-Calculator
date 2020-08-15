package com.abay.assenov.service.impl;

import com.abay.assenov.service.WriterDataXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.nio.file.Paths;

public class WriterDataXMLImpl<T> implements WriterDataXML<T> {

    @Override
    public void marshaling(String path, String fileName, T content, Class classs) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(classs);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Marshal the list in console
            jaxbMarshaller.marshal(content, System.out);

            //Marshal the list in file
            jaxbMarshaller.marshal(content, Paths.get(path.concat(fileName)).toFile());
        } catch (JAXBException e) {
            System.err.format("JAXBException: %s%n", e);
        }
    }
}
