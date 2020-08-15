package com.abay.assenov.runner;

import com.abay.assenov.service.CalculatorDecathlon;
import com.abay.assenov.service.impl.CalculatorDecathlonImpl;
import com.abay.assenov.service.impl.PropertyServiceImpl;
import com.abay.assenov.service.impl.ReaderDataCSVImpl;
import com.abay.assenov.service.impl.WriterDataXMLImpl;

import static com.abay.assenov.constant.Contstans.FIRST_ARGUMENT;
import static com.abay.assenov.constant.Contstans.ZERO_LENGTH;

public class RunApp {

    public static void main(String[] args) {
        if (args.length > ZERO_LENGTH) {
            String pathToFile = args[FIRST_ARGUMENT];

            CalculatorDecathlon calculatorDecathlon = new CalculatorDecathlonImpl(new ReaderDataCSVImpl(),
                    new WriterDataXMLImpl<>(),
                    new PropertyServiceImpl());

            calculatorDecathlon.execute(pathToFile);

        } else {
            throw new IllegalArgumentException("Not found path to CSV file");
        }
    }
}
