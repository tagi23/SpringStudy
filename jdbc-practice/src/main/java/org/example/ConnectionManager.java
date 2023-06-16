package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String DB_URL = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_DRIVER = "org.h2.Driver";
    public static final int MAX_POOL_SIZE = 30;
    private static final DataSource ds;

    static {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(DB_DRIVER);
        hikariDataSource.setJdbcUrl(DB_URL);
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("");
        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE); //커넥션풀 적용해보기  (hikariCP 사용)
        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE);

        ds = hikariDataSource;
    }

    public static Connection getConnection() {   //getConnection 생성
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static DataSource getDataSource(){
        return ds;
    }
}
