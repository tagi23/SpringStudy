package org.example.calculator.tobe;

import org.example.calculator.domain.positiveNumber;

public interface NewArithmeticOperator {
    boolean supports(String operator);   //operator를 지원하는지
    int calculate(positiveNumber operand1 , positiveNumber operand2);  //계산
}
