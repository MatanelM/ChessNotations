package application.orm.interfaces;

import java.util.ArrayList;

import application.orm.entities.Championship;

public interface ChampionshipDBable {

	public ArrayList<Championship> getAllChampionships();
	public Championship getChampionshipByYear(int year);
	public void addChampionship(Championship championship);
	public boolean isChampionshipExists(int chapionshipYear);
	public void deleteChampionship(Championship c);
}
