package application.MVC.components;

import java.util.ArrayList;

import application.MVC.components.MyLabel.LabelSize;
import application.orm.entities.Notation;
import application.orm.entities.Tournament;
import application.orm.entities.TournamentGame;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class TournamentBox extends VBox{

	private ArrayList<TournamentGame> games;
	private String name;
	
	
	
	public TournamentBox(ArrayList<TournamentGame> games, String name) {
		super();
		this.games = games;
		this.name = name;
		
		MyLabel lbl = new MyLabel(name, LabelSize.LARGE);
		this.getChildren().addAll(lbl);
		for (TournamentGame game : games) {
			MyLabel lblNamesWhite = new MyLabel(String.format("White: %s %s", game.getWhitePlayer().getLastName(), game.getWhitePlayer().getFirstName()), LabelSize.MEDIUM);
			MyLabel lblNamesBlack = new MyLabel(String.format("Black: %s %s", game.getBlackPlater().getLastName(), game.getBlackPlater().getFirstName()), LabelSize.MEDIUM);
			this.getChildren().addAll(lblNamesWhite, lblNamesBlack);
			for (Notation n : game.getNotations()) {
				this.getChildren().addAll(new MyLabel(n.toString(), LabelSize.SMALL));
			}
			MyLabel lblResult = new MyLabel(String.format("Result: %.1f %.1f", game.getWhiteResult(), game.getBlackResult()), LabelSize.MEDIUM);
			
			this.getChildren().addAll(lblResult,new Separator());
					
		}
	}
	
	
	
	
}
