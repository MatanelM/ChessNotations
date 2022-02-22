package application.orm.entities;

import java.util.Objects;

public class Notation {

	private static int counter = 0;
	
	private int id;
	private String notationDescription;
	
	public Notation(String notationDescription) {
		super();
		this.id = counter++;
		this.notationDescription = notationDescription;
	}
	
	public Notation() {
		super();
		this.id = counter++;
		this.notationDescription = "";
	}
	

	public Notation(int id, String notationDescription) {
		super();
		this.id = id;
		this.notationDescription = notationDescription;
	}

	public static int getCounter() {
		return counter;
	}
	
	public static void reduceCounter() {
		Notation.counter--;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNotationDescription() {
		return notationDescription;
	}
	public void setNotationDescription(String notationDescription) {
		this.notationDescription = notationDescription;
	}
	@Override
	public String toString() {
		return String.format("%d. %s", this.id, this.notationDescription);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, notationDescription);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notation other = (Notation) obj;
		return id == other.id && Objects.equals(notationDescription, other.notationDescription);
	}
	
	
}
