package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;

public class ConnectDB {
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	public ConnectDB() {
	}

	// oracle ∞Ë¡§
	String jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String userId = "NY";
	String userPw = "NY";

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
	String result = "";

	
	
	
	//=============================================================================================
	public String connectionDB(String id, String pwd, int age, String gender, String phone, String email,
			String agree) {
		
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

			System.out.println("---------------------");
		 	System.out.println(id);
		 	System.out.println(pwd);
		 	System.out.println(age);
		 	System.out.println(gender);
		 	System.out.println(phone);
		 	System.out.println(email);
		 	System.out.println(agree);
		 
			
			sql = "SELECT * FROM USERS WHERE USERID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("rs.getString(\"userid\") "+rs.getString("userid"));
				if (rs.getString("userid") != null) {
					return "N";

				}

			} else {
				sql2 = "INSERT INTO USERS VALUES(?,?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, id);
				pstmt2.setString(2, pwd);
				pstmt2.setInt(3, age);
				pstmt2.setString(4, gender);
				pstmt2.setString(5, phone);
				pstmt2.setString(6, email);
				pstmt2.setString(7, agree);
			
				pstmt2.executeUpdate();
			}
		 
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt2 != null)
					pstmt2.close();
			if (pstmt != null)
					pstmt.close();
			if (conn != null)
				
					conn.close();
			} catch(SQLException e) {
				System.out.println("error");
			}
			
				 
		}
			return "Y";


	}
}