package org.sapataria.models.employee;

import docbr.CPF;
import lombok.Getter;

@Getter
public class Manager extends Employee {
    private final EmployeeRoles role = EmployeeRoles.manager;
    private final int password;

    public Manager(CPF cpf, String name, float salary, int password){
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
        this.password = password;
    }
}
