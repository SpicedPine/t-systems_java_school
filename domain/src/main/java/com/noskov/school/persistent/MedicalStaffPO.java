package com.noskov.school.persistent;

import com.noskov.school.enums.StaffPost;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "STAFF")
public class MedicalStaffPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "POST", nullable = false)
    private StaffPost post;

    @ManyToMany
    @JoinTable(name = "PATIENT_MEDICAL_STAFF",
            joinColumns = @JoinColumn(name = "MEDICAL_STAFF_ID"),
            inverseJoinColumns = @JoinColumn(name = "PATIENT_ID"))
    private Set<PatientPO> patients = new HashSet<>();
}
