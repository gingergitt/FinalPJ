package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayHistoryDB {
	private static PayHistoryDB instance = new PayHistoryDB();

	public static PayHistoryDB getInstance() {
		return instance;
	}

	public PayHistoryDB() {
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

	public String HistroyDB(String id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

			// 가장 최근의 결제 기록만 가져오도록 한다. (시퀀스순서대로 -> 값이 큰 것이 가장 최근값 )

			sql = "SELECT * FROM (SELECT PAYMENTNO, PRODUCT.PRODUCTNAME, PAYMENT.USERID,PRODUCT.PRODUCTPRICE FROM PAYMENT,"
					+ " PRODUCT WHERE PAYMENT.PRODUCTNO = PRODUCT.PRODUCTNO "
					+ "AND PAYMENT.USERID = ? ORDER BY PAYMENTNO DESC) WHERE rownum=1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int paymentno = Integer.parseInt(rs.getString("paymentno"));
				String productname = rs.getString("productname");
				String productprice = rs.getString("productprice");

				return "SS";

			} else {
				return "DD";
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
			return "DDD";
		}
	}


// SELECT * FROM (SELECT PAYMENTNO, PRODUCT.PRODUCTNAME, PAYMENT.USERID, PRODUCT.PRODUCTPRICE FROM PAYMENT, 
//PRODUCT WHERE PAYMENT.PRODUCTNO = PRODUCT.PRODUCTNO AND PAYMENT.USERID = 'admin' ORDER BY PAYMENTNO DESC) WHERE rownum=1;