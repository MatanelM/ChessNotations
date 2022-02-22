package application.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.orm.entities.Championship;
import application.orm.entities.Player;
import application.orm.interfaces.ChampionshipDBable;
import application.pool.ConnectionPool;
import application.util.Util;

public class ChampionshipDB implements ChampionshipDBable {

	@Override
	public ArrayList<Championship> getAllChampionships() {
		String statement = "select * from championship;";
		ArrayList<Championship> championships = new ArrayList<>();
		GameDB gameDB = new GameDB();
		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(statement);

			while (rs.next()) {
				PlayerDB playerDB = new PlayerDB();

				Player white = playerDB.getPlayerById(rs.getString("WhitePlayer"));
				Player black = playerDB.getPlayerById(rs.getString("BlackPlayer"));
				if (white == null || black == null)
					continue;

				Championship championship = new Championship(rs.getInt("Championshipid"), white, black,
						Util.getCountryEnumByAlpha3(rs.getString("Country")), rs.getInt("ChampionshipYear"));
				championship.setGames(gameDB.getChamiponshipGamesByYear(championship.getChapionshipYear()));
				championships.add(championship);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return championships;
	}

	@Override
	public Championship getChampionshipByYear(int year) {
		Connection conn = ConnectionPool.getInstance().getConnection();
		Championship championship = null;
		GameDB gameDB = new GameDB();
		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery("Select * from championship where ChampionshipYear=" + year + ";");

			if (rs.next()) {
				PlayerDB playerDB = new PlayerDB();

				Player white = playerDB.getPlayerById(rs.getString("WhitePlayer"));
				Player black = playerDB.getPlayerById(rs.getString("BlackPlayer"));
				if (white == null || black == null)
					championship = null;
				else {
					championship = new Championship(rs.getInt("Championshipid"), white, black,
							Util.getCountryEnumByAlpha3(rs.getString("Country")), rs.getInt("ChampionshipYear"));
					championship.setGames(gameDB.getChamiponshipGamesByYear(championship.getChapionshipYear()));
				}
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return championship;
	}

	@Override
	public void addChampionship(Championship championship) {
		Connection conn = ConnectionPool.getInstance().getConnection();
		String statement = "Insert into championship (Championshipid, BlackPlayer, WhitePlayer, Country, ChampionshipYear) values ("
				+ championship.getChampionshipId() + ", '" + championship.getBlackPlayer().getId() + "', '" + championship.getWhitePlayer().getId() + "', '"
				+ (championship.getCountry() == null ? "OOO" : championship.getCountry().getAlpha3Code()) + "', " + championship.getChapionshipYear() + ");";
		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(statement);

		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionPool.getInstance().returnConnection(conn);
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

	}

	public boolean isChampionshipExists(int chapionshipYear) {
		return getChampionshipByYear(chapionshipYear) != null;
	}

	public void deleteChampionship(Championship c) {
		GameDB gameDB = new GameDB();
		Connection conn = ConnectionPool.getInstance().getConnection();
		c.getGames().forEach(g -> gameDB.deleteGame(g));
		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate("delete from championship where Championshipid = '" + c.getChampionshipId() + "';");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		
	}

}
