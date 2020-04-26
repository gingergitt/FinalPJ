package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CardPlusDB {

	private static CardPlusDB instance = new CardPlusDB();

	public static CardPlusDB getInstance() {
		return instance;
	}

	public CardPlusDB() {
	}

	// oracle °èÁ¤
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
	
	Statement test = null;
int count=0;
	String sql = "";
	String result = "";

	public String CardPlussDB(String id) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

//			sql = "SELECT USERID, COUNT(USERID) FROM CARD WHERE USERID = USERID GROUP BY USERID HAVING COUNT(USERID) > 0";
			sql = "select count(*) as cnt from CARD where userid = id ";
		

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
//				String cardno = rs.getString("cardno");
//				String cardname = rs.getString("cardname");
//				String cardagency = rs.getString("cardagency");
				
				count++;
				System.out.println("count " + count);
				rs.getInt("cnt");
//				if(rs.getString("userid") > 0 && rs.getString("userid") < 6 ) {
//					rs.
//				}
		
				return "YY";
				

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

