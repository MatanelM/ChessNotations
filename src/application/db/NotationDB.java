package application.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.orm.entities.Notation;
import application.orm.interfaces.NotationDBable;
import application.pool.ConnectionPool;

public class NotationDB implements NotationDBable {

	@Override
	public ArrayList<Notation> getGameNotations(int gameId) {
		String statement = "select * from notation where GameID="+gameId+";";
		ArrayList<Notation> notations = new ArrayList<>();
		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(statement);

			while (rs.next()) {
				Notation notation = new Notation(rs.getInt("MoveNumber"),
						rs.getString("DescriptionMove"));
				notations.add(notation);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return notations;
	}

	@Override
	public Notation getNotationByGameAndId(int gameId, int notationId) {
		String statement = "select * from notation where GameID="+gameId+" and MoveNumber="+notationId+";";
		Notation notation = null;
		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(statement);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return notation;
	}

	@Override
	public void removeNotation(int gameId, int notationId) {
		String statement = "delete from notation Where GameID=" + gameId + " and MoveNumber=" + notationId + " ;";

		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(statement);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public void updateNotationDescription(int gameId, Notation notation) {
		String statement = "Update notation Set DescriptionMove='" + notation.getNotationDescription() + "' "
				+ "Where GameID=" + gameId + " and MoveNumber=" + notation.getId() + " ;";

		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(statement);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

	}

	@Override
	public void addNotation(int gameId, Notation notation) {
		String statement = "insert into notation (GameID, MoveNumber, DescriptionMove) values (" + gameId + ","
				+ notation.getId() + ", '" + notation.getNotationDescription() + "');";

		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(statement);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

	}

}
