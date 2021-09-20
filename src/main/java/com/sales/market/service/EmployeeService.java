/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;


import com.sales.market.model.Employee;

public interface EmployeeService extends GenericService<Employee> {

    Employee findByEmail(String email);

    Employee findSystemEmployee();
}
