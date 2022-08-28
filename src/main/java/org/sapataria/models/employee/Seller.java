package org.sapataria.models.employee;

import docbr.CPF;
import lombok.Getter;

@Getter
public class Seller extends Employee {
    private final EmployeeRoles role = EmployeeRoles.sales;
    public Seller(CPF cpf, String name, float salary){
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }
}
