package application.IO;

import application.orm.entities.Championship;
import application.orm.entities.Game;
import application.orm.entities.Player;
import application.orm.entities.Tournament;

public class InputDataStructure {

	Game game = null;
	String country = "", round = "";
	String result= "";
	Player white = new Player(), black = new Player();
	Tournament tournament = new Tournament();
	Championship championship = new Championship();
	
}
