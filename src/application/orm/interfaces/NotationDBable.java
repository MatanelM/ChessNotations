package application.orm.interfaces;

import java.util.ArrayList;

import application.orm.entities.Notation;

public interface NotationDBable {

	ArrayList<Notation> getGameNotations(int gameId);
	Notation getNotationByGameAndId(int gameId, int notationId);
	void removeNotation(int gameId, int notationId);
	void updateNotationDescription(int gameId, Notation notation);
	void addNotation(int gameId, Notation notation);
	
}
