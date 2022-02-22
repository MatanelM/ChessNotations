package application.orm.entities;

import java.util.Objects;

public class Player {

	private String id;
	private String firstName;
	private String lastName;

	private CountryEnum country;
	private int elo;
	private int bestElo;
	private int yearOfBirth;
	
	public Player(String id, String firstName, String lastName, CountryEnum country, int elo, int bestElo,
			int yearOfBirth) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.elo = elo;
		this.bestElo = bestElo;
		this.yearOfBirth = yearOfBirth;
	}

	public Player() {
		this.id = "";
		this.firstName = "";
		this.lastName = "";
		this.country = null;
		this.elo = 0;
		this.bestElo = 0;
		this.yearOfBirth = 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public CountryEnum getCountry() {
		return country;
	}

	public void setCountry(CountryEnum country) {
		this.country = country;
	}

	public int getElo() {
		return elo;
	}

	public void setElo(int elo) {
		this.elo = elo;
	}

	public int getBestElo() {
		return bestElo;
	}

	public void setBestElo(int bestElo) {
		this.bestElo = bestElo;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country
				+ ", elo=" + elo + ", bestElo=" + bestElo + ", yearOfBirth=" + yearOfBirth + "]";
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
		Player other = (Player) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
