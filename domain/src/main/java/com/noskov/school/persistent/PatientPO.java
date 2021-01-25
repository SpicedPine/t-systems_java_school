package com.noskov.school.persistent;

import com.noskov.school.enums.PatientStatus;

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

    @Column(name = "STATUS", nullable = false)
    private PatientStatus status;

    @OneToMany(mappedBy = "patient")
    private List<PrescriptionPO> prescriptionList = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    private List<EventPO> eventList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "PATIENT_MEDICAL_STAFF",
            joinColumns = @JoinColumn(name = "PATIENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "MEDICAL_STAFF_ID"))
    private Set<MedicalStaffPO> physicians = new HashSet<>();

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

    public String getPhysician() {
        return physician;
    }

    public void setPhysician(String physician) {
        this.physician = physician;
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

