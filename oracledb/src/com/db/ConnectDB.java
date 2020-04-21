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
	String jdbcUrl = "jdbc:oracle:thin:@70.12.226.146:1521:xe";

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
			String address, String agree, int adcategoryno, String living, String food, String fashion, 
			String culture, String beauty, String pet, String sport, String publicad, String economy) {
		//adcategoryno = 1;
	
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
		 	System.out.println(address);
		 	System.out.println(agree);
		 	System.out.println(adcategoryno);
		 
			
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
				
				sql3 = "INSERT INTO ADCATEGORY VALUES(?,?,?,?,?,?,?,?,?,?)";
				pstmt3 = conn.prepareStatement(sql3);
				pstmt3.setInt(1, adcategoryno);
				pstmt3.setString(2, living);
				pstmt3.setString(3, food);
				pstmt3.setString(4, fashion);
				pstmt3.setString(5, culture);
				pstmt3.setString(6, beauty);
				pstmt3.setString(7, pet);
				pstmt3.setString(8, sport);
				pstmt3.setString(9, publicad);
				pstmt3.setString(10, economy);
				
				pstmt3.executeUpdate();
				
				
				sql2 = "INSERT INTO USERS VALUES(?,?,?,?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, id);
				pstmt2.setString(2, pwd);
				pstmt2.setInt(3, age);
				pstmt2.setString(4, gender);
				pstmt2.setString(5, phone);
				pstmt2.setString(6, email);
				pstmt2.setString(7, address);
				pstmt2.setString(8, agree);
				pstmt2.setInt(9, adcategoryno);
				
				System.out.println(pstmt2.toString());
				pstmt2.executeUpdate();
				
				
				
				//adcategoryno++;
			}
		 
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt3 != null)
					pstmt3.close();
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