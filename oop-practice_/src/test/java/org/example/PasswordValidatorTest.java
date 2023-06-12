package org.example;

import org.assertj.core.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

/**
 * 비밀번호는 최소 8자 이상 12자 이하여야 한다.
 * 비밀번호가 8자 미만 또는 12자 초과인 경우 IllegalArgumentException 예외를 발생시킨다.
 * 경계조건에 대해 테스트 코드를 작성해야 한다.
 */
public class PasswordValidatorTest {
    @DisplayName("비밀번호가 최소8자이상 12자 이하면 예외가 발생하지 않는다.")   //테스트코드의 의도 작성
    @Test
    void validatePasswordTest(){
        assertThatCode(()-> PasswordValidator.validate("serverwizard"))    // given
                .doesNotThrowAnyException();          //해당부분이 호출 했을때 익셉션이 발생하지 않는다.
    }
    @DisplayName("비밀번호가 8자미만 또는 12자 초과하는 경우 IllegalArgumentException 예외가 발생한다.")
    @ParameterizedTest  //의존성이 필요
    @ValueSource(strings = {"aabbccc","aabbccddeeffg"})  //경계값 테스트를 위한 변수
    void validatePasswordTest2(String Password) {
        assertThatCode(() -> PasswordValidator.validate(Password)) //경계값들을 줬을때
                .isInstanceOf(IllegalArgumentException.class)   // 익셉션이 발생하고
                .hasMessage("비밀번호는 최소8자 이상 12자 이하여야 한다.");  //익셉션 메시지가 맞는지?
    }
}
