package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbIntegrityException;

public class Program {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("update seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			
			int x = 1;
			if (x < 2) {
				throw new SQLException("fake error");
			}
			
			
			int rows2 = st.executeUpdate("update seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

			System.out.println("rows 1" + rows1);
			System.out.println("rows 2" + rows2);
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
