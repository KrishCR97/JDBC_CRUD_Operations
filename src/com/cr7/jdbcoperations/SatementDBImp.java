package com.cr7.jdbcoperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author GameS
 *
 */
public class SatementDBImp {
	/**
	 * @param deptNo
	 */
	public void getDeptDetails(int deptNo) {
		Statement statement = null;
		ResultSet res = null;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Mucherlas1");
			statement = conn.createStatement();
			String q1 = "select * from dept";
			res = statement.executeQuery(q1);
			while (res.next()) {
				System.out.println(res.getInt(1));
				System.out.println(res.getString(2));
				System.out.println(res.getString("loc"));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				res.close();
				statement.close();
				conn.close();
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String... args) {
		SatementDBImp sd = new SatementDBImp();
		sd.getDeptDetails(10);
	}
}
