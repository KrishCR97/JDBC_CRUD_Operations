package com.cr7.jdbcoperations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;

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
		CallableStatement statement1 = null;
		ResultSet res = null;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Mucherlas1");
			statement = conn.createStatement();
			String q1 = "select * from dept where deptno = " + deptNo;
			res = statement.executeQuery(q1);
			ResultSetMetaData rmd = res.getMetaData();
			System.out.println(rmd.getColumnName(1));
			DatabaseMetaData dmd = conn.getMetaData();
			System.out.println(dmd.getDatabaseProductName());
			// made call to the sql procedure present in teh database
			statement1 = conn.prepareCall("{call getLoc(?,?)}");
			statement1.setInt(1, 10);
			statement1.registerOutParameter(2, Types.CHAR);
			statement1.execute();
			System.out.println(statement1.getString(2));
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
