package application.orm.entities;

import java.util.Objects;

public class TournamentGame extends Game {

	private int tournamentId;
	private Player whitePlayer;
	private Player blackPlater;

	public TournamentGame(int id, Player whitePlayer, Player blackPlater, Game game) {
		super(game.getId(), game.getDate(), game.getWhiteResult(), game.getBlackResult(),
				game.getNotations());
		this.tournamentId = id;
		this.whitePlayer = whitePlayer;
		this.blackPlater = blackPlater;
	}

	public TournamentGame() {
		super();
		this.tournamentId = 0;
		this.whitePlayer = null;
		this.blackPlater = null;
	}

	public int getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(int id) {
		this.tournamentId = id;
	}

	public Player getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(Player whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	public Player getBlackPlater() {
		return blackPlater;
	}

	public void setBlackPlater(Player blackPlater) {
		this.blackPlater = blackPlater;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(tournamentId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TournamentGame other = (TournamentGame) obj;
		return tournamentId == other.tournamentId;
	}

}
