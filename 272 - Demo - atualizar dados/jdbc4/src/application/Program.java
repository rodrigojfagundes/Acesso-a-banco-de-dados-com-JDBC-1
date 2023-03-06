package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {


	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			//se conectando ao banco de dados
			conn = DB.getConnection();
			st = conn.prepareStatement(
					//acessando a TABELA SELLER e ATUALIZANDO o valor dos SALARY
					//onde o DEPARTAMENT for IGUAL AO VALOR INFORMADO
					"UPDATE seller "
					+ "SET BaseSalary = BaseSalary + ? "
					+ "WHERE "
					+ "(DepartmentId = ?)");
			//agora vamos informar o valor das (??) INTERROGACAO acima
			st.setDouble(1, 200.0);
			st.setInt(2, 2);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done! Rows affected" + rowsAffected);
			
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
