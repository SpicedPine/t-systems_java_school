package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.TherapyType;
import com.noskov.school.service.imp.prescription.PrescriptionScratch;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProcedureParser {
    public static List<String> parseProcedure(PrescriptionScratch scratch, List<String> list){
        scratch.getType().setTherapyType(TherapyType.PROCEDURE);
        return list.stream()
                .filter(Predicate.not(Predicate.isEqual("Procedure")))
                .collect(Collectors.toList());
    }
}
