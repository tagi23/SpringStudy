package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CookingTest {
    @DisplayName("메뉴에 해당하는 음식을 만든다.")
    @Test
    void cookingTest() {
        Cooking cooking = new Cooking();
        MenuItem menuItem = new MenuItem("만두",6000);
        Cook cook = cooking.makeCook(menuItem);
        assertThat(cook).isEqualTo(new Cook("만두",6000));
    }
}
