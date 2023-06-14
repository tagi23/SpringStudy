package org.example;

import java.util.List;
//일급크레딧 : 리스트형태로된 courses 정보들만 인스턴스 변수를 클래스
public class Courses { //여러개의 정보를 가진 클래스
    private final List<Course> courses;  //과목들
    //다른 변수가 있으면 안됨
    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public double multiplyCreditAndCourseGrade() {
        double multipliedCreditAndCourseGrade = 0;
        for (Course course: courses) {
            multipliedCreditAndCourseGrade += course.multiplyCreditAndCourseGrade();
        }
        return multipliedCreditAndCourseGrade;
    }

    public int calculateCompledCredit() {
        int totalCompletedCredit = courses.stream() // 이수한 과목들을 돌면서
                .mapToInt(Course::getCredit)  //학점들을 인트로 변환후
                .sum();  //차례로 더하기
        return totalCompletedCredit;
    }
}
