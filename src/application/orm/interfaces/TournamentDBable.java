package application.orm.interfaces;

import java.util.ArrayList;

import application.orm.entities.Tournament;

public interface TournamentDBable {
	
	public ArrayList<Tournament> getAllTournaments();
	public Tournament getTournamentById(int id);
	public Tournament getTournamentByName(String name);
	public void addTournament(Tournament tournament);
	public boolean isTournamentExists(String name);
	public void deleteTournamentByName(String name);
	
}
