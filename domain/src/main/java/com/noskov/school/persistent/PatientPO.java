package com.noskov.school.persistent;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name = "PATIENTS")
public class PatientPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "DIAGNOSE", nullable = false)
    private String diagnose;

    @Column(name = "SOCIAL_NUMBER", nullable = false)
    private int socialNumber;

    @Column(name = "PHYSICIAN", nullable = false)
    private String physician;

    /**
     * true->treated
     * false->treating
     */
    @Column(name = "STATUS", nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "patient")
    List<PrescriptionPO> prescriptionList = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    List<EventPO> eventList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "PATIENT_MEDICAL_STAFF",
            joinColumns = @JoinColumn(name = "PATIENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "MEDICAL_STAFF_ID"))
    HashSet<MedicalStaffPO> physitians = new HashSet<>();
}

