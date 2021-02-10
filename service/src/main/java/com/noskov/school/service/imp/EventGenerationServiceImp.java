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
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
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
            addEventsForWeek(date, generatedEvents, therapyType, patient, periodsQuantity, quantityInPeriod, periodTimePeriod, timePatternTimePeriod, additionalInformation, timeList);
        } else if(timePatternTimePeriod == TimePeriods.DAY) {
            addEventsForDay(date, generatedEvents, therapyType, patient, periodsQuantity, quantityInPeriod, periodTimePeriod, timeList);
        } else if(timePatternTimePeriod == TimePeriods.Month){
            addEventsForMonth(date, generatedEvents, therapyType, patient, periodsQuantity, quantityInPeriod, periodTimePeriod, timeList);
        } else throw new RuntimeException("eventGeneration exception");

        generatedEvents.forEach(event -> eventDAO.add(event));
    }

    private void addEventsForMonth(LocalDate date, List<EventPO> generatedEvents, ProcedureAndMedicinePO therapyType, PatientPO patient, int periodsQuantity, int quantityInPeriod, TimePeriods periodTimePeriod, List<LocalTime> timeList) {
        for (int i = 0; i < periodsQuantity; i++) {
            addEventsByQuantity(date, generatedEvents, therapyType, patient, quantityInPeriod, periodTimePeriod, timeList);
            date = date.plusMonths(1);
        }
    }

    private void addEventsForDay(LocalDate date, List<EventPO> generatedEvents, ProcedureAndMedicinePO therapyType, PatientPO patient, int periodsQuantity, int quantityInPeriod, TimePeriods periodTimePeriod, List<LocalTime> timeList) {
        for (int i = 0; i < periodsQuantity; i++) {
            for (int k = 0; k < periodTimePeriod.getDaysInPeriod(); k++) {
                LocalDate dayDate = date.plus(1, ChronoUnit.DAYS);
                date = date.plus(1, ChronoUnit.DAYS);
                for (int j = 0; j < quantityInPeriod; j++) {
                    if (timeList.isEmpty()) {
                        LocalTime time = LocalTime.of(9 + j * 3, 0);
                        LocalDateTime dateTime = LocalDateTime.of(dayDate, time);
                        EventPO event = new EventPO(patient, dateTime, EventStatus.PLANNED, therapyType);
                        generatedEvents.add(event);
                    } else {
                        if (timeList.size() == quantityInPeriod) {
                            addEventsFromTimeList(generatedEvents, timeList, patient, dayDate, therapyType);
                            j += quantityInPeriod;
                        }
                    }
                }
            }
            //date = date.plus(1, ChronoUnit.valueOf(periodTimePeriod.toString().toUpperCase() + "S"));
        }
    }

    private void addEventsForWeek(LocalDate date, List<EventPO> generatedEvents, ProcedureAndMedicinePO therapyType, PatientPO patient, int periodsQuantity, int quantityInPeriod, TimePeriods periodTimePeriod, TimePeriods timePatternTimePeriod, String additionalInformation, List<LocalTime> timeList) {
        Set<String> weekDaysSet = WeekDayParser.parseWeekDays(additionalInformation);
        for (int i = 0; i < periodsQuantity; i++) {
            int nSteps = periodTimePeriod.getDaysInPeriod()/ timePatternTimePeriod.getDaysInPeriod();
            for (int k = 0; k < nSteps; k++) {
                if (weekDaysSet.isEmpty()) {
                    addEventsByQuantity(date, generatedEvents, therapyType, patient, quantityInPeriod, timePatternTimePeriod, timeList);
                    date = date.plus(1, ChronoUnit.valueOf(timePatternTimePeriod.toString().toUpperCase() + "S"));
                } else {
                    addEventsByWeekDaysSet(date, generatedEvents, therapyType, patient, timeList, weekDaysSet);
                    date = date.plus(1, ChronoUnit.valueOf(timePatternTimePeriod.toString().toUpperCase() + "S"));
                }
            }
        }
    }

    private void addEventsByWeekDaysSet(LocalDate date, List<EventPO> generatedEvents, ProcedureAndMedicinePO therapyType, PatientPO patient, List<LocalTime> timeList, Set<String> weekDaysSet) {
        for (String dayOfWeek : weekDaysSet) {
            LocalDate weekDate = date.with(TemporalAdjusters.next(DayOfWeek.valueOf(dayOfWeek)));
            if(timeList.isEmpty()){
                addSingleEventWithDefaultTime(generatedEvents, therapyType, patient, weekDate);
            } else {
                addEventsFromTimeList(generatedEvents,timeList,patient,weekDate,therapyType);
            }
        }
    }

    private void addEventsByQuantity(LocalDate date, List<EventPO> generatedEvents, ProcedureAndMedicinePO therapyType, PatientPO patient, int quantityInPeriod, TimePeriods timePatternTimePeriod, List<LocalTime> timeList) {
        for (int j = 0; j < quantityInPeriod; j++) {
            int daysToAdd = timePatternTimePeriod.getDaysInPeriod()/quantityInPeriod;
            LocalDate nextDate = date.plus(daysToAdd, ChronoUnit.DAYS);
            date = date.plus(daysToAdd, ChronoUnit.DAYS);
            if (timeList.isEmpty()) {
                addSingleEventWithDefaultTime(generatedEvents, therapyType, patient, nextDate);
            } else {
                addEventsFromTimeList(generatedEvents,timeList,patient,nextDate,therapyType);
            }
        }
    }

    private void addSingleEventWithDefaultTime(List<EventPO> generatedEvents, ProcedureAndMedicinePO therapyType, PatientPO patient, LocalDate nextDate) {
        LocalTime time = LocalTime.of(10, 0);
        EventPO event = new EventPO(patient, LocalDateTime.of(nextDate, time), EventStatus.PLANNED, therapyType);
        generatedEvents.add(event);
    }

    private void addEventsFromTimeList(List<EventPO> eventList, List<LocalTime> timeList, PatientPO patient,
                                                LocalDate date, ProcedureAndMedicinePO therapy){
        timeList.forEach(time ->{
            EventPO event = new EventPO(patient,LocalDateTime.of(date,time),EventStatus.PLANNED, therapy);
            eventList.add(event);
        });
    }
}
