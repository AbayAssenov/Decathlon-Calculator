package com.abay.assenov.service;

import java.util.List;

public interface MapperModel<T> {

    List<T> mapDataFromListString(List<String> content);
}
