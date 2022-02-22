package application.orm.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Game {

	private int id;
	private Date date;
	private float whiteResult;
	private float blackResult;

	private ArrayList<Notation> notations;

	public Game(int id, Date date, float whiteResult, float blackResult,
			ArrayList<Notation> notations) {
		super();
		this.id = id;
		this.date = date;
		this.whiteResult = whiteResult;
		this.blackResult = blackResult;
		this.setNotations(notations);
	}

	public Game() {
		this.id = 0;
		this.date = Date.valueOf(LocalDate.now());
		this.blackResult = 0;
		this.whiteResult = 0;
		this.notations = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getBlackResult() {
		return blackResult;
	}

	public void setBlackResult(float blackResult) {
		this.blackResult = blackResult;
	}

	public float getWhiteResult() {
		return whiteResult;
	}

	public void setWhiteResult(float whiteResult) {
		this.whiteResult = whiteResult;
	}

	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("Game %d: date: %s \n", id, date.toString()));
		for (Notation notation : notations) {
			sb.append(String.format("%d. %s\n", notation.getId(), notation.getNotationDescription()));
		}
		
		if (whiteResult==1)
			sb.append("1-0\n");
		else if (whiteResult==0)
			sb.append("0-1\n");
		else
			sb.append("1/2-1/2 \n");
		
		return sb.toString();
		
	}

	
	
	public ArrayList<Notation> getNotations() {
		return notations;
	}

	public void setNotations(ArrayList<Notation> notations) {
		this.notations = notations;
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
		Game other = (Game) obj;
		return id == other.id;
	}

	public void setResult(String result) {
		switch(result) {
		case "1-0":
			this.blackResult = 0;
			this.whiteResult = 1;
			break;
		case "0-1":
			this.blackResult = 1;
			this.whiteResult = 0;
			break;
		case "1/2-1/2":
			this.blackResult = (float)(1.0/2);
			this.whiteResult = (float)(1.0/2);
			break;
		}
	}

}
