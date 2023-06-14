package org.example;
// Tdd 방식으로 설계
//객체지향 설계 및 구현 방식

/*학점계산기 구현
        • 요구사항
        • 평균학점 계산 방법 = (학점수×교과목 평점)의 합계/수강신청 총학점 수
        • MVC패턴(Model-View-Controller) 기반으로 구현한다.
        • 일급 컬렉션 사용 */

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//1 . 도메인을 구성하는 객체를 고민 : 학점계산기 도메인 : 이수한 과목: 객체지향프로그래밍 , 자료구조 , 영어회화 , 학점 계산기
//2 . 객체들 간의 관계를 고민 : ex : 이수한 과목은 인스턴스 변수를 가지면서 계산하면 됨
//3 . 정적인 타입으로 추상화해서 도메인 모델링 ex: 이수한 과목 : 객체지향프로그래밍 , 자료구조 , 영어회화 --> 과목(코스)클래스
//4 . 협력을 설계 : 이수한 과목을 전달하여 평균학점 계산요청 ----> 학점 계산기  -----> (학점수×교과목 평점)의 합계   ----->과목(코스) 일급 컬렉션
//                                                                    ------> 수강신청 총학점 수        ------>과목(코스) 일급 컬렉션

public class GradeCalculatorTest {
    //5 . 객체들을 포관하는 타입에 적절한 책임을 할당
    @DisplayName("학점 평균을 계산")
    @Test
    void calculateGradeTest() {
        List<Course> courses = List.of(new Course("OOP",3,"A+"),
                new Course("자료구조" , 3 , "A+"));
        GradeCalculator gradeCalculator = new GradeCalculator(courses);  //학점 과목 전달
        double gradeResult = gradeCalculator.calculateGrade();    //전달받은 이수한 과목을 성적 계산

        assertThat(gradeResult).isEqualTo(4.5);
    }
}
