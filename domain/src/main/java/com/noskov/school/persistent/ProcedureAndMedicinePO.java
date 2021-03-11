package com.noskov.school.persistent;

import com.noskov.school.enums.TherapyType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proc_and_med")
public class ProcedureAndMedicinePO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "type",nullable = false)
    private TherapyType type;

    /**
     * List of events for certain procedure or medicine
     */
    @OneToMany(mappedBy = "eventType")
    private List<EventPO> eventList = new ArrayList<>();

    /**
     * List of prescriptions for certain procedure or medicine
     */
    @OneToMany(mappedBy = "prescriptionType")
    private List<PrescriptionPO> prescriptionList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TherapyType getType() {
        return type;
    }

    public void setType(TherapyType type) {
        this.type = type;
    }

    public List<EventPO> getEventList() {
        return eventList;
    }

    public void setEventList(List<EventPO> eventList) {
        this.eventList = eventList;
    }

    public List<PrescriptionPO> getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(List<PrescriptionPO> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }
}
