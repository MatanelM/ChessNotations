package application.MVC.components;

import java.util.ArrayList;

import application.MVC.components.MyLabel.LabelSize;
import application.orm.entities.ChampionshipGame;
import application.orm.entities.Game;
import application.orm.entities.Notation;
import application.orm.entities.TournamentGame;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class GamesBox extends VBox{

	private ArrayList<Game> games;

	public GamesBox(ArrayList<Game> games) {
		super();
		this.games = games;
		
		for (Game game : games) {
			
			if ( game instanceof ChampionshipGame ) {
				
				ChampionshipGame cgame = (ChampionshipGame) game;
				
				for (Notation n : cgame.getNotations()) {
					this.getChildren().addAll(new MyLabel(n.toString(), LabelSize.SMALL));
				}
				
			}else if (game instanceof TournamentGame ) {
				TournamentGame tgame = (TournamentGame )game;
				MyLabel lblNamesWhite = new MyLabel(String.format("White: %s %s", tgame.getWhitePlayer().getLastName(), tgame.getWhitePlayer().getFirstName()), LabelSize.MEDIUM);
				MyLabel lblNamesBlack = new MyLabel(String.format("Black: %s %s", tgame.getBlackPlater().getLastName(), tgame.getBlackPlater().getFirstName()), LabelSize.MEDIUM);
				this.getChildren().addAll(lblNamesWhite, lblNamesBlack);
				
				for (Notation n : game.getNotations()) {
					this.getChildren().addAll(new MyLabel(n.toString(), LabelSize.SMALL));
				}
			}
			MyLabel lblResult = new MyLabel(String.format("Result: %.1f %.1f", game.getWhiteResult(), game.getBlackResult()), LabelSize.MEDIUM);
			MyLabel lblID = new MyLabel(String.format("GameID: %d", game.getId()), LabelSize.SMALL);
			this.getChildren().addAll(lblResult,lblID,new Separator());
					
		}
		
	}
	
}
