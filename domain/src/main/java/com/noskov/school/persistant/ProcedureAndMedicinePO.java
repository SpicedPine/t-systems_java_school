package com.noskov.school.persistant;

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

    /**
     * true->procedure
     * false->medicine
     */
    @Column(name = "TYPE",nullable = false)
    private boolean type;

    /**
     * List of events for certain procedure or medicine
     */
    @OneToMany(mappedBy = "eventType")
    List<EventPO> eventList = new ArrayList<>();

    /**
     * List of prescriptions for certain prescription or medicine
     */
    @OneToMany(mappedBy = "prescriptionType")
    List<PrescriptionPO> prescriptionList = new ArrayList<>();

}
