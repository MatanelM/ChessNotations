package application.MVC.components;

import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

import application.MVC.components.MyLabel.LabelSize;
import application.orm.entities.*;;
public class ChampionshipBox extends VBox {

	private ArrayList<ChampionshipGame> games;
	private Championship championship;
	public ChampionshipBox(ArrayList<ChampionshipGame> games, Championship championship) {
		super();
		this.games = games;
		this.championship = championship;
		
		MyLabel lbl = new MyLabel(String.format("World Championship %d", championship.getChapionshipYear()), LabelSize.LARGE);
		
		MyLabel lblNamesWhite = new MyLabel(String.format("White: %s %s", championship.getWhitePlayer().getLastName(), championship.getWhitePlayer().getFirstName()), LabelSize.MEDIUM);
		MyLabel lblNamesBlack = new MyLabel(String.format("Black: %s %s", championship.getBlackPlayer().getLastName(), championship.getBlackPlayer().getFirstName()), LabelSize.MEDIUM);
		
		this.getChildren().addAll(lbl, lblNamesWhite, lblNamesBlack);
		for (ChampionshipGame game : games) {
			for (Notation n : game.getNotations()) {
				this.getChildren().addAll(new MyLabel(n.toString(), LabelSize.SMALL));
			}
			MyLabel lblResult = new MyLabel(String.format("Result: %.1f %.1f", game.getWhiteResult(), game.getBlackResult()), LabelSize.MEDIUM);
			
			this.getChildren().addAll(lblResult,new Separator());
			
		}
	}
	
	
	
	
}
