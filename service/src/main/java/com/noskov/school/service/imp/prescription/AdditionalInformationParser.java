package com.noskov.school.service.imp.prescription;

import java.util.List;
import java.util.StringJoiner;

public class AdditionalInformationParser {

    public static List<String> parseCommonAdditionalInformation(PrescriptionScratch scratch, List<String> list) throws Exception {
        int lastIndex = -1;
        for (String s : list) {
            try{
                Integer.parseInt(s);
                lastIndex = list.indexOf(s);
                break;
            } catch (NumberFormatException e){

            }
        }
        if (lastIndex != -1){
            StringJoiner additionalInformation = new StringJoiner(" "," ","");
            list.stream().limit(lastIndex).forEach(additionalInformation::add);
            scratch.getTimePattern().addAdditionalInformation(additionalInformation.toString());
            list.stream().limit(lastIndex).forEach(list::remove);
            return list;
        } else throw new Exception("not found quantity after additional information");
    }
}
