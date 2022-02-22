package application.MVC;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import application.IO.InputFiles;
import application.db.ChampionshipDB;
import application.db.GameDB;
import application.db.PlayerDB;
import application.db.TournamentDB;
import application.orm.entities.Championship;
import application.orm.entities.ChampionshipGame;
import application.orm.entities.Game;
import application.orm.entities.Player;
import application.orm.entities.Tournament;
import application.orm.entities.TournamentGame;

public class Model {

	private ArrayList<ModelListener> listeners;

	private ArrayList<Game> games;
	private ArrayList<Player> players;
	private ArrayList<Tournament> tournaments;
	private ArrayList<Championship> championships;

	public Model() {
		games = new ArrayList<>();
		players = new ArrayList<>();
		tournaments = new ArrayList<>();
		championships = new ArrayList<>();
		this.listeners = new ArrayList<>();
	}

	public void addListener(ModelListener listener) {
		this.listeners.add(listener);
	}

	public void addFileToDBInModel(String filepath, ArrayList<Tournament> tournaments2, ArrayList<Championship> championships2, ArrayList<Game> games2, ArrayList<Player> players2) {

		try {
			InputFiles.readFile(filepath, tournaments2, championships2, games2, players2);
		} catch (FileNotFoundException e) {
			for(ModelListener l: listeners) {
				l.showErrorFromModel(e.getMessage());
			}
		}
	}

	public void loadData() {
		
		games = new GameDB().getAllGames();
		players = new PlayerDB().getAllPlayers();
		tournaments = new TournamentDB().getAllTournaments();
		championships = new ChampionshipDB().getAllChampionships();
		
		fireSetDataFromModel(games, players, tournaments, championships);
		
		
	}

	private void fireSetDataFromModel(ArrayList<Game> games2, ArrayList<Player> players2,
			ArrayList<Tournament> tournaments2, ArrayList<Championship> championships2) {
		for (ModelListener l : listeners) {
			l.setDataFromModel(tournaments2, championships2, games2, players2);
		}
		
	}

	public void deleteTournamentByNameInModel(String text) {
		TournamentDB tdb = new TournamentDB();
		try {
			tdb.deleteTournamentByName(text);
			fireMessageToViewFromModel(String.format("Tournament %s deleted", text));
		}catch(Exception e) {
			fireMessageToViewFromModel(e.getMessage());
		}
		
	}

	private void fireMessageToViewFromModel(String format) {
		listeners.forEach(l -> l.showErrorFromModel(format));
	}

	public void deleteGameFromModel(Game g) {
		GameDB gdb = new GameDB();
		try {
			gdb.deleteGame(g);
			
			fireMessageToViewFromModel(String.format("Game %d deleted", g.getId()));
		}catch(Exception e) {
			fireMessageToViewFromModel(e.getMessage());
		}
	}

	public void deleteChampionshipFromModel(Championship c) {
		ChampionshipDB cdb = new ChampionshipDB();
		try {
			cdb.deleteChampionship(c);
			
			fireMessageToViewFromModel(String.format("%dChampionship deleted", c.getChapionshipYear()));
		}catch(Exception e) {
			fireMessageToViewFromModel(e.getMessage());
		}
	}


	
}
