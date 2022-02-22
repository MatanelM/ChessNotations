package application.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.orm.entities.Tournament;
import application.orm.interfaces.TournamentDBable;
import application.pool.ConnectionPool;
import application.util.Util;

public class TournamentDB implements TournamentDBable {

	@Override
	public ArrayList<Tournament> getAllTournaments() {
		String statement = "select * from Tournament;";
		ArrayList<Tournament> tournamens= new ArrayList<>();
		Connection conn = ConnectionPool.getInstance().getConnection();
		GameDB gameDB = new GameDB();
		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(statement);

			while (rs.next()) {
				Tournament tournament = 
						new Tournament(rs.getInt("Tournamentid"), 
								rs.getString("TournamentName"),
						 Util.getCountryEnumByAlpha3(rs.getString("CountryCode")),
						rs.getInt("Tournamentid"), gameDB.getTournamentsGamesByName(rs.getString("TournamentName")));
				tournamens.add(tournament);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return tournamens;
	}

	@Override
	public Tournament getTournamentById(int id) {
		Connection conn = ConnectionPool.getInstance().getConnection();
		GameDB gameDB = new GameDB();
		Tournament tournament = null;
		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery("Select * from player where TournamentID='" + id + "';");

			if (rs.next()) {
				tournament = 
						new Tournament(rs.getInt("Tournamentid"), 
								rs.getString("TournamentName"),
						 Util.getCountryEnumByAlpha3(rs.getString("CountryCode")),
						rs.getInt("Tournamentid"), gameDB.getTournamentsGamesByName(rs.getString("TournamentName")));
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return tournament;
	}

	@Override
	public void addTournament(Tournament tournament) {
		Connection conn = ConnectionPool.getInstance().getConnection();
		
		String insertStatement = "Insert into tournament (TournamentName, CountryCode, TournamentYear) values ('"
				+ tournament.getName() +"', '" +
				(tournament.getCountry() == null ? "OOO" : tournament.getCountry().getAlpha3Code())
				+ "', " + tournament.getTournamentYear() + ");";
		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(insertStatement);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public Tournament getTournamentByName(String name) {
		String statement = "select * from Tournament where TournamentName='"+name+"';";
		Connection conn = ConnectionPool.getInstance().getConnection();
		GameDB gameDB = new GameDB();
		Tournament tournament = null;
		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(statement);

			while (rs.next()) {
				tournament = 
						new Tournament(rs.getInt("Tournamentid"), 
								rs.getString("TournamentName"),
						 Util.getCountryEnumByAlpha3(rs.getString("CountryCode")),
						rs.getInt("Tournamentid"), gameDB.getTournamentsGamesByName(rs.getString("TournamentName")));
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return tournament;
	}

	@Override
	public boolean isTournamentExists(String name) {
		return this.getTournamentByName(name) != null;
	}

	@Override
	public void deleteTournamentByName(String name) {
		GameDB gameDB = new GameDB();
		Tournament tournament = this.getTournamentByName(name);
		tournament.getGames().forEach(g -> {
			gameDB.deleteGame(g);
		});
		String statementTournament = "delete from tournament where TournamentName=\""+name+"\";";
		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(statementTournament);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

}
