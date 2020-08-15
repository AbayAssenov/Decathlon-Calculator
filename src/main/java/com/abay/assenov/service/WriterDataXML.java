package com.abay.assenov.service;

public interface WriterDataXML<T> {

    void marshaling(String path, String fileName, T content, Class classs);
}
