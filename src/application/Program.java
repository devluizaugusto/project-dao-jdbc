package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2500 WHERE DepartmentId = 1");
			
			//int x = 1;
			
			//if(x < 2) {
				//throw new SQLException("FAKE ERROR!");
		//	}
		
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3500 WHERE DepartmentId = 2");
			
			conn.commit();
			
			System.out.println("ROWS 1: " + rows1);
			System.out.println("ROWS 2: " + rows2);
		} 
		catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("TRANSACTION ROLLED BACK! CAUSED BY: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("ERROR TRYING TO ROLLBACK! CAUSED BY: " + e.getMessage());
			}
		} 
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
