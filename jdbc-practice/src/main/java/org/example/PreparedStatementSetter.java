package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
    void setter(PreparedStatement pstmt) throws SQLException;  //setter용 pstmt 생성
}
