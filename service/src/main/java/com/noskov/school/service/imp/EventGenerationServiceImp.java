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
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
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
                if (weekDaysSet.isEmpty()){
                    for (int j = 0; j < quantityInPeriod; j++) {
                        LocalDate weekDate = date.plus(timePatternTimePeriod.getDaysInPeriod() / quantityInPeriod, ChronoUnit.DAYS);
                        if (timeList.isEmpty()) {
                            LocalTime time = LocalTime.of(10, 0);
                            EventPO event = new EventPO(patient, LocalDateTime.of(weekDate, time), EventStatus.PLANNED, therapyType);
                            generatedEvents.add(event);
                        } else {
                            timeList.forEach(time -> {
                                EventPO event = new EventPO(patient, LocalDateTime.of(weekDate, time), EventStatus.PLANNED, therapyType);
                                generatedEvents.add(event);
                            });
                        }
                    }
                } else {
                    for (String dayOfWeek : weekDaysSet) {
                        LocalDate weekDate = date.with(TemporalAdjusters.next(DayOfWeek.valueOf(dayOfWeek)));
                        if(timeList.isEmpty()){
                            LocalTime time = LocalTime.of(10,0);
                            EventPO event = new EventPO(patient,LocalDateTime.of(weekDate,time),EventStatus.PLANNED,therapyType);
                            generatedEvents.add(event);
                        } else {
                            timeList.forEach(time ->{
                                EventPO event = new EventPO(patient,LocalDateTime.of(weekDate,time),EventStatus.PLANNED,therapyType);
                                generatedEvents.add(event);
                            });
                        }
                    }
                    date = date.plus(1, ChronoUnit.valueOf(periodTimePeriod.toString().toUpperCase()+"s"));
                }
            }
        } else if(timePatternTimePeriod == TimePeriods.DAY) {
            for (int i = 0; i < periodsQuantity; i++) {
                for (int j = 0; j < quantityInPeriod; j++) {
                    LocalDate dayDate = date.plus(1, ChronoUnit.DAYS);
                    if(timeList.isEmpty()){
                        LocalTime time = LocalTime.of(9+j*3,0);
                        LocalDateTime dateTime = LocalDateTime.of(dayDate,time);
                        EventPO event = new EventPO(patient,dateTime,EventStatus.PLANNED,therapyType);
                        generatedEvents.add(event);
                    } else {
                        if(timeList.size()==quantityInPeriod) {
                            timeList.forEach(time -> {
                                EventPO event = new EventPO(patient, LocalDateTime.of(dayDate, time), EventStatus.PLANNED, therapyType);
                                generatedEvents.add(event);
                            });
                            j+=quantityInPeriod;
                        }
                    }
                }
                date = date.plus(1, ChronoUnit.valueOf(periodTimePeriod.toString().toUpperCase() + "s"));
            }
        } else if(timePatternTimePeriod == TimePeriods.Month){
            for (int i = 0; i < periodsQuantity; i++) {
                for (int j = 0; j < quantityInPeriod; j++) {
                    LocalDate monthDate = date.plus(periodTimePeriod.getDaysInPeriod()/quantityInPeriod, ChronoUnit.DAYS);
                    if (timeList.isEmpty()){
                         LocalTime time = LocalTime.of(10,0);
                         EventPO event = new EventPO(patient,LocalDateTime.of(monthDate,time),EventStatus.PLANNED,therapyType);
                         generatedEvents.add(event);
                    } else {
                        timeList.forEach(time ->{
                            EventPO event = new EventPO(patient,LocalDateTime.of(monthDate,time),EventStatus.PLANNED,therapyType);
                            generatedEvents.add(event);
                        });
                    }
                }
            }
        } else throw new RuntimeException("eventGeneration exception");

        generatedEvents.forEach(event -> eventDAO.add(event));
    }

    private List<EventPO> addEventsFromList(List<EventPO> eventList, List<LocalTime> timeList, PatientPO patient,
                                   LocalDate date, ProcedureAndMedicinePO therapy){
        timeList.forEach(time ->{
            EventPO event = new EventPO(patient,LocalDateTime.of(date,time),EventStatus.PLANNED, therapy);
            eventList.add(event);
        });
        return eventList;
    }
}
