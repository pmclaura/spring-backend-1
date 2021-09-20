package com.sales.market.model;

import com.sales.market.dto.UserDto;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User extends ModelBase<UserDto> {

    @Column(unique = true, nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 60)
    private String password;
    @Column(nullable = false)
    private boolean enabled;
    @Column(nullable = false)
    private boolean system = false;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", nullable = false, updatable = false)})
    private Set<Role> roles;

    @OneToOne(optional = false)
    private Employee employee;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }
}
