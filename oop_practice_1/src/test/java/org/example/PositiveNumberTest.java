package org.example;

import org.example.calculate.positiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

public class PositiveNumberTest {

    @DisplayName("0이나 음수를 나온경우 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {0,-1})
    void createTest(int value){    //0 또는 음수일 경우 예외발생
        assertThatCode(() -> new positiveNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0또는 음수를 전달할 수 없습니다.");
    }
}
