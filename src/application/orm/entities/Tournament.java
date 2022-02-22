package application.orm.entities;

import java.util.ArrayList;
import java.util.Objects;

public class Tournament {

	private int id;
	private String name;
	private CountryEnum country;
	private int tournamentYear;
	private ArrayList<TournamentGame> games;

	public Tournament(int id, String name, CountryEnum country, int tournamentYear, ArrayList<TournamentGame> games) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.tournamentYear = tournamentYear;
		this.games = games;
	}

	public Tournament() {
		this.id = 0;
		this.name = "";
		this.country = null;
		this.tournamentYear = 1970;
		this.games = new ArrayList<TournamentGame>();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CountryEnum getCountry() {
		return country;
	}

	public void setCountry(CountryEnum country) {
		this.country = country;
	}

	public int getTournamentYear() {
		return tournamentYear;
	}

	public void setTournamentYear(int tournamentYear) {
		this.tournamentYear = tournamentYear;
	}

	public ArrayList<TournamentGame> getGames() {
		return games;
	}

	public void setGames(ArrayList<TournamentGame> games) {
		this.games = games;
	}

	@Override
	public String toString() {
		return "Tournament [id=" + id + ", name=" + name + ", country=" + country + ", tournamentYear=" + tournamentYear
				+ ", games=" + games + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tournament other = (Tournament) obj;
		return id == other.id;
	}

}
