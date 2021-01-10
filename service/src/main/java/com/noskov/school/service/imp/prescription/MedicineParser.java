package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.TherapyType;
import com.noskov.school.service.imp.prescription.PrescriptionScratch;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MedicineParser {
    public static List<String> parseMedicine(PrescriptionScratch scratch, List<String> list) {
        scratch.getType().setTherapyType(TherapyType.MEDICINE);
        return list.stream()
                .filter(Predicate.not(Predicate.isEqual("Medicine")))
                .collect(Collectors.toList());
    }

    public static List<String> parseDose(PrescriptionScratch scratch, List<String> list) {
        scratch.getDose().setDose(list.get(list.size()-1));
        list.remove(list.size()-1);
        return list;
    }
}
