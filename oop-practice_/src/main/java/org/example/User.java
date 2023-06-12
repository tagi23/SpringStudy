package org.example;

import org.example.password.RandomPasswordGenerator;

public class User {
    private String password;

    public void initPassword(PasswordGenerator passwordGenerator){
        //as - is
        //RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator(); //랜덤 패스워드

        //to-be  좀더 약결합
        String password = passwordGenerator.generatePassword();   //패스워드 생성
        /**
         * 비밀번호는 최소 8자 이상 12자 이하여야 한다.
         */
        if(password.length()>= 8 && password.length() <= 12){
            this.password = password;
        }

    }

    public String getPassword() {
        return password;
    }
}
