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


    public MedicalStaffPO(Long id, String firstName, String lastName, StaffPost post) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.post = post;
    }

    public MedicalStaffPO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public StaffPost getPost() {
        return post;
    }

    public void setPost(StaffPost post) {
        this.post = post;
    }

    public Set<PatientPO> getPatients() {
        return patients;
    }

    public void setPatients(Set<PatientPO> patients) {
        this.patients = patients;
    }

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }
}
