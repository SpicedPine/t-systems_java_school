package com.noskov.school.service.imp;

import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.dao.api.PrescriptionDAO;
import com.noskov.school.enums.EventStatus;
import com.noskov.school.enums.TimePeriods;
import com.noskov.school.persistent.EventPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import com.noskov.school.service.api.EventGenerationService;
import com.noskov.school.service.imp.prescription.AdditionalInformationParser;
import com.noskov.school.service.imp.prescription.PrescriptionBuilder;
import com.noskov.school.service.imp.prescription.PrescriptionScratch;
import com.noskov.school.service.imp.prescription.WeekDayParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.standard.TemporalAccessorParser;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EventGenerationServiceImp implements EventGenerationService {

    @Autowired
    private PrescriptionDAO prescriptionDAO;

    @Autowired
    private EventDAO eventDAO;

    @Override
    public void generateEvents(PrescriptionPO prescription) {
        PrescriptionBuilder builder = new PrescriptionBuilder();
        PrescriptionScratch scratch = builder.parsePrescription(prescription.getFormedPrescription());
        LocalDate date = LocalDate.now();

        List<EventPO> generatedEvents= new ArrayList<>();
        ProcedureAndMedicinePO therapyType = prescription.getPrescriptionType();
        PatientPO patient = prescription.getPatient();

        int periodsQuantity = scratch.getPeriodsQuantity();
        int quantityInPeriod = scratch.getTimePatternQuantity();
        TimePeriods periodTimePeriod = scratch.getPeriodTimePeriod();
        TimePeriods timePatternTimePeriod = scratch.getTimePatternTimePeriod();
        String additionalInformation = scratch.getTimePatternAdditionalInformation();

        List<LocalTime> timeList = AdditionalInformationParser.parseDayTime(additionalInformation);

        if(timePatternTimePeriod == TimePeriods.WEEK){
            Set<String> weekDaysSet = WeekDayParser.parseWeekDays(additionalInformation);
            for (int i = 0; i < periodsQuantity; i++) {
                for (String dayOfWeek : weekDaysSet) {
                    LocalDate weekDate = date.with(TemporalAdjusters.next(DayOfWeek.valueOf(dayOfWeek)));
                    timeList.forEach(time ->{
                        EventPO event = new EventPO(patient,LocalDateTime.of(weekDate,time),EventStatus.PLANNED,therapyType);
                        generatedEvents.add(event);
                        });
                }
                date = date.plus(1, ChronoUnit.valueOf(periodTimePeriod.toString().toUpperCase()+"s"));
            }
        } else if(timePatternTimePeriod == TimePeriods.DAY){
            for (int i = 0; i < periodsQuantity; i++) {
                LocalDate dayDate = date.plus(1, ChronoUnit.DAYS);
                timeList.forEach(time ->{
                    EventPO event = new EventPO(patient,LocalDateTime.of(dayDate,time),EventStatus.PLANNED,therapyType);
                    generatedEvents.add(event);
                });
                date = date.plus(1,ChronoUnit.valueOf(periodTimePeriod.toString().toUpperCase()+"s"));
            }
        } else if(timePatternTimePeriod == TimePeriods.Month){
            for (int i = 0; i < periodsQuantity; i++) {
                LocalDate mounthDate = date.plus(1,ChronoUnit.DAYS);
                timeList.forEach(time ->{
                    EventPO event = new EventPO(patient,LocalDateTime.of(mounthDate,time),EventStatus.PLANNED,therapyType);
                    generatedEvents.add(event);
                });
                date = date.plus(1,ChronoUnit.MONTHS);
            }
        } else throw new RuntimeException("eventGeneration exception");

        generatedEvents.forEach(event -> eventDAO.add(event));
    }
}
