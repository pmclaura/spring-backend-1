package com.sales.market.service;

import com.sales.market.model.Employee;
import com.sales.market.model.Role;
import com.sales.market.model.RoleType;
import com.sales.market.model.User;
import com.sales.market.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleService roleService;
    @Test
    void findUsersByRol() {
        initializeRoles();
        initializeEmployees();
        userService.findUsersByRol(RoleType.SUPERVISOR.getType());
        assertEquals(3, userService.findUsersByRol(RoleType.SUPERVISOR.getType()).size());
    }

    private void initializeRoles() {
        createRole(RoleType.ADMIN.getId(), RoleType.ADMIN.getType());
        createRole(RoleType.GENERAL.getId(), RoleType.GENERAL.getType());
        createRole(RoleType.SUPERVISOR.getId(), RoleType.SUPERVISOR.getType());
    }

    private Role createRole(long id, String roleName) {
        Role role = new Role();
        role.setId(id);
        role.setName(roleName);
        roleService.save(role);
        return role;
    }

    private void initializeEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            createEmployee("Edson", "Terceros", "edsonariel@gmail.com", false, 3L);
            createEmployee("Ariel", "Terceros", "ariel@gmail.com", false, 3L);
            createEmployee("System", "", "edson@gmail.com", true, 3L);
        }
    }

    private void createEmployee(String firstName, String lastName, String email, boolean system, Long idRole) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employeeRepository.save(employee);
        createUser(email, employee, system, idRole);
    }

    private void createUser(String email, Employee employee, boolean system, Long idRole) {
        User user = new User();
        Role role = new Role();
        HashSet<Role> roles = new HashSet<>();

        user.setEmail(email);
        user.setEnabled(true);
        user.setSystem(system);
        user.setPassword("$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.");
        user.setEmployee(employee);

        role.setId(idRole);
        roles.add(role);
        user.setRoles(roles);
        userService.save(user);
    }

}