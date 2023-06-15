package org.example.calculate;

import org.example.calculate.*;

import java.util.List;

public class Caculator{
    //인터페이스를 가지는 List 생성 (각각의 구현체를 상위 인터페이스를 통해서 받는다.)
    private static final List<NewArithmeticOperator> arithmeticOperator = List.of(new AdditonOperator() , new SubtractionOperator() , new MultiplicationOperator() , new DivisionOperator());
    public static int calculator(positiveNumber operrand_1 , String operator , positiveNumber operrand_2) {
        // return ArithmeticOperator.calculator(operrand_1 , operator , operrand_2);
        return arithmeticOperator.stream()
                .filter(arithmeticOperator ->arithmeticOperator.supports(operator))  //올바른 operator를 찾는다.
                .map(arithmeticOperator -> arithmeticOperator.calculate(operrand_1,operrand_2))//찾은 구현체에게 calculate를 위임한다 (int로 받기위해 map을 해줌)
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));
    }
}
