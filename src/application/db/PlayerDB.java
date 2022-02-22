package application.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import application.orm.entities.Player;
import application.orm.interfaces.PlayerDBable;
import application.pool.ConnectionPool;
import application.util.*;;

public class PlayerDB implements PlayerDBable {

	@Override
	public ArrayList<Player> getAllPlayers() {
		String statement = "select * from player;";
		ArrayList<Player> players = new ArrayList<>();
		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(statement);

			while (rs.next()) {
				Player player = new Player(rs.getString("PlayerID"), rs.getString("FirstName"),
						rs.getString("LastName"), Util.getCountryEnumByAlpha3(rs.getString("CountryCode")),
						rs.getInt("Elo"), rs.getInt("BestElo"), rs.getInt("Birth"));
				players.add(player);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return players;
	}

	@Override
	public Player getPlayerById(String string) {
		Connection conn = ConnectionPool.getInstance().getConnection();
		Player player = null;
		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery("Select * from player where PlayerID='" + string + "';");

			if (rs.next()) {
				player = new Player(rs.getString("PlayerID"), rs.getString("FirstName"), rs.getString("LastName"),
						Util.getCountryEnumByAlpha3(rs.getString("CountryCode")), rs.getInt("Elo"),
						rs.getInt("BestElo"), rs.getInt("Birth"));
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return player;

	}

	@Override
	public Player getPlayerByName(String firstName, String lastName) {

		String statement = "select * from player where FirstName LIKE '" + firstName + "%' and LastName LIKE \""
				+ lastName + "\";";

		Connection conn = ConnectionPool.getInstance().getConnection();
		Player player = null;
		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(statement);

			if (rs.next()) {
				player = new Player(rs.getString("PlayerID"), rs.getString("FirstName"), rs.getString("LastName"),
						Util.getCountryEnumByAlpha3(rs.getString("CountryCode")), rs.getInt("Elo"),
						rs.getInt("BestElo"), rs.getInt("Birth"));
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return player;

	}

	@Override
	public ArrayList<Player> getPlayersAboveElo(int elo) {
		String statement = "select * from player where Elo > " + elo + ";";
		ArrayList<Player> players = new ArrayList<>();
		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(statement);

			while (rs.next()) {
				Player player = new Player(rs.getString("PlayerID"), rs.getString("FirstName"),
						rs.getString("LastName"), Util.getCountryEnumByAlpha3(rs.getString("CountryCode")),
						rs.getInt("Elo"), rs.getInt("BestElo"), rs.getInt("Birth"));
				players.add(player);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return players;
	}

	@Override
	public Player getWorldChampion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePlayer(int id) {
		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate("delete from player where PlayerID = '" + id + "';");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

	}

	@Override
	public void addPlayer(Player player) {
		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate("Insert into player (PlayerID, FirstName, LastName, Elo) values ('"
					+ UUID.randomUUID().toString() + "', \"" + player.getFirstName() + "\", \"" + player.getLastName()
					+ "\", " + player.getElo() + ");");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public Player getHighestPlayerElo() {
		String statement = "select * from player  order by elo desc limit 1;";
		Connection conn = ConnectionPool.getInstance().getConnection();
		Player player = null;
		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(statement);

			if (rs.next()) {
				player = new Player(rs.getString("PlayerID"), rs.getString("FirstName"), rs.getString("LastName"),
						Util.getCountryEnumByAlpha3(rs.getString("CountryCode")), rs.getInt("Elo"),
						rs.getInt("BestElo"), rs.getInt("Birth"));
				return player;
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return player;
	}

	@Override
	public boolean isPlayerExists(String firstName, String lastName) {
		return this.getPlayerByName(firstName, lastName) != null;
	}

}
