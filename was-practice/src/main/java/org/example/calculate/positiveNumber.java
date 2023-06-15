package org.example.calculate;

public class positiveNumber {

    private final int value;

    public positiveNumber(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if(isNegativeNumber(value)){  //value가 음수라면
            throw new IllegalArgumentException("0또는 음수를 전달할 수 없습니다.");
        }
    }

    private boolean isNegativeNumber(int value) {
        return value <=0;
    }

    public int toInt(){
        return value;
    }
}
