package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {
    private static ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }
    public ConnectDB() {  }

    // oracle 계정
//    String jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
    String jdbcUrl = "jdbc:oracle:thin:@192.168.0.3:8080:1521:xe";
    String userId = "pny";
    String userPw = "pny";
    

    Connection conn = null;
    Connection conn2 = null;
    
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    PreparedStatement pstmt3 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;

    String sql = "";
    String sql2 = "";
    String sql3 = "";
    String returns = "aaaaaaaaaa";
    String returns2 = "bbbbbbbbbbb";

    
    public String conncetionDB2 (String id) {
         try {
        	 Class.forName("oracle.jdbc.driver.OracleDriver");
			conn2 = DriverManager.getConnection(jdbcUrl, userId, userPw);
			sql = "SELECT USERID FROM USERS WHERE USERID = ?";
			pstmt3 = conn2.prepareStatement(sql3);
			pstmt3.setString(1, id);
			rs2 = pstmt3.executeQuery();
            if (rs.next()) {
                returns2 = "이미 사용중인 아이디입니다..";
                
            } else {
                sql2 = "INSERT INTO USERS VALUES(?)";
                pstmt3 = conn2.prepareStatement(sql2);
                pstmt3.setString(1, id);
                pstmt3.executeUpdate();
                returns2 = "사용 가능한 아이디입니다.";
            }
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returns2;
    	
    }
    public String connectionDB(String id, String pwd, String age, String gender, String phone,String email,
    		String agree) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT * FROM USERS WHERE USERID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
 
            rs = pstmt.executeQuery();
            if (rs.next()) {
                returns = "이미 존재하는 아이디 입니다.";
                
            } else {
                sql2 = "INSERT INTO USERS VALUES(?,?,?,?,?,?,?)";
                pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setString(1, id);
                pstmt2.setString(2, pwd);
                pstmt2.setString(3, age);
                pstmt2.setString(4, gender);
                pstmt2.setString(5, phone);
                pstmt2.setString(6, email);
                pstmt2.setString(7, agree);
                pstmt2.executeUpdate();
                returns = "회원 가입 성공 !";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return returns;
    }
}