package org.example.calculate;

public class AdditonOperator implements NewArithmeticOperator {
    @Override
    public boolean supports(String operator) {
        return "+".equals(operator);
    }

    @Override
    public int calculate(positiveNumber oprand1, positiveNumber oprand2) {
        return oprand1.toInt()+oprand2.toInt();
    }
}
