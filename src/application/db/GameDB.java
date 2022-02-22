package application.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import application.orm.entities.ChampionshipGame;
import application.orm.entities.Game;
import application.orm.entities.Notation;
import application.orm.entities.Player;
import application.orm.entities.TournamentGame;
import application.orm.interfaces.GameDBable;
import application.pool.ConnectionPool;

public class GameDB implements GameDBable {

	@Override
	public ArrayList<Game> getAllGames() {
		ArrayList<Game> games = new ArrayList<>();
		games.addAll(this.getAllChamiponshipGames());
		games.addAll(this.getAllTournamentGames());

		return games;
	}

	@Override
	public ArrayList<ChampionshipGame> getAllChamiponshipGames() {
		String statementChampionshipGame = "select * from championshipgame natural join game;";
		NotationDB notationDB = new NotationDB();
		ArrayList<ChampionshipGame> games = new ArrayList<>();
		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(statementChampionshipGame);

			while (rs.next()) {
				ChampionshipGame game = new ChampionshipGame();
				game.setId(rs.getInt("GameID"));
				game.setDate(rs.getDate("GameDate"));
				game.setBlackResult(rs.getFloat("BlackResultID"));
				game.setWhiteResult(rs.getFloat("WhiteResultID"));
				game.setGameNumber(rs.getInt("GameNumber"));
				game.setNotations(notationDB.getGameNotations(game.getId()));
				games.add(game);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return games;
	}

	@Override
	public ArrayList<TournamentGame> getAllTournamentGames() {
		String statementTournamentGame = "select * from tournamentgame natural join game;";
		PlayerDB playerDB = new PlayerDB();
		ArrayList<TournamentGame> games = new ArrayList<>();
		Connection conn = ConnectionPool.getInstance().getConnection();
		NotationDB notationDB = new NotationDB();
		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(statementTournamentGame);

			while (rs.next()) {

				Player white = playerDB.getPlayerById(rs.getString("WhitePlayerID"));
				Player black = playerDB.getPlayerById(rs.getString("BlackPlayerID"));
				if (white == null || black == null)
					continue;

				TournamentGame game = new TournamentGame();

				game.setId(rs.getInt("GameID"));
				game.setDate(rs.getDate("GameDate"));
				game.setBlackResult(rs.getFloat("BlackResultID"));
				game.setWhiteResult(rs.getFloat("WhiteResultID"));
				game.setTournamentId(rs.getInt("Tournamentid"));
				game.setWhitePlayer(white);
				game.setBlackPlater(black);
				game.setNotations(notationDB.getGameNotations(game.getId()));
				games.add(game);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return games;
	}

	@Override
	public Player getTournamentGameWinner(int tournamentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addChampionshipGame(ChampionshipGame game, int championshipID) {
		NotationDB notationDB = new NotationDB();
		int gameID = game.getGameNumber();
		if (game.getNotations()!= null ) {
			gameID += game.getNotations().hashCode();
		}
		game.setId( gameID);
		String updateGame = "insert into game (GameID, GameDate, BlackResultID, WhiteResultID ) values (" + game.getId() + ", '"
				+ game.getDate() + "', " + game.getBlackResult() + ", " + game.getWhiteResult() + ");";
		String updateChampionship = "insert into championshipgame (GameNumber, Championshipid, GameID) value ("
				+ game.getGameNumber() + ", '" + championshipID + "', " + game.getId() + ");";
		
		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(updateGame);

			stmt.executeUpdate(updateChampionship);
			game.getNotations().forEach(n -> {
				notationDB.addNotation(game.getId(), n);
			});
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public void addTournamentGame(TournamentGame game, int tournamentID) {
		Connection conn = ConnectionPool.getInstance().getConnection();
		NotationDB notationDB = new NotationDB();
		
		try (Statement stmt = conn.createStatement()) {
			int gameID = game.getWhitePlayer().getElo() + game.getBlackPlater().getElo();
			if (game.getNotations() != null ) {
				gameID += game.getNotations().hashCode();
				game.setId(gameID);
				String gameUpdateStatement = "insert into game (GameID, GameDate, BlackResultID, WhiteResultID ) values (" + gameID + ", '"
						+ game.getDate() + "', " + game.getBlackResult() + ", " + game.getWhiteResult() + ");";
				
				stmt.executeUpdate(gameUpdateStatement);
				
				String tournamentUpdateStatement= "insert into tournamentgame (Tournamentid, GameID, BlackPlayerID, WhitePlayerID) values ("
						+ game.getTournamentId() + ", " + gameID + ", '" + game.getBlackPlater().getId() + "', '"
						+ game.getWhitePlayer().getId() + "');";
				stmt.executeUpdate(tournamentUpdateStatement);
				
				game.getNotations().forEach(n -> {
					notationDB.addNotation(game.getId(), n);
				});
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

	}

	@Override
	public ChampionshipGame getChampionshipGame(int year, int gameNumber) {
		String statementChampionshipGame = "select * from championshipgame natural join game natural join championship where ChampionshipYear="
				+ year + " and GameNumber=" + gameNumber + ";";
		NotationDB notationDB = new NotationDB();
		ChampionshipGame game = null;
		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(statementChampionshipGame);

			if (rs.next()) {
				game = new ChampionshipGame();
				game.setId(rs.getInt("GameID"));
				game.setDate(rs.getDate("GameDate"));
				game.setBlackResult(rs.getFloat("BlackResultID"));
				game.setWhiteResult(rs.getFloat("WhiteResultID"));
				game.setGameNumber(rs.getInt("GameNumber"));
				game.setNotations(notationDB.getGameNotations(game.getId()));

			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return game;
	}

	@Override
	public ArrayList<ChampionshipGame> getChamiponshipGamesByYear(int year) {
		String statementChampionshipGame = "select * from championshipgame natural join game natural join championship where ChampionshipYear="
				+ year + ";";
		NotationDB notationDB = new NotationDB();
		ArrayList<ChampionshipGame> games = new ArrayList<>();
		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(statementChampionshipGame);

			while (rs.next()) {
				ChampionshipGame game = new ChampionshipGame();
				game.setId(rs.getInt("GameID"));
				game.setDate(rs.getDate("GameDate"));
				game.setBlackResult(rs.getFloat("BlackResultID"));
				game.setWhiteResult(rs.getFloat("WhiteResultID"));
				game.setGameNumber(rs.getInt("GameNumber"));
				game.setNotations(notationDB.getGameNotations(game.getId()));
				games.add(game);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return games;
	}

	@Override
	public ArrayList<TournamentGame> getTournamentsGamesByName(String name) {
		String statementTournamentGame = "select * from tournamentgame natural join game natural join tournament WHERE TournamentName=\""
				+ name + "\";";
		PlayerDB playerDB = new PlayerDB();
		ArrayList<TournamentGame> games = new ArrayList<>();
		Connection conn = ConnectionPool.getInstance().getConnection();
		NotationDB notationDB = new NotationDB();
		
		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(statementTournamentGame);

			while (rs.next()) {

				Player white = playerDB.getPlayerById(rs.getString("WhitePlayerID"));
				Player black = playerDB.getPlayerById(rs.getString("BlackPlayerID"));
				if (white == null || black == null)
					continue;

				TournamentGame game = new TournamentGame();

				game.setId(rs.getInt("GameID"));
				game.setDate(rs.getDate("GameDate"));
				game.setBlackResult(rs.getFloat("BlackResultID"));
				game.setWhiteResult(rs.getFloat("WhiteResultID"));
				game.setTournamentId(rs.getInt("Tournamentid"));
				game.setWhitePlayer(white);
				game.setBlackPlater(black);
				game.setNotations(notationDB.getGameNotations(game.getId()));
				games.add(game);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return games;
	}

	@Override
	public TournamentGame getTournamentGameById(int tournamentId, int gameid) {
		String queryTournamentGame = "select * from tournamentgame natural join game natural join tournament where Tournamentid="
				+ tournamentId + " and GameID=" + gameid + " ;";

		TournamentGame game = null;
		Connection conn = ConnectionPool.getInstance().getConnection();
		PlayerDB playerDB = new PlayerDB();
		NotationDB notationDB = new NotationDB();
		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(queryTournamentGame);

			while (rs.next()) {

				Player white = playerDB.getPlayerById(rs.getString("WhitePlayerID"));
				Player black = playerDB.getPlayerById(rs.getString("BlackPlayerID"));
				if (white == null || black == null)
					continue;
				game = new TournamentGame();
				game.setId(rs.getInt("GameID"));
				game.setDate(rs.getDate("GameDate"));
				game.setBlackResult(rs.getFloat("BlackResultID"));
				game.setWhiteResult(rs.getFloat("WhiteResultID"));
				game.setTournamentId(rs.getInt("Tournamentid"));
				game.setWhitePlayer(white);
				game.setBlackPlater(black);
				game.setNotations(notationDB.getGameNotations(game.getId()));
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return game;

	}

	@Override
	public ArrayList<Game> getGamesOfPlayerByName(String firstName, String lastName) {
		PlayerDB playerDB = new PlayerDB();
		Player player = playerDB.getPlayerByName(firstName, lastName);
		NotationDB notationDB = new NotationDB();
		ArrayList<Game> games = new ArrayList<Game>();
		String queryPlayerChampionships = "select * from championshipgame natural join game where BlackPlayer='"
				+ player.getId() + "' or WhitePlayer='" + player.getId() + "';";
		String queryPlayerTournaments = "select * from tournamentgame natural join game where BlackPlayer='"
				+ player.getId() + "' or WhitePlayer='" + player.getId() + "';";

		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(queryPlayerChampionships);

			while (rs.next()) {
				ChampionshipGame game = new ChampionshipGame();
				game.setId(rs.getInt("GameID"));
				game.setDate(rs.getDate("GameDate"));
				game.setBlackResult(rs.getFloat("BlackResultID"));
				game.setWhiteResult(rs.getFloat("WhiteResultID"));
				game.setGameNumber(rs.getInt("GameNumber"));
				game.setNotations(notationDB.getGameNotations(game.getId()));
				games.add(game);
			}

			rs = stmt.executeQuery(queryPlayerTournaments);
			while (rs.next()) {

				Player white = playerDB.getPlayerById(rs.getString("WhitePlayerID"));
				Player black = playerDB.getPlayerById(rs.getString("BlackPlayerID"));
				if (white == null || black == null)
					continue;

				TournamentGame game = new TournamentGame();

				game.setId(rs.getInt("GameID"));
				game.setDate(rs.getDate("GameDate"));
				game.setBlackResult(rs.getFloat("BlackResultID"));
				game.setWhiteResult(rs.getFloat("WhiteResultID"));
				game.setTournamentId(rs.getInt("Tournamentid"));
				game.setWhitePlayer(white);
				game.setBlackPlater(black);
				game.setNotations(notationDB.getGameNotations(game.getId()));
				games.add(game);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return games;
	}

	@Override
	public void deleteGame(Game game) {
		String statementGame = "delete from game where GameID="+game.getId()+";";
		Connection conn = ConnectionPool.getInstance().getConnection();

		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(statementGame);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	

}
