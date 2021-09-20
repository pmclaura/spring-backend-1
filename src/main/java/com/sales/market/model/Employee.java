/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model;

import com.sales.market.dto.EmployeeDto;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee extends ModelBase<EmployeeDto> {
    private String firstName;
    private String lastName;

    @Column(length = 1, nullable = false)
    private boolean available = true;

    @OneToOne(mappedBy = "employee")
    private User user;

    private Byte[] image;

    // por defecto en fields es EAGER y en collecciones es LAZY  y todo valor booleano es true
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REMOVE})
    // mapped by employee es el nombre de la variable en Contrato copprespondiente a el field Employee
    private List<Contract> contracts;

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

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
