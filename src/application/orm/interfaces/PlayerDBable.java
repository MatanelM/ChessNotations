package application.orm.interfaces;

import java.util.ArrayList;

import application.orm.entities.Player;

public interface PlayerDBable {

	public ArrayList<Player> getAllPlayers();

	public Player getPlayerById(String id);

	public Player getPlayerByName(String firstName, String lastName);

	public boolean isPlayerExists(String firstName, String lastName);

	public ArrayList<Player> getPlayersAboveElo(int elo);

	public Player getWorldChampion();

	public Player getHighestPlayerElo();

	public void removePlayer(int id);

	public void addPlayer(Player player);

}
