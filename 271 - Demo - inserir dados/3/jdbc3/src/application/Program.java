package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
					
			//com 1 comando SQL estamos inserindo 2 DEPARTMENT ao mesmo TEMPO
			st = conn.prepareStatement(
					"insert into department (Name) values ('D1'), ('D2')", 
					Statement.RETURN_GENERATED_KEYS);
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! id = " + id);
				}
				
			}
			else {
				System.out.println("no rown affected");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
