package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
    public void executeUpdate(User user , String sql , PreparedStatementSetter pss) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pss.setter(pstmt);
            pstmt.executeUpdate(); //업뎃
        }finally {     //사용후 자원을 해제해야함
            if(pstmt != null){
                pstmt.close();
            }
            if(con !=null){
                con.close();
            }
        }
    }

    public Object executeQuery(String sql , PreparedStatementSetter pss ,RowMapper rowMapper) throws SQLException {  //id 조회
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pss.setter(pstmt);

            rs = pstmt.executeQuery();  //찾기

            Object obj = null;
            if(rs.next()){   //결과값이 있다면
                return rowMapper.map(rs);
            }
            return obj; //정보 반환해주기
        }finally {  //자원해제
            if (rs !=null){
                rs.close();
            }
            if(pstmt != null){
                pstmt.close();
            }
            if(con !=null){
                con.close();
            }
        }
    }
}
