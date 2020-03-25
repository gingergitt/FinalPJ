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
	String jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
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

			sql = "SELECT * FROM USERS WHERE USERID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();

			if (rs.next()) {
//					System.out.println("rs.getString(\"userid\") "+rs.getString("userid"));
				if (rs.getString("userid") != null && rs.getString("userpwd") == pwd) {
					return "YY";

				} else if(rs.getString("userid")== null){
						
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null)
					pstmt.close();
		
				if (conn != null)
				
					conn.close();
			} catch(SQLException e) {
				System.out.println("error");
			}
			
				 
		}
		return "NO ID";

	}
}
