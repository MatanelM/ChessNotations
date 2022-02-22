package application.MVC;

import java.util.ArrayList;

import application.orm.entities.Championship;
import application.orm.entities.Game;
import application.orm.entities.Player;
import application.orm.entities.Tournament;

public interface ViewListener {


	void addFileToDBFromView(String filepath, ArrayList<Tournament> tournaments, ArrayList<Championship> championships, ArrayList<Game> games, ArrayList<Player> players);

	void deleteTournamentFromView(String text);

	void deleteGameFromView(Game g);

	void deleteChampionshipFromView(Championship wch);

}
