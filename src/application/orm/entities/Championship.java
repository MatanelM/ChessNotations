package application.orm.entities;

import java.util.ArrayList;
import java.util.Objects;

public class Championship {
	
	private int championshipId;
    private Player whitePlayer;
    private Player blackPlayer;
    private CountryEnum country;
    private int chapionshipYear;
    private ArrayList<ChampionshipGame> games;
    
	public Championship(int championshipId, Player whitePlayer, Player blackPlayer, CountryEnum country,
			int chapionshipYear) {
		super();
		this.championshipId = championshipId;
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		this.country = country;
		this.chapionshipYear = chapionshipYear;
	}

	public Championship() {
		this.championshipId = 0;
		this.whitePlayer = null;
		this.blackPlayer = null;
		this.country = null;
		this.chapionshipYear = 1970;
	}

	public int getChampionshipId() {
		return championshipId;
	}

	public void setChampionshipId(int championshipId) {
		this.championshipId = championshipId;
	}

	public Player getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(Player whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	public Player getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(Player blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	public CountryEnum getCountry() {
		return country;
	}

	public void setCountry(CountryEnum country) {
		this.country = country;
	}

	public int getChapionshipYear() {
		return chapionshipYear;
	}

	public void setChapionshipYear(int chapionshipYear) {
		this.chapionshipYear = chapionshipYear;
	}

	@Override
	public String toString() {
		return "Championship [championshipId=" + championshipId + ", whitePlayer=" + whitePlayer + ", blackPlayer="
				+ blackPlayer + ", country=" + country + ", chapionshipYear=" + chapionshipYear + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(championshipId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Championship other = (Championship) obj;
		return championshipId == other.championshipId;
	}

	public ArrayList<ChampionshipGame> getGames() {
		return games;
	}

	public void setGames(ArrayList<ChampionshipGame> games) {
		this.games = games;
	}    

}
