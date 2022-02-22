package application.MVC;

import java.util.ArrayList;

import application.orm.entities.Championship;
import application.orm.entities.Game;
import application.orm.entities.Player;
import application.orm.entities.Tournament;

public interface AbstractView {

	void addListener(ViewListener listener);

	void showErrorInView(String m);

	void setDataToView(ArrayList<Tournament> tournaments2, ArrayList<Championship> championships2,
			ArrayList<Game> games2, ArrayList<Player> players2);
	
}
