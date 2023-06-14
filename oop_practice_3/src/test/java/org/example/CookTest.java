package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class CookTest {
    @DisplayName("요리를 만듭니다.")
    @Test
    void createTest() {
        assertThatCode(()->new Cook("만두",6000))
                .doesNotThrowAnyException();
    }
}
