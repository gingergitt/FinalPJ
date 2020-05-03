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
//	String jdbcUrl = "jdbc:oracle:thin:@70.12.226.146:1521:xe";
	String jdbcUrl = "jdbc:oracle:thin:@192.168.1.162:1521:xe";


	String userId = "NY";
	String userPw = "NY";

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

			sql = "SELECT * FROM USERS WHERE USERID =? AND USERPWD =?";
		

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
//age gender phone email agree ++ address,adcategorynum
			if (rs.next()) {
		 
				int age = Integer.parseInt(rs.getString("userage"));
				String gender = rs.getString("usergender");
				String phone = rs.getString("userphone");
				String email = rs.getString("useremail");
				String address = rs.getString("useraddr");
				String agree = rs.getString("useragree");
				int adcategoryno = Integer.parseInt(rs.getString("adcategoryno"));
				
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
