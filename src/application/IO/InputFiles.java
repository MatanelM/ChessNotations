package application.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

import application.db.ChampionshipDB;
import application.db.GameDB;
import application.db.PlayerDB;
import application.db.TournamentDB;
import application.orm.entities.Championship;
import application.orm.entities.ChampionshipGame;
import application.orm.entities.CountryEnum;
import application.orm.entities.Game;
import application.orm.entities.Notation;
import application.orm.entities.Player;
import application.orm.entities.Tournament;
import application.orm.entities.TournamentGame;
import application.util.Util;

public class InputFiles {
	/*
	 * public static void main(String[] args) {
	 * 
	 * // String filepath = "2013 Canadian Open - PGN 139g.pgn"; String filepath =
	 * "C:\\Users\\matan\\OneDrive\\שולחן העבודה\\215803.pgn"; try {
	 * readFile(filepath); } catch (FileNotFoundException e) { e.printStackTrace();
	 * }
	 * 
	 * }
	 */
	
	public static void readFile(String filepath,  ArrayList<Tournament> tournaments,  ArrayList<Championship> championships, ArrayList<Game> games, ArrayList<Player> players) throws FileNotFoundException {
		
		Scanner fileReader = new Scanner(new File(filepath));
		PlayerDB playerDB = new PlayerDB();
		TournamentDB tournamentDB = new TournamentDB();
		ChampionshipDB championshipDB = new ChampionshipDB();
		GameDB gameDB = new GameDB();

		while (fileReader.hasNext()) {
			try {
				InputDataStructure data = new InputDataStructure();
				if (!readPart(fileReader, data))
					continue;
				data.game.setResult(data.result);
				boolean whiteExists = playerDB.isPlayerExists(data.white.getFirstName(), data.white.getLastName());
				if (!whiteExists) {
					playerDB.addPlayer(data.white);
				}
				boolean blackExists = playerDB.isPlayerExists(data.black.getFirstName(), data.black.getLastName());
				if (!blackExists) {
					playerDB.addPlayer(data.black);
				}

				data.white = playerDB.getPlayerByName(data.white.getFirstName(), data.white.getLastName());
				data.black = playerDB.getPlayerByName(data.black.getFirstName(), data.black.getLastName());
				if ( !whiteExists ) {
					players.add(data.white);
				}
				if ( !blackExists) {
					players.add(data.black);
				}
				
				if (data.championship == null) {
					Tournament tournament = null;
					if (tournamentDB.getTournamentByName(data.tournament.getName()) == null) {
						data.tournament.setCountry(Util.getCountryEnumByAlpha3(data.country));

						tournamentDB.addTournament(data.tournament);
						tournament = tournamentDB.getTournamentByName(data.tournament.getName());
						tournaments.add(tournament);
					}
					for (Tournament t: tournaments) {
						if ( t.getName().equals(data.tournament.getName())) {
							tournament = t;
							break;
						}
					}
					
					TournamentGame tournamentGame = new TournamentGame(tournament.getId(), data.white, data.black,
							data.game);
					tournamentGame.setWhitePlayer(data.white);
					tournamentGame.setBlackPlater(data.black);
					tournamentGame.setNotations(data.game.getNotations());
					tournamentGame.setWhiteResult(data.game.getWhiteResult());
					tournamentGame.setBlackResult(data.game.getBlackResult());
					tournamentGame.setDate(data.game.getDate());
					gameDB.addTournamentGame(tournamentGame, tournament.getId());
					
					games.add(tournamentGame);

				} else if (data.tournament == null) {
					Championship championship = null;
					if (!championshipDB.isChampionshipExists(data.championship.getChapionshipYear())) {
						data.championship.setBlackPlayer(data.black);
						data.championship.setWhitePlayer(data.white);
						data.championship.setCountry(Util.getCountryEnumByAlpha3(data.country));
						championshipDB.addChampionship(data.championship);
						
						championship = championshipDB.getChampionshipByYear(data.championship.getChapionshipYear());
						championships.add(championship);
					}
					for (Championship c : championships) {
						if ( c.getChapionshipYear() == data.championship.getChapionshipYear()) {
							championship = c;
							break;
						}
					}
					ChampionshipGame championshipGame = new ChampionshipGame();
					championshipGame.setGameNumber(Integer.parseInt(data.round));
					championshipGame.setNotations(data.game.getNotations());
					championshipGame.setWhiteResult(data.game.getWhiteResult());
					championshipGame.setBlackResult(data.game.getBlackResult());
					championshipGame.setDate(data.game.getDate());
					gameDB.addChampionshipGame(championshipGame, championship.getChampionshipId());
					games.add(championshipGame);
					championship.getGames().add(championshipGame);
				}

				System.out.println(data.white);
				System.out.println(data.black);
				System.out.println(data.game.toString());
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("error reading data");
			}
			
		}
		System.out.println("Done");
	}

