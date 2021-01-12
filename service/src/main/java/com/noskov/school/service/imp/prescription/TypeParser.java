package com.noskov.school.service.imp.prescription;

import java.util.Arrays;
import java.util.List;

public class TypeParser {

    public static String parseTherapyTypeName(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        return list.get(1);
    }
}
