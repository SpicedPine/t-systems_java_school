package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.TherapyType;
import com.noskov.school.enums.TimePeriods;
import com.noskov.school.service.api.time.TimeManagement;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class PrescriptionBuilder implements TimeManagement {
    @Override
    public String buildPrescription(PrescriptionScratch scratch) {
        StringJoiner stringPrescription = new StringJoiner(" ");
        String therapyType = scratch.getType().toString();
        String timePattern = scratch.getTimePattern().toString();
        String period = scratch.getPeriod().toString();

        stringPrescription.add(therapyType)
                .add(timePattern)
                .add(period);

        if(scratch.getType().getTherapyType().equals(TherapyType.MEDICINE)){
            stringPrescription.add(scratch.getDose().toString());
        }

        return stringPrescription.toString();
    }

    @Override
    public PrescriptionScratch parsePrescription(String prescription) throws Exception {
        PrescriptionScratch scratch = new PrescriptionScratch();
        List<String> list = Arrays.asList(prescription.split(" "));
        if (list.get(0).equalsIgnoreCase(TherapyType.PROCEDURE.toString())){
            list = ProcedureParser.parseProcedure(scratch, list);
        } else if (list.get(0).equalsIgnoreCase(TherapyType.MEDICINE.toString())){
            list = MedicineParser.parseMedicine(scratch, list);
            list = MedicineParser.parseDose(scratch, list);
        }

        list = QuantityParser.parseQuantity(scratch, list);

        if (list.get(0).equals(TimePeriods.DAY.toString()+"s")
            || list.get(0).equals(TimePeriods.DAY.toString())){
                list = TimePeriodParser.parseDay(scratch, list);
        } else if (list.get(0).equals(TimePeriods.WEEK.toString()+"s")
            || list.get(0).equals(TimePeriods.WEEK.toString())){
                list = TimePeriodParser.parseWeek(scratch, list);
                list = WeekDayParser.parseWeekDays(scratch, list);
        } else if (list.get(0).equals(TimePeriods.Month.toString()+"s")
            || list.get(0).equals(TimePeriods.Month.toString())){
                list = TimePeriodParser.parseMonth(scratch, list);
        }

        list = AdditionalInformationParser.parseCommonAdditionalInformation(scratch, list);
        list = QuantityParser.parseQuantity(scratch,list);

        if (list.get(0).equals(TimePeriods.DAY.toString()+"s")
                || list.get(0).equals(TimePeriods.DAY.toString())){
            list = TimePeriodParser.parseDay(scratch, list);
        } else if (list.get(0).equals(TimePeriods.WEEK.toString()+"s")
                || list.get(0).equals(TimePeriods.WEEK.toString())){
            list = TimePeriodParser.parseWeek(scratch, list);
        } else if (list.get(0).equals(TimePeriods.Month.toString()+"s")
                || list.get(0).equals(TimePeriods.Month.toString())){
            list = TimePeriodParser.parseMonth(scratch, list);
        }

        return scratch;
    }
}
