package application.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.orm.entities.Country;
import application.orm.interfaces.CountryDBable;
import application.pool.ConnectionPool;

public class CountryDB implements CountryDBable {

	public ArrayList<Country> getAllCountries() {

		ArrayList<Country> countries = new ArrayList<Country>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM country;");
			
			while(rs.next()) {
				countries.add(new Country( rs.getString("Country"), rs.getString("Alpha2_code"), rs.getString("Alpha3_code"), rs.getInt("CountryId") ));
			}
		}catch(SQLException e ) {
			e.printStackTrace();
		}finally {
			if ( stmt != null )
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if ( rs != null )
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			ConnectionPool.getInstance().returnConnection(conn);
			
		}
		
		return countries;
	}

}
