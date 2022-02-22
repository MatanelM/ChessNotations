package application.MVC;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.sun.javafx.image.impl.ByteIndexed.Getter;

import application.IO.InputFiles;
import application.MVC.components.ChampionshipBox;
import application.MVC.components.GamesBox;
import application.MVC.components.MyButton;
import application.MVC.components.MyLabel;
import application.MVC.components.ProfileBox;
import application.MVC.components.TournamentBox;
import application.MVC.components.MyLabel.LabelSize;
import application.MVC.components.MyButton.ButtonSizeType;
import application.orm.entities.Championship;
import application.orm.entities.ChampionshipGame;
import application.orm.entities.Game;
import application.orm.entities.Player;
import application.orm.entities.Tournament;
import application.orm.entities.TournamentGame;
import application.util.Util;

import java.awt.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class View implements AbstractView {

	private static final int WIDTH = 1300;
	private static final int HEIGHT = 700;
	private static final Font LABEL_FONT = Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 24);

	private ArrayList<Game> games;
	private ArrayList<Player> players;
	private ArrayList<Tournament> tournaments;
	private ArrayList<Championship> championships;

	private ArrayList<ViewListener> listeners;
	private Desktop desktop = Desktop.getDesktop();

	private HBox Top;
	private VBox vbCenter;

	private ProfileBox pBox;
	private TournamentBox tBox;
	private ChampionshipBox cBox;

	private VBox screen1;
	private VBox screen2;
	private HBox hbScreen2;
	private ScrollPane spRight;

	private VBox vbRight;
	private VBox screen3;

	private String filepath = "C:\\Users\\MatanelM\\workspace\\ChessNotations\\world-chess-2013-anand-carlsen.pgn";

	public View(Stage stage) {
		games = new ArrayList<>();
		players = new ArrayList<>();
		tournaments = new ArrayList<>();
		championships = new ArrayList<>();
		this.listeners = new ArrayList<>();
		VBox root = new VBox();
		vbCenter = new VBox();
		vbCenter.setPadding(new Insets(30));

		root.setBackground(
				new Background(new BackgroundImage(new Image("file:assets/main.jpg"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true))));
		HBox top = new HBox();
		MyButton btnSwitchToAdd = new MyButton("Add Game", MyButton.ButtonSizeType.MEDIUM);
		btnSwitchToAdd.setOnAction(e -> {
			switchCenter(screen1);
		});
		MyButton btnSwitchToSearch = new MyButton("Search Game", MyButton.ButtonSizeType.MEDIUM);
		btnSwitchToSearch.setOnAction(e -> {
			switchCenter(screen2);
		});
		MyButton btnSwitchToDelete = new MyButton("Remove Game", MyButton.ButtonSizeType.MEDIUM);
		btnSwitchToDelete.setOnAction(e -> {
			switchCenter(screen3);
		});
		top.setPrefHeight(150);
		top.setMinHeight(140);
		top.setAlignment(Pos.CENTER);
		top.setSpacing(10);
		top.getChildren().addAll(btnSwitchToAdd, btnSwitchToSearch, btnSwitchToDelete);
		// tab 1
		screen1 = new VBox();
		screen1.setAlignment(Pos.CENTER);
		screen1.setSpacing(20);
		HBox hbUpload = new HBox();
		hbUpload.setSpacing(10);
		hbUpload.setAlignment(Pos.CENTER);
		final FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PGN", "*.pgn"));

		final MyButton openButton = new MyButton("Open a pgn file...", ButtonSizeType.SMALL);

		MyLabel lblFileName = new MyLabel("", LabelSize.SMALL);
		hbUpload.getChildren().addAll(openButton, lblFileName);

		openButton.setOnAction(e -> {

			File file = fileChooser.showOpenDialog(stage);
			if (file != null) {
				filepath = file.getPath();
				String extension = Util.findExtension(filepath);
				if (extension.toLowerCase().equals("pgn"))
					lblFileName.setText(filepath);
				else {
					lblFileName.setText("Incorrect file type");
					filepath = "";
				}
			} else {
				filepath = "";
			}
		});

		MyButton btnAddPgn = new MyButton("Add pgn file", MyButton.ButtonSizeType.EXTRA_LARGE);
		btnAddPgn.setOnAction(e -> {
			if (filepath.equals(""))
			{
				lblFileName.setText(filepath);
				return;
			}
			for (ViewListener l : listeners) {
				l.addFileToDBFromView(filepath, tournaments, championships, games, players);
			}
		});
		MyLabel lblAddGameHere = new MyLabel("Add game here", LabelSize.EXTRA_LARGE);
		lblAddGameHere.setFont(LABEL_FONT);
		screen1.getChildren().addAll(lblAddGameHere, hbUpload, btnAddPgn);
		vbCenter.getChildren().add(screen1);
		// tab 2

		screen2 = new VBox();
		BackgroundFill bf = new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY);
		// Background creation
		Background bg = new Background(bf);
		// set background
		screen2.setBackground(bg);
		screen2.setStyle("-fx-background-color: transparent;");
		hbScreen2 = new HBox();
		hbScreen2.setBackground(bg);
		screen2.getChildren().add(hbScreen2);

		VBox vbLeft = new VBox();

		vbRight = new VBox();
		spRight = new ScrollPane();
		hbScreen2.getChildren().addAll(vbLeft, spRight);
		hbScreen2.setSpacing(20);

		VBox vbByPlayers = new VBox();
		MyLabel lblByPlayers = new MyLabel("Search by player", LabelSize.MEDIUM);
		HBox hbSearchByPlayer = new HBox();
		MyLabel lblByPlayersFirst = new MyLabel("First", LabelSize.SMALL);
		TextField tfByPlayersFirst = new TextField();
		MyLabel lblByPlayersLast = new MyLabel("Last", LabelSize.SMALL);
		TextField tfByPlayersLast = new TextField();
		hbSearchByPlayer.getChildren().addAll(lblByPlayersFirst, tfByPlayersFirst, lblByPlayersLast, tfByPlayersLast);
		hbSearchByPlayer.setSpacing(10);
		MyButton btnByPlayers = new MyButton("Search", ButtonSizeType.SMALL);
		btnByPlayers.setOnAction(e -> {
			String firstName = tfByPlayersFirst.getText();
			String lastName = tfByPlayersLast.getText();
			Player player = null;
			for (Player player2 : players) {
				if (player2.getFirstName().toLowerCase().contains(firstName.toLowerCase()) && player2.getLastName() != null
						&& player2.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
					player = player2;
					break;
				}

			}
			if (player == null) {
				showErrorInView("Player name not found");
			} else {
				ProfileBox pbox = new ProfileBox(player, getChampionshipGamesOfPlayer(player), getTournamentGamesOfPlayer(player));
				spRight.setContent(pbox);
			}
		});
		vbByPlayers.getChildren().addAll(lblByPlayers, hbSearchByPlayer, btnByPlayers);
		vbByPlayers.setSpacing(10);

		Separator sep1 = new Separator();

		VBox vbByTournament = new VBox();
		MyLabel lblByTournament = new MyLabel("Search by Tournament Name", LabelSize.MEDIUM);
		TextField tfByTournament = new TextField();
		MyButton btnByTournament = new MyButton("Search", ButtonSizeType.SMALL);
		btnByTournament.setOnAction(e -> {
			String name = tfByTournament.getText();
			ArrayList<TournamentGame> games = getGamesOfTournament(name);
			if (games.isEmpty()) {
				showErrorInView("Tournament not found");
				return;
			}
			TournamentBox tbox = new TournamentBox(games, name);
			spRight.setContent(tbox);
		});
		vbByTournament.getChildren().addAll(lblByTournament, tfByTournament, btnByTournament);
		vbByTournament.setSpacing(10);

		Separator sep2 = new Separator();

		VBox vbByChampionship = new VBox();
		MyLabel lblByChampionship = new MyLabel("Search by Championship Year", LabelSize.MEDIUM);
		TextField tfByChampionship = new TextField();
		MyButton btnByChampionship = new MyButton("Search", ButtonSizeType.SMALL);
		btnByChampionship.setOnAction(e -> {
			int year = 0;
			try {
				year = Integer.parseInt(tfByChampionship.getText());
			} catch (Exception ex) {
				showErrorInView("Not a number");
			}
			ArrayList<ChampionshipGame> games = getGamesOfChampionship(year);
			Championship championship = null;
			for (Championship championship2 : championships) {

				if (championship2.getChapionshipYear() == year) {
					championship = championship2;
					break;
				}
			}
			if (games.isEmpty() || championship == null) {
				showErrorInView("Championship not found");
				return;
			}
			ChampionshipBox cbox = new ChampionshipBox(games, championship);
			spRight.setContent(cbox);
		});
		vbByChampionship.getChildren().addAll(lblByChampionship, tfByChampionship, btnByChampionship);
		vbByChampionship.setSpacing(10);

		Separator sep3 = new Separator();

		VBox vbByEloHigher = new VBox();
		MyLabel lblByEloHigher = new MyLabel("Search by elo higher than", LabelSize.MEDIUM);
		TextField tfByEloHigher = new TextField();
		MyButton btnByEloHigher = new MyButton("Search", ButtonSizeType.SMALL);
		btnByEloHigher.setOnAction(e -> {
			try {
				spRight.setContent(new GamesBox(getGamesWithEloAbove(Integer.parseInt(tfByEloHigher.getText()))));
			}catch (Exception ex) {
				showErrorInView(ex.getMessage());
			}
		});
		vbByEloHigher.getChildren().addAll(lblByEloHigher, tfByEloHigher, btnByEloHigher);
		vbByEloHigher.setSpacing(10);

		Separator sep4 = new Separator();

		VBox vbByEloLower = new VBox();
		MyLabel lblByEloLower = new MyLabel("Search by elo lower than", LabelSize.MEDIUM);
		TextField tfByEloLower = new TextField();
		MyButton btnByEloLower = new MyButton("Search", ButtonSizeType.SMALL);
		btnByEloLower.setOnAction(e -> {
			try {
				spRight.setContent(new GamesBox(getGamesWithEloUnder(Integer.parseInt(tfByEloLower.getText()))));
			}catch (Exception ex) {
				showErrorInView(ex.getMessage());
			}
		});
		vbByEloLower.getChildren().addAll(lblByEloLower, tfByEloLower, btnByEloLower);
		vbByEloLower.setSpacing(10);

		vbLeft.setPrefWidth(WIDTH / 2 - 40);
		vbLeft.getChildren().addAll(vbByPlayers, sep1, vbByTournament, sep2, vbByChampionship, sep3, vbByEloHigher,
				sep4, vbByEloLower);
		vbLeft.setSpacing(5);
		vbRight.getChildren().add(new MyLabel("Content here", LabelSize.LARGE));
		spRight.setPrefWidth(WIDTH / 2 - 40);
		spRight.setMaxWidth(1920);

		spRight.setContent(vbRight);

		// tab 3
		VBox vbDeleteByID = new VBox();
		HBox hbDeleteByID = new HBox();
		hbDeleteByID.setMaxWidth(350);
		MyLabel lblDelete = new MyLabel("The game id: ", LabelSize.MEDIUM);
		lblDelete.setPrefWidth(280);
		TextField tfDeleteId = new TextField();
		MyButton btnDelete = new MyButton("Delete", ButtonSizeType.SMALL);
		btnDelete.setOnAction(e -> {
			try {
				
				int id = Integer.parseInt(tfDeleteId.getText());
				for (Game g : games) {
					if ( g.getId() == id) {
						tournaments.remove(g);
						listeners.forEach(l->l.deleteGameFromView(g));		
						break;
					}
				}
				
			}catch (Exception ex) {
				showErrorInView(ex.getMessage());
				
			}
		});
		hbDeleteByID.getChildren().addAll(lblDelete, tfDeleteId);
		vbDeleteByID.setAlignment(Pos.CENTER);
		vbDeleteByID.setSpacing(10);

		vbDeleteByID.getChildren().addAll(hbDeleteByID, btnDelete);

		VBox vbDeleteByTournament = new VBox();
		HBox hbDeleteByTournament = new HBox();
		hbDeleteByTournament.setMaxWidth(350);
		MyLabel lblDeleteTournament = new MyLabel("The tournament name: ", LabelSize.MEDIUM);
		lblDeleteTournament.setPrefWidth(280);
		TextField tfDeleteTournament = new TextField();
		MyButton btnDeleteTournament = new MyButton("Delete", ButtonSizeType.SMALL);
		btnDeleteTournament.setOnAction(e -> {
			try {
				String text = tfDeleteTournament.getText();
				for (Tournament tournament : tournaments) {
					if ( tournament.getName().equals(text)) {
						tournaments.remove(tournament);
						break;
					}
				}
				listeners.forEach(l->l.deleteTournamentFromView(text));		
				
			}catch (Exception ex) {
				showErrorInView(ex.getMessage());
				
			}
		});
		hbDeleteByTournament.getChildren().addAll(lblDeleteTournament, tfDeleteTournament);
		vbDeleteByTournament.setAlignment(Pos.CENTER);
		vbDeleteByTournament.setSpacing(10);
		vbDeleteByTournament.getChildren().addAll(hbDeleteByTournament, btnDeleteTournament);

		VBox vbDeleteByChampionship = new VBox();
		HBox hbDeleteByChampionship = new HBox();
		hbDeleteByChampionship.setMaxWidth(350);
		MyLabel lblDeleteChampionship = new MyLabel("The championship year: ", LabelSize.MEDIUM);
		lblDeleteChampionship.setPrefWidth(280);
		TextField tfDeleteChampionship = new TextField();
		MyButton btnDeleteChampionship = new MyButton("Delete", ButtonSizeType.SMALL);
		btnDeleteChampionship.setOnAction(e -> {
			try {
				int year = Integer.parseInt(tfDeleteChampionship.getText());
				for (Championship wch : championships) {
					if ( wch.getChapionshipYear() == year) {
						championships.remove(wch);
						listeners.forEach(l->l.deleteChampionshipFromView(wch));		
						break;
					}
				}
				
			}catch (Exception ex) {
				showErrorInView(ex.getMessage());
				
			}
		});
		hbDeleteByChampionship.getChildren().addAll(lblDeleteChampionship, tfDeleteChampionship);
		vbDeleteByChampionship.setAlignment(Pos.CENTER);
		vbDeleteByChampionship.setSpacing(10);
		vbDeleteByChampionship.getChildren().addAll(hbDeleteByChampionship, btnDeleteChampionship);

		screen3 = new VBox();
		screen3.setAlignment(Pos.CENTER);
		screen3.setSpacing(30);
		screen3.getChildren().addAll(vbDeleteByID, vbDeleteByTournament, vbDeleteByChampionship);

		// root scene and stage
		root.getChildren().addAll(top, vbCenter);
		Scene scene = new Scene(root, WIDTH, HEIGHT);

		stage.setScene(scene);
		stage.show();
	}

	private void switchCenter(Node screen) {
		this.vbCenter.getChildren().clear();
		this.vbCenter.getChildren().add(screen);
	}

	@Override
	public void addListener(ViewListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void showErrorInView(String m) {
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(new MyLabel(m, LabelSize.LARGE));
		Scene scene = new Scene(root, 400, 300);
		
		Stage s = new Stage();
		s.setScene(scene);
		s.show();
	}

	@Override
	public void setDataToView(ArrayList<Tournament> tournaments2, ArrayList<Championship> championships2,
			ArrayList<Game> games2, ArrayList<Player> players2) {
		this.tournaments = tournaments2;
		this.championships = championships2;
		this.games = games2;
		this.players = players2;

	}
	
	public ArrayList<TournamentGame> getTournamentGamesOfPlayer(Player player) {
		ArrayList<TournamentGame> gamesTotal = new ArrayList<>();

		for (Tournament tournament : tournaments) {
			for (TournamentGame tgame : tournament.getGames()) {
				Player white = tgame.getWhitePlayer();
				Player black = tgame.getBlackPlater();
				if (white.equals(player) || black.equals(player)) {
					gamesTotal.add(tgame);
				}
			}
		}

		return gamesTotal;
	}

	public ArrayList<Championship> getChampionshipGamesOfPlayer(Player player) {
		ArrayList<Championship> gamesTotal = new ArrayList<>();

		for (Championship wch : championships) {
			if (wch.getBlackPlayer().equals(player) || wch.getWhitePlayer().equals(player))
				if (wch.getGames() != null)
					gamesTotal.add(wch);
		}

		return gamesTotal;
	}

	
	public ArrayList<ChampionshipGame> getGamesOfChampionship(int year) {
		ArrayList<ChampionshipGame> gamesTotal = new ArrayList<>();

		for (Championship wch : championships) {
			if (wch.getChapionshipYear() == year && wch.getGames()!= null) {
				gamesTotal.addAll(wch.getGames());
			}
		}

		return gamesTotal;
	}

	public ArrayList<TournamentGame> getGamesOfTournament(String name) {
		ArrayList<TournamentGame> gamesTotal = new ArrayList<>();

		for (Tournament tournament : tournaments) {
			if (tournament.getName().contains(name)) {
				gamesTotal.addAll(tournament.getGames());
			}
		}

		return gamesTotal;
	}

	public ArrayList<Game> getGamesWithEloAbove(int elo) {
		ArrayList<Game> gamesTotal = new ArrayList<>();
		ArrayList<Player> playersUnder = new ArrayList<>();

		for (Player player : playersUnder) {

			if (player.getElo() >= elo) {
				playersUnder.add(player);
			}
		}
		for (Game g : games) {

			for (Player player : playersUnder) {
				if (g instanceof TournamentGame) {
					TournamentGame tgame = (TournamentGame) g;
					if (player.getId().equals(tgame.getWhitePlayer().getId())
							|| player.getId().equals(tgame.getBlackPlater().getId())) {
						gamesTotal.add(g);
					}
				}
			}
		}

		for (Championship wch : championships) {
			if (wch.getWhitePlayer().getElo() >= elo || wch.getBlackPlayer().getElo() >= elo) {
				gamesTotal.addAll(wch.getGames());
			}
		}

		return gamesTotal;
	}

	public ArrayList<Game> getGamesWithEloUnder(int elo) {
		ArrayList<Game> gamesTotal = new ArrayList<>();
		ArrayList<Player> playersUnder = new ArrayList<>();

		for (Player player : playersUnder) {

			if (player.getElo() <= elo) {
				playersUnder.add(player);
			}
		}
		for (Game g : games) {

			for (Player player : playersUnder) {
				if (g instanceof TournamentGame) {
					TournamentGame tgame = (TournamentGame) g;
					if (player.getId().equals(tgame.getWhitePlayer().getId())
							|| player.getId().equals(tgame.getBlackPlater().getId())) {
						gamesTotal.add(g);
					}
				}
			}
		}

		for (Championship wch : championships) {
			if (wch.getWhitePlayer().getElo() <= elo || wch.getBlackPlayer().getElo() <= elo) {
				gamesTotal.addAll(wch.getGames());
			}
		}

		return gamesTotal;
	}

}
