package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class MenuItemTest {
    @DisplayName("메뉴항목을 생성한다.")
    @Test
    void MenuItemTest() {
        assertThatCode(() -> new MenuItem("만두",6000))
                .doesNotThrowAnyException();
    }
}
