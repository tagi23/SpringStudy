package org.example.calculate;

public class DivisionOperator implements NewArithmeticOperator {
    @Override
    public boolean supports(String operator) {
        return "/".equals(operator);
    }

    @Override
    public int calculate(positiveNumber oprand1, positiveNumber oprand2) {
        if(oprand2.toInt() == 0){
            throw new IllegalArgumentException("0으로는 나눌 수 없습니다.");
        }
        return oprand1.toInt()/oprand2.toInt();
    }
}
