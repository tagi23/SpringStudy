package org.example;

public  class PasswordValidator {

    public static final String WRONG_PASSWORD_LENGTH_PASSWORD = "비밀번호는 최소8자 이상 12자 이하여야 한다.";

    public static void validate(String password) {
        int length = password.length();
        if(length<8 || length>12){
            throw new IllegalArgumentException(WRONG_PASSWORD_LENGTH_PASSWORD);
        }
    }
}
