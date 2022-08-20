package org.sapataria.helpers;

import lombok.Getter;

import java.util.InputMismatchException;


@Getter
public class CPF{
    private final String cpf;
    private int sum = 0, num, i, weight, remainder, digitValue;


    public CPF(String cpf){
        this.cpf = cpf;
        if (this.cpf.length() != 11){
            throw new InputMismatchException("CPF inválido.");
        }
        System.out.println(firstVDCheck());

    }

    private boolean firstVDCheck(){
        int digitToCheck = (int) (this.cpf.charAt(9) - 48);
        sum = 0;
        num = 0;
        weight = 10;
        remainder = 0;
        for (i = 0; i < 9; i++){
            num = (int) (this.cpf.charAt(i) - 48);
            sum = sum + (num * weight);
            weight = weight - 1;
        }
        remainder = (sum * 10 ) % 11;
        digitValue = remainder == 10 ? 0 : remainder;
        return digitValue == digitToCheck;

    }

}
