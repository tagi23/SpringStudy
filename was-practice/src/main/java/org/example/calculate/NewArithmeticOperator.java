package org.example.calculate;

public interface NewArithmeticOperator {
    boolean supports(String operator);   //operator를 지원하는지
    int calculate(positiveNumber oprand1 , positiveNumber oprand2);  //계산
}
