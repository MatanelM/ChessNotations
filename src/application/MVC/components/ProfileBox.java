package application.MVC.components;

import java.util.ArrayList;

import application.MVC.components.MyLabel.LabelSize;
import application.orm.entities.*;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class ProfileBox extends VBox {

	private Player player;
	private ArrayList<Game> games;

	public ProfileBox(Player player, ArrayList<Championship> cgames, ArrayList<TournamentGame> tgames) {
		super();
		this.player = player;

		MyLabel lbl = new MyLabel(String.format("%s %s", player.getLastName(), player.getFirstName()), LabelSize.LARGE);
		this.getChildren().addAll(lbl);

		if ( !cgames.isEmpty()) {
			MyLabel lblNamesWhite = new MyLabel(String.format("Championship games: "), LabelSize.MEDIUM);
			this.getChildren().addAll(lblNamesWhite, new Separator());
		}
		for (Championship wch : cgames) {
			MyLabel lblNamesWhite = new MyLabel(String.format("White: %s %s", wch.getWhitePlayer().getLastName(),
					wch.getWhitePlayer().getFirstName()), LabelSize.MEDIUM);
			MyLabel lblNamesBlack = new MyLabel(String.format("Black: %s %s", wch.getBlackPlayer().getLastName(),
					wch.getBlackPlayer().getFirstName()), LabelSize.MEDIUM);

			this.getChildren().addAll(lblNamesWhite, lblNamesBlack);
			for (ChampionshipGame wchg : wch.getGames()) {
				MyLabel lblRound = new MyLabel(String.format("Round: %d", wchg.getGameNumber()), LabelSize.MEDIUM);
				this.getChildren().addAll(lblRound);
				for (Notation n : wchg.getNotations()) {
					this.getChildren().addAll(new MyLabel(n.toString(), LabelSize.SMALL));
				}
			}
		}
		if ( !tgames.isEmpty()) {
			MyLabel lblNamesWhite = new MyLabel(String.format("Tournament games: "), LabelSize.MEDIUM);
			this.getChildren().addAll(lblNamesWhite, new Separator());
		}
		for (TournamentGame game : tgames) {

			TournamentGame tgame = (TournamentGame) game;
			MyLabel lblNamesWhite = new MyLabel(String.format("White: %s %s", tgame.getWhitePlayer().getLastName(),
					tgame.getWhitePlayer().getFirstName()), LabelSize.MEDIUM);
			MyLabel lblNamesBlack = new MyLabel(String.format("Black: %s %s", tgame.getBlackPlater().getLastName(),
					tgame.getBlackPlater().getFirstName()), LabelSize.MEDIUM);
			this.getChildren().addAll(lblNamesWhite, lblNamesBlack);

			for (Notation n : game.getNotations()) {
				this.getChildren().addAll(new MyLabel(n.toString(), LabelSize.SMALL));
			}
			MyLabel lblResult = new MyLabel(
					String.format("Result: %.1f %.1f", game.getWhiteResult(), game.getBlackResult()), LabelSize.MEDIUM);

			this.getChildren().addAll(lblResult, new Separator());

		}
	}
}
