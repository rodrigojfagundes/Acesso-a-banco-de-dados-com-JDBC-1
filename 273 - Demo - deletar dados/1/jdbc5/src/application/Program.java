package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					//o prepareStatement recebe o comando SQL para DELETAR
					//algo na TABELA DEPARTMENT... WHERE/ONDE qm tiver o ID
					//IGUAL o valor q sera passado para a "??" interrogacao
					"DELETE FROM department "
					+ "WHERE "
					+ "Id = ?");
			
			//informando QUAL sera o VALOR do PRIMEIRO "?" Interrogacao
			st.setInt(1, 5);

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
