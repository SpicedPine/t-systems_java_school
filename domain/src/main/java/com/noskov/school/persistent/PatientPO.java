package com.noskov.school.persistent;

import com.noskov.school.enums.PatientStatus;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name = "patients")
public class PatientPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "diagnose", nullable = false)
    private String diagnose;

    @Column(name = "social_number", nullable = false, unique = true)
    private int socialNumber;

    @Column(name = "status", nullable = false)
    private PatientStatus status;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<PrescriptionPO> prescriptionList = new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<EventPO> eventList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "patient_medical_staff",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "medical_staff_id"))
    private Set<MedicalStaffPO> physicians = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public int getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(int socialNumber) {
        this.socialNumber = socialNumber;
    }

    public PatientStatus getStatus() {
        return status;
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
    }

    public List<PrescriptionPO> getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(List<PrescriptionPO> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }

    public List<EventPO> getEventList() {
        return eventList;
    }

    public void setEventList(List<EventPO> eventList) {
        this.eventList = eventList;
    }

    public Set<MedicalStaffPO> getPhysicians() {
        return physicians;
    }

    public void setPhysicians(HashSet<MedicalStaffPO> physicians) {
        this.physicians = physicians;
    }
}

