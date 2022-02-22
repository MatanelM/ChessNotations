package application.MVC;

import java.util.ArrayList;

import application.IO.InputFiles;
import application.orm.entities.Championship;
import application.orm.entities.Game;
import application.orm.entities.Player;
import application.orm.entities.Tournament;

public class Controller implements ViewListener, ModelListener{

	private AbstractView view;
	private Model model;
	
	public Controller(AbstractView view, Model model) {
		super();
		this.view = view;
		this.model = model;
		
		this.view.addListener(this);
		this.model.addListener(this);
		
	}



	@Override
	public void showErrorFromModel(String message) {
		this.view.showErrorInView(message);
	}



	@Override
	public void addFileToDBFromView(String filepath, ArrayList<Tournament> tournaments,
			ArrayList<Championship> championships, ArrayList<Game> games, ArrayList<Player> players) {
		this.model.addFileToDBInModel(filepath, tournaments, championships, games, players);
		
	}



	@Override
	public void setDataFromModel(ArrayList<Tournament> tournaments2, ArrayList<Championship> championships2,
			ArrayList<Game> games2, ArrayList<Player> players2) {
		this.view.setDataToView(tournaments2, championships2, games2, players2);
		
	}



	@Override
	public void deleteTournamentFromView(String text) {
		
		this.model.deleteTournamentByNameInModel(text);
	}



	@Override
	public void deleteGameFromView(Game g) {
		this.model.deleteGameFromModel(g);
	}



	@Override
	public void deleteChampionshipFromView(Championship wch) {
		this.model.deleteChampionshipFromModel(wch);
		
	}



	
	
}
