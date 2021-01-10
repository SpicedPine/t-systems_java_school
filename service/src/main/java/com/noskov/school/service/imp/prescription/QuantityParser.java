package com.noskov.school.service.imp.prescription;

import java.util.List;

public class QuantityParser {
    public static List<String> parseQuantity(PrescriptionScratch scratch, List<String> list){
        scratch.getTimePattern().setQuantity(Integer.parseInt(list.get(0)));
        list.remove(0);
        return list;
    }
}
