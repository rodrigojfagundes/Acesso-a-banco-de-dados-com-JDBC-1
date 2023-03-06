package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class Program {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			//criando uma variavel para saber o numero de linhas mexidas (ROWS1)
			int rows1 = st.executeUpdate("update seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			int rows2 = st.executeUpdate("update seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			//chamando o COMMIT para CONFIRMAR SE TODAS AS 2 TRANSACOES FORAM
			//FEITAS
			conn.commit();
			
			System.out.println("rows 1 " + rows1);
			System.out.println("rows 2 " + rows2);
		}

		catch (SQLException e) {

			try {
				conn.rollback();
				throw new DbException("transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {

				throw new DbException("error trying to roolback! Caused by " + e1.getMessage());
			}
		}

		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
