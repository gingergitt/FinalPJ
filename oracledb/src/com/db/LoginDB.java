package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDB {
	private static LoginDB instance = new LoginDB();

	public static LoginDB getInstance() {
		return instance;
	}

	public LoginDB() {
	}

	// oracle ∞Ë¡§
//	String jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String jdbcUrl = "jdbc:oracle:thin:@192.168.0.3:1521:xe";

	String userId = "PNY";
	String userPw = "PNY";

	Connection conn = null;
	Connection conn2 = null;

	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;

	String sql = "";
	String result = "";

	public String loginnDB(String id, String pwd) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

			sql = "SELECT * FROM USERS WHERE USERID = ? AND USERPWD =?";
		

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
//age gender phone email agree
			if (rs.next()) {
				int age = Integer.parseInt(rs.getString("age"));
				String gender = rs.getString("gender");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String agree = rs.getString("agree");
				
				return "YY";
				
//				if (rs.getString("userid") != null && rs.getString("userpwd") == pwd) {
//					return "YY";
//
//				} else if(rs.getString("userid")== null){
//						return "nulllll";
//				}
			} else {
				return " NNNNNN ";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null)
					rs.close();
				
				
				if (pstmt != null)
					pstmt.close();
		
				if (conn != null)
				
					conn.close();
			} catch(SQLException e) {
				System.out.println(e+"error");
			}
			
				 
		}
		return "NN";
		

	}
}
