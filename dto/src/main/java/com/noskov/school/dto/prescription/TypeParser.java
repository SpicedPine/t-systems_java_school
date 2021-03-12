package com.noskov.school.dto.prescription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class TypeParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeParser.class);

    public static String parseTherapyTypeName(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        LOGGER.info("Parsed therapy type: {}", list.get(1));
        return list.get(1);
    }
}
