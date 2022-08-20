package docbr;

import java.util.InputMismatchException;

public class CPF {
    private String cpf;


    public CPF(String cpf){
        boolean repeatedCPF = true;

        final int firstDigitIndex = 9, secondDigitIndex = 10;
        this.cpf = cpf;

        if (this.cpf.length() != 11){
            throw new InputMismatchException("CPF inválido.");
        }

        for (int i = 0; i < this.cpf.length(); i++) {
            if (this.cpf.charAt(i) != this.cpf.charAt(i + 1)) {
                repeatedCPF = false;
                break;
            }
        }
        if (repeatedCPF){
            throw new InputMismatchException("CPF inválido.");
        }

        if (this.cpf.contains(".") || this.cpf.contains("-")){
            this.cpf = this.cpf.replace(".", "");
        }

        if (isVDInvalid(firstDigitIndex) || isVDInvalid(secondDigitIndex)){
            throw new InputMismatchException("Verificação de CPF falhou.");
        }
    }

    private boolean isVDInvalid(int digitIndex){
        int sum = 0, num, i, weight, remainder, digitValue;
        int digitToCheck = (int) (this.cpf.charAt(digitIndex) - 48);
        weight = digitIndex == 9 ? 10 : 11;
        for (i = 0; i < digitIndex; i++){
            num = (int) this.cpf.charAt(i) - 48;
            sum = sum + (num * weight);
            weight = weight - 1;
        }
        remainder = (sum * 10 ) % 11;
        digitValue = remainder == 10 ? 0 : remainder;
        return digitValue != digitToCheck;

    }

    public String getCPF(){
        return this.cpf;
    }

    public String getFormattedCPF(){
        return String.format("%s.%s.%s-%s", this.cpf.substring(0, 3), this.cpf.substring(3, 6), this.cpf.substring(6, 9), this.cpf.substring(9, 11));
    }

}
