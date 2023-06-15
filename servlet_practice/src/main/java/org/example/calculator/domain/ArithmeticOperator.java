package org.example.calculator.domain;

import java.util.Arrays;

public enum ArithmeticOperator {
    //추상 메소드로 각각의 실행
    ADDITON("+"){
        @Override
        public int arithmeticcalculate(int oprend1, int oprend2) {
            return oprend1 + oprend2;
        }
    }, SUBTRACTION("-") {
        @Override
        public int arithmeticcalculate(int oprend1, int oprend2) {
            return oprend1 - oprend2;
        }
    }, MULTIPLICATION("*") {
        @Override
        public int arithmeticcalculate(int oprend1, int oprend2) {
            return oprend1 * oprend2;
        }
    }, DIVISION("/") {
        @Override
        public int arithmeticcalculate(int oprend1, int oprend2) {
            return oprend1 / oprend2;
        }
    };

    private final String operation;

    ArithmeticOperator(String operation) {
        this.operation = operation;
    }

    public abstract int arithmeticcalculate(final int oprend1, final int oprend2);  //추상 메소드

    public static int calculator(int operrand1, String operator, int operrand2) { //외부에 노출되는 public interface
        ArithmeticOperator arithmeticOperator = Arrays.stream(values())
                .filter(v->v.operation.equals(operator))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));
        return arithmeticOperator.arithmeticcalculate(operrand1,operrand2);
    }
}
