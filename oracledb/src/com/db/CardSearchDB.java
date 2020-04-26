package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardSearchDB {
	private static CardSearchDB instance = new CardSearchDB();

	public static CardSearchDB getInstance() {
		return instance;
	}

	public CardSearchDB() {
	}
	
	// oracle 계정
		String jdbcUrl = "jdbc:oracle:thin:@70.12.226.146:1521:xe";

		String userId = "NY";
		String userPw = "NY";

		Connection conn = null;
		Connection conn2 = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		String sql = "";

		String returns = "aaaaaaaaaa";

		String result = "";

		public String SearchDB(String id) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

				//카드이름(회원이름), 카드회사만 가져오기 

				sql = "SELECT CARDNAME, CARDAGENCY FROM CARD WHERE USERID = id;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					String cardname =rs.getString("cardname");
					String cardagency = rs.getString("cardagency");

					return "OO";

				} else {
					return "XX";
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();

					if (pstmt != null)
						pstmt.close();

					if (conn != null)

						conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
				return "XXX";
			}
		}

	
	

