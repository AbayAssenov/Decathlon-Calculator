package com.abay.assenov.service;

public interface WriterData<T> {

    void marshaling(String path, String fileName, T content, Class classs);
}
