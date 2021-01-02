package com.noskov.school.persistent;

import com.noskov.school.utils.TherapyType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PROC_AND_MED")
public class ProcedureAndMedicinePO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "TYPE",nullable = false)
    private TherapyType type;

    /**
     * List of events for certain procedure or medicine
     */
    @OneToMany(mappedBy = "eventType")
    List<EventPO> eventList = new ArrayList<>();

    /**
     * List of prescriptions for certain procedure or medicine
     */
    @OneToMany(mappedBy = "prescriptionType")
    List<PrescriptionPO> prescriptionList = new ArrayList<>();
}
