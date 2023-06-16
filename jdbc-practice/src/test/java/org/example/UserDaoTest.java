package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {
    @BeforeEach //테스트코드를 수행하기 이전에 실행해야하는 코드는 beforeEach에 작성(세팅과정)
    void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql"));  //db_schema.sql 읽어오기
        DatabasePopulatorUtils.execute(populator,ConnectionManager.getDataSource());
    }

    @Test
    void createTest() throws SQLException {
        UserDao userDao = new UserDao();  //db 작업 수행객체

        userDao.create(new User("id","password","sungjin","email")); //저장

        User user = userDao.findById("id");  //id에 해당하는 정보조회

        assertThat(user).isEqualTo(new User("id","password","sungjin","email"));  //해당하는정보가 맞는지 테스트
    }
}
