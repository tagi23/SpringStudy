package org.example.calculator.tobe;

import org.example.calculator.domain.positiveNumber;

public class MultiplicationOperator implements NewArithmeticOperator {
    @Override
    public boolean supports(String operator) {
        return "*".equals(operator) ;
    }

    @Override
    public int calculate(positiveNumber operand1, positiveNumber operand2) {
        return operand1.toInt() * operand2.toInt();
    }
}
