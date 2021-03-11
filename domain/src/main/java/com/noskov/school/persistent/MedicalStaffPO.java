package com.noskov.school.persistent;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "staff")
public class MedicalStaffPO implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private StaffPostPO post;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany(mappedBy = "physicians")
    /*@JoinTable(name = "PATIENT_MEDICAL_STAFF",
            joinColumns = @JoinColumn(name = "MEDICAL_STAFF_ID"),
            inverseJoinColumns = @JoinColumn(name = "PATIENT_ID"))*/
    private Set<PatientPO> patients = new HashSet<>();


    public MedicalStaffPO(Long id, String firstName, String lastName, StaffPostPO post) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.post = post;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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

    public StaffPostPO getPost() {
        return post;
    }

    public void setPost(StaffPostPO post) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(post);
    }

    @Override
    public String getUsername() {
        return getFirstName() + " " + getLastName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
