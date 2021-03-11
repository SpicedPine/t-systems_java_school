package com.noskov.school.persistent;

import com.noskov.school.enums.Role;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "staff_posts")
public class StaffPostPO implements GrantedAuthority {

    @Id
    private Long id;

    @Column(name = "staff_post")
    private Role role;

    @Transient
    @OneToMany(mappedBy = "post")
    private Set<MedicalStaffPO> staff;
    public StaffPostPO() {
    }

    public StaffPostPO(Long id) {
        this.id = id;
    }

    public StaffPostPO(Long id, Role role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public String toString() {
        return role.toString();
    }

    @Override
    public String getAuthority() {
        return role.getType();
    }
}
