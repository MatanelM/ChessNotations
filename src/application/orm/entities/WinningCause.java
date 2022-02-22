package application.orm.entities;

public enum WinningCause {

	// WIN
	MATE(1, "Checkate"), TIME(2, "Time"), RESIGNATION(3, "Resignation"), FORFEIT(4, "Forfeit"),
	// DRAW
	REPETITION(5, "Repetition"), STALEMATE(6, "Stalemate"), INSUFFICIENT_MATERIAL(7, "Insufficient Material"),
	AGREEMENT(8, "Agreement");

	private int enumNumber;
	private String enumString;

	WinningCause(int enumNumber, String enumString) {
		this.setEnumNumber(enumNumber);
		this.setEnumString(enumString);
	}

	public String getEnumString() {
		return enumString;
	}

	public void setEnumString(String enumString) {
		this.enumString = enumString;
	}

	public int getEnumNumber() {
		return enumNumber;
	}

	public void setEnumNumber(int enumNumber) {
		this.enumNumber = enumNumber;
	}
	
}
