package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class CouseTest {

    @DisplayName("과목(코스)을 생성한다.")
    @Test
    void createTest(){
        assertThatCode(() -> new Course("OOP",3,"A+"))
                .doesNotThrowAnyException(); //해당코스가 정상적으로 생성이 됐다면 어떠한 Exception이 발생하지 않는다.
    }
}
