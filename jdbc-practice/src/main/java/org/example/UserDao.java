package org.example;

import java.sql.*;

public class UserDao {

    public void create(User user) throws SQLException {  //전달하는쪽에서 세팅
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO USERS VALUES(?,?,?,?)";
        jdbcTemplate.executeUpdate(user, sql, pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        });
    }
//    public User findById(String userId) throws SQLException {  //id 조회
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//            con = ConnectionManager.getConnection();
//            String sql = "SELECT userId,password,name,email FROM USERS WHERE userId = ?";
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1,userId);
//
//            rs = pstmt.executeQuery();  //찾기
//
//            User user = null;
//
//            if(rs.next()){   //결과값이 있다면
//                user = new User(    //정보 받아오기
//                        rs.getString("userId"),
//                        rs.getString("password"),
//                        rs.getString("name"),
//                        rs.getString("email")
//                );
//            }
//            return user; //정보 반환해주기
//        }finally {  //자원해제
//            if (rs !=null){
//                rs.close();
//            }
//            if(pstmt != null){
//                pstmt.close();
//            }
//            if(con !=null){
//                con.close();
//            }
//        }
//    }
    public User findById(String userId) throws SQLException {  //id 조회
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT userId,password,name,email FROM USERS WHERE userId = ?";
        return (User) jdbcTemplate.executeQuery(sql,
                pstmt -> pstmt.setString(1, userId),
                resultSet -> new User(
                        resultSet.getString("userId"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                ));
    }
}
