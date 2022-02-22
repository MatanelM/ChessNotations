package application.orm.interfaces;

import java.util.ArrayList;

import application.orm.entities.ChampionshipGame;
import application.orm.entities.Game;
import application.orm.entities.Player;
import application.orm.entities.TournamentGame;

public interface GameDBable {

	public ArrayList<Game> getAllGames();
	
	public ArrayList<ChampionshipGame> getAllChamiponshipGames();
	public ArrayList<TournamentGame> getAllTournamentGames();
	public Player getTournamentGameWinner(int tournamentId);
	public ChampionshipGame getChampionshipGame(int year, int gameNumber);
	
	public ArrayList<ChampionshipGame> getChamiponshipGamesByYear(int year);
	public void addChampionshipGame(ChampionshipGame game, int championshipID);
	
	public ArrayList<TournamentGame> getTournamentsGamesByName(String name);
	public Game getTournamentGameById(int tournamentId, int gameid);
	public void addTournamentGame(TournamentGame game, int tournamentID);
	
	
	public ArrayList<Game> getGamesOfPlayerByName(String firstName, String lastName);
	
	public void deleteGame(Game game);
}
