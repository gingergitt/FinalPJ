package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardPayDB {

	private static CardPayDB instance = new CardPayDB();
	
	public static CardPayDB getInstance() {
		return instance;
	}
	public CardPayDB() {
		
	}
	
	//oracle
	String jdbcUrl = "jdbc:oracle:thin:@70.12.226.146:80:1521:xe";
	String userId = "NY";
	String userPw = "NY";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	String sql = "";
	String sql2 = "";
	String returns = "the_returns_are..";
	String res = "";
	
	
	//
	public String cardsDB(String cardno, String cardname,String id, String cardagency) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
			
			System.out.println("=====================");
			System.out.println(cardno);
			System.out.println(cardname);
			System.out.println(id);
			System.out.println(cardagency);
			
			sql = "SELECT * FROM CARD WHERE USERID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("rs.getString(\"cardno\") "+rs.getString("cardno"));
				if (rs.getString("cardno") != null) {
					return "NO";

				}
			}else {
				sql2 = "INSERT INTO CARD VALUES(?,?,?,?)";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1,cardno);
				pstmt2.setString(2,cardname);
				pstmt2.setString(3,id);
				pstmt2.setString(4,cardagency);
				
				pstmt2.executeUpdate();
				
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			if(pstmt2 !=null) 
				pstmt2.close();
			if(pstmt !=null) 
				pstmt.close();
			if(conn !=null)
				conn.close();
			}catch(SQLException e) {
				System.out.println("error");
			}
		}
		
		
		
		return "OK";
		
	}
	
	
	
}