	public static boolean readPart(Scanner fileReader, InputDataStructure data) throws FileNotFoundException {

		String line;
		String firstName, lastName;
		String result = "";
		String event = "";
		Date date = null, eventDate;

		while (fileReader.hasNext()) {
			line = fileReader.nextLine();
			if (line.startsWith("[")) {

				int length = line.length();
				line = line.substring(1, length - 1);

				String key, value, valueWithSpaces;
				key = line.split(" ")[0];
				value = "";
				valueWithSpaces = "";
				for (int i = 1; i < line.split(" ").length; i++) {
					value += line.split(" ")[i];
					valueWithSpaces += line.split(" ")[i] + " ";
				}
				value = value.trim();
				valueWithSpaces = valueWithSpaces.trim();
				value = value.substring(1, value.length() - 1);
				valueWithSpaces = valueWithSpaces.substring(1, valueWithSpaces.length() - 1);
				switch (key) {
				case "White":
					firstName = value.split(",")[1].trim();
					lastName = value.split(",")[0].trim();
					data.white.setFirstName(firstName);
					data.white.setLastName(lastName);
					break;
				case "Black":
					
					firstName = value.split(",")[1].trim();
					lastName = value.split(",")[0].trim();
					data.black.setFirstName(firstName);
					data.black.setLastName(lastName);
					break;
				case "WhiteElo":
					try {
						data.white.setElo(Integer.parseInt(value));
						
					}catch(Exception e) {
						
					}
					break;
				case "BlackElo":
					try {
						data.black.setElo(Integer.parseInt(value));						
					}catch(Exception e) {
						
					}
					break;
				case "Result":
					result = value;
					break;
				case "Round":
					data.round = value;
					break;
				case "Date":
					if (value.contains("?"))
						break;
					value = value.replace(".", "-");
					value = value.replace(".", "-");
					date = Date.valueOf(value);
					break;
				case "Event":
					if (value.toLowerCase().contains("wch") || value.toLowerCase().contains("championship")) {
						// create new championship
						data.tournament = null;
						String[] split = valueWithSpaces.split(" ");
						for (String string : split) {
							try {
								data.championship.setChapionshipYear(Integer.parseInt(string));
							} catch (Exception e) {
								continue;
							}
						}
					} else {
						data.championship = null;
						data.tournament.setName(valueWithSpaces);
					}
					break;
				case "EventDate":
					if (value.contains("?"))
						break;
					value = value.replace(".", "-");
					value = value.replace(".", "-");
					eventDate = Date.valueOf(value);
					break;
				case "Site":
					String[] split = valueWithSpaces.split(" ");
					for (String string : split) {
						CountryEnum c = Util.getCountryEnumByAlpha3(string);
						if (c != null) {
							data.country = c.getAlpha3Code();
							break;
						}

						if (string.length() == 3) {
							boolean isCountry = true;
							for (char ch : string.toCharArray()) {
								if (ch >= 'A' && ch <= 'Z')
									continue;
								else
									isCountry = false;
							}
							if (isCountry) {
								data.country = string;
							}
							break;
						}
					}
					break;
				case "EventCountry":
					data.country = value;
					break;
				case "BlackCountry":
					for (int i = 0; i < CountryEnum.values().length; i++) {
						if (CountryEnum.values()[i].getAlpha3Code().equals(value)) {
							data.black.setCountry(CountryEnum.values()[i]);
							break;
						}
					}
					break;
				case "WhiteCountry":
					for (int i = 0; i < CountryEnum.values().length; i++) {
						if (CountryEnum.values()[i].getAlpha3Code().equals(value)) {
							data.white.setCountry(CountryEnum.values()[i]);
							break;
						}
					}
					break;

				default:
					System.out.println(line);
					break;
				}
			}

			if (line.startsWith("1.")) {
				String notations = "";
				while (!line.isEmpty()) {
					notations += line + " ";
					if (fileReader.hasNext())
						line = fileReader.nextLine();
					else
						line = "";
				}
				while (notations.contains("{")) {
					int start = notations.indexOf("{");
					int end = notations.indexOf("}");
					notations = notations.substring(0, start) + notations.substring(end + 1, notations.length());
				}
				while (notations.contains("(") ) {
					int start = notations.indexOf("(");
					
					Stack<Integer> stack = new Stack<>();
					
					
					while (  notations.indexOf("(", start+1) != -1 && notations.indexOf("(", start+1) < notations.indexOf(")")  ) {
						stack.push(start);
						start = notations.indexOf("(", start+1);
					}

					while( stack.size() > 1) {
						int end = notations.indexOf(")");
						notations = notations.substring(0, start) + notations.substring(end + 1, notations.length());
						start = stack.pop();
					}
					int end = notations.indexOf(")", start);
					notations = notations.substring(0, start) + notations.substring(end + 1, notations.length());
					
				}
				String[] splitNotations = notations.split(" ");
				ArrayList<String> stripped = new ArrayList<>();
				for (String string : splitNotations) {
					if (string.equals("") || string.contains("$") || string.endsWith(".."))
						continue;
					stripped.add(string);
				}
				splitNotations = (String[]) stripped.toArray(new String[stripped.size()]);
				stripped.clear();
				for(String string: splitNotations) {
					for(String string1: string.split("\\.")) {
						if (!string.equals(""))
								stripped.add(string1);
					}
				}
				splitNotations = (String[]) stripped.toArray(new String[stripped.size()]);
				
				ArrayList<Notation> notationsList = new ArrayList<>();

				
				
				for (int i = 0; i < splitNotations.length; i += 3) {
					Notation notation = new Notation();
					int num = 0;
					String description = "";
					for (int j = 0; j < 3; j++) {
						// got to last
						if (i + j == splitNotations.length - 1) {
							result = splitNotations[i + j];
							break;
						}
						if (j == 0) {
							try {
								num = Integer.parseInt(splitNotations[i + j].replace(".", ""));

							} catch (Exception c) {
								System.out.println(splitNotations);
							}
						} else {
							description += splitNotations[i + j] + " ";
						}
					}
					if (num != 0) {
						notation.setId(num);
						notation.setNotationDescription(description);
						notationsList.add(notation);
					}
				}
				Game game = new Game();
				game.setNotations(notationsList);
				if (date != null)
					game.setDate(date);
				game.setResult(result);
				data.game = game;

				System.out.println("Done with 1 data");
				return true;
			}
		}
		return false;

	}

}
