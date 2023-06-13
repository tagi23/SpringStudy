package org.example;


import org.assertj.core.util.Streams;
import org.example.calculate.positiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/*요구사항
        • 간단한 사칙연산을 할 수 있다.
        • 양수로만 계산할 수 있다.
        • 나눗셈에서 0을 나누는 경우 IllegalArgument 예외를 발생시킨다.
        • MVC패턴(Model-View-Controller) 기반으로 구현한다.*/
public class CalculatorTest {

    // 1+2 ---Calculator에게 전달하면서 작업을 위임
//    //  3
//    @DisplayName("덧셈 연산을 수행")
//    @Test
//    void additonTest() {
//        int result = Caculator.calculator(1,"+",2);
//        assertThat(result).isEqualTo(3);
//    }
//
//    @DisplayName("뺼셈 연산을 수행")
//    @Test
//    void suptractionTest(){
//        int result = Caculator.calculator(1,"-",2);
//        assertThat(result).isEqualTo(-1);
//    }
    @DisplayName("사칙연산을 수행한다.")
    @ParameterizedTest //파라미터를 받고 test
    @MethodSource("formulaAndResult")
    void calculaterTest(int operand1 , String operation , int operand2 , int result){
        int calculatorResult = Caculator.calculator(new positiveNumber(operand1),operation,new positiveNumber(operand2));
        assertThat(calculatorResult).isEqualTo(result);
    }

    private static Stream<Arguments> formulaAndResult(){
        return Stream.of(
                arguments(1,"+",2,3),
                arguments(1,"-",2,-1),
                arguments(4,"*",2,8),
                arguments(4,"/",2,2)
        );
    }

}
