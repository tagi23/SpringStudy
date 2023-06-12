package SjStudy.SpringStudy_1.domain;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  //db가 id(value)를 자동으로 생성해주는거를 identity라고 함
    private Long id;  // 데이터를 구분하기 위한 시스템적 id


    private String name;  // 이름

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
